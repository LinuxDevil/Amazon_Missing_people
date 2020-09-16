package com.teckathon.missingpeopleapp.ui.fragments.home

import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.databinding.PostBinding
import com.xwray.groupie.databinding.BindableItem

class Post(private val post: Missing): BindableItem<PostBinding>() {

    override fun bind(viewBinding: PostBinding, position: Int) {
        viewBinding.missing = post
    }

    override fun getLayout(): Int = R.layout.post

}