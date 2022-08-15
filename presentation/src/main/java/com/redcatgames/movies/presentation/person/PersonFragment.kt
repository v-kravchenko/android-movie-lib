package com.redcatgames.movies.presentation.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redcatgames.movies.domain.model.Person
import com.redcatgames.movies.presentation.ProfileSize
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.PersonFragmentBinding
import com.redcatgames.movies.presentation.getProfileUri
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import com.redcatgmes.movies.baseui.util.currentLocale
import com.redcatgmes.movies.baseui.util.loadByUrl
import com.redcatgmes.movies.baseui.util.visible
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class PersonFragment : BaseFragment() {

    private val args by navArgs<PersonFragmentArgs>()
    private val viewModel: PersonViewModel by viewModels()
    private var binding: PersonFragmentBinding by autoCleared { it.movieList.adapter = null }
    private val dateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("dd.MM.yyyy", requireContext().currentLocale)
    }

    private val moviesAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = PersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieList.adapter = moviesAdapter

        moviesAdapter.onItemClick =
            {
                navigateTo(
                    PersonFragmentDirections.actionPersonFragmentToMovieFragment(
                        it.movieId, it.title))
            }

        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        binding.topAppBar.title = args.personTitle
        binding.personPhoto.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                if (args.gender == 2) R.drawable.person_placeholder_medium_male
                else R.drawable.person_placeholder_medium_female))

        setupObserver()
    }

    private fun setupObserver() {

        viewModel.personInfo.observe { info ->
            info?.let { personInfo ->

                updateBaseInfo(personInfo.person)

                binding.textBiography.text = personInfo.person.biography.ifEmpty { "-" }

                moviesAdapter.setItems(personInfo.casts.sortedByDescending { it.popularity })
            }
        }

        viewModel.events.observe { event ->
            when (event) {
                is PersonViewModel.Event.PersonLoaded ->
                    event.result.onFailure { showToast("Error loading: ${it.message}") }
            }
        }
    }

    private fun updateBaseInfo(person: Person) {

        binding.personPhoto.loadByUrl(person.getProfileUri(ProfileSize.MEDIUM)) {
            val resId =
                if (person.gender == 2) R.drawable.person_placeholder_medium_male
                else R.drawable.person_placeholder_medium_female

            placeholder(resId)
            error(resId)
        }

        binding.textOriginalName.text =
            buildSpannedString {
                bold { append(getString(R.string.person_original_name_title)) }
                append(" ${person.name}")
            }
        binding.textBirthday.text =
            buildSpannedString {
                bold { append(getString(R.string.person_birthday_title)) }
                val birthDay = person.birthDay
                if (birthDay == null) {
                    append(" -")
                } else {
                    append(" ${dateFormat.format(birthDay)}")
                }
            }

        binding.textDeathday.text =
            buildSpannedString {
                val deathDay = person.deathDay
                if (deathDay != null) {
                    bold { append(getString(R.string.person_death_title)) }
                    append(" ${dateFormat.format(deathDay)}")
                }
            }

        binding.viewBaseInfo.visible()
    }
}
