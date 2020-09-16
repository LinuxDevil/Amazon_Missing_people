package com.teckathon.missingpeopleapp.ui.fragments.found

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.databinding.InformFoundFragmentBinding
import com.teckathon.missingpeopleapp.util.Coroutines
import kotlinx.android.synthetic.main.inform_missing_fragment.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import java.time.LocalDate

class InformFoundFragment : Fragment(), DIAware {

    private lateinit var viewModel: InformFoundViewModel
    override val di: DI by lazy { (context?.applicationContext as DIAware).di }
    private val factory: InformFoundViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: InformFoundFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.inform_found_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(InformFoundViewModel::class.java)
        binding.viewmodel = viewModel

        binding.submitButton.setOnClickListener {
            val firstName: String = informFirstNameEditText.text.toString();
            val middleName = informMiddleNameEditText.text.toString();
            val lastName = informLastNameEditText.text.toString();
            val city = informCityEditText.text.toString();
            val locationFound: String = informLocationFoundEditText.text.toString();
            val notes = informNotesEditText.text.toString();

            Coroutines.readWrite {
                viewModel.addFound(
                    name = firstName,
                    "DEFAULT",
                    "9999999999",
                    "Male",
                    LocalDate.now().toString(),
                    city,
                    "DEFAULT",
                    LocalDate.now().toString(),
                    lastKnownLocation = locationFound,
                    notes
                )
            }

        }


        return binding.root
    }


}