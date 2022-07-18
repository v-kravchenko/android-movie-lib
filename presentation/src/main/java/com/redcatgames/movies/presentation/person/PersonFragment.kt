package com.redcatgames.movies.presentation.person

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannedString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.PersonFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import com.redcatgmes.movies.baseui.util.loadByUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment : BaseFragment() {

    private val args by navArgs<PersonFragmentArgs>()
    private val viewModel: PersonViewModel by viewModels()
    private var binding: PersonFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        binding.topAppBar.title = args.personTitle
        setupObserver()
    }

    private fun setupObserver() {

        viewModel.personInfo.observe { info ->
            info?.let { personInfo ->
                binding.textOriginalName.text = buildSpannedString {
                    bold {
                        append(getString(R.string.person_original_name_title))
                    }
                    append(" ${personInfo.name}")
                }
                binding.textBirthday.text = buildSpannedString {
                    bold {
                        append(getString(R.string.person_birthday_title))
                    }
                    append(" ${(personInfo.birthDay ?: "-")}")
                }

                binding.textOverview.text = personInfo.biography.ifEmpty {
                    "-"
                }
                binding.personPhoto.loadByUrl("w154${personInfo.profilePath}") {
                    placeholder(R.drawable.person_placeholder_w154)
                    error(R.drawable.person_placeholder_w154)
                }
            }
        }

        viewModel.loadPersonEvent.observe {
            it.onFailure { throwable ->
                Toast.makeText(
                    requireContext(),
                    "Error loading: ${throwable.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}