package com.redcatgames.movies.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.redcatgames.movies.R
import com.redcatgames.movies.domain.model.Language

class LanguageAdapter(context: Context) :
    ArrayAdapter<com.redcatgames.movies.domain.model.Language?>(context, R.layout.list_item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        getItem(position)?.let { item ->
            val text1 = view?.findViewById<TextView>(R.id.text1)
            text1?.text = item.englishName
        }

        return view ?: throw Exception("View is null")
    }
}