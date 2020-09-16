package com.teckathon.missingpeopleapp.ui.fragments.missing

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonParser
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.databinding.InformMissingFragmentBinding
import com.teckathon.missingpeopleapp.util.Coroutines
import kotlinx.android.synthetic.main.inform_missing_fragment.*
import org.json.JSONStringer
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class InformMissingFragment : Fragment(), DIAware {

    private lateinit var viewModel: InformMissingViewModel
    override val di: DI by lazy { (context?.applicationContext as DIAware).di }
    private val factory: InformMissingViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: InformMissingFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.inform_missing_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(InformMissingViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.submitButton.setOnClickListener {
            val firstName: String = informFirstNameEditText.text.toString();
            val middleName = informMiddleNameEditText.text.toString();
            val lastName = informLastNameEditText.text.toString();
            val city = informCityEditText.text.toString();
            val locationFound: String = informLocationFoundEditText.text.toString();
            val notes = informNotesEditText.text.toString();

            Coroutines.readWrite {
                viewModel.addMissing(
                    name = firstName,
                    "DEFAULT",
                    "DEFAULT",
                    "DEFAULT",
                    "DEFAULT",
                    "DEFAULT",
                    "DEFAULT",
                    "DEFAULT",
                    lastKnownLocation = locationFound,
                    "888888888",
                )
            }

        }




        return binding.root
    }

}