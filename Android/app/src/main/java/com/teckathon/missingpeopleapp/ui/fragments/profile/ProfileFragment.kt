package com.teckathon.missingpeopleapp.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.databinding.ProfileFragmentBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class ProfileFragment : Fragment(), DIAware {

    override val di: DI by lazy { (context?.applicationContext as DIAware).di }
    private val factory: ProfileViewModelFactory by instance()

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}