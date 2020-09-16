package com.teckathon.missingpeopleapp.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.data.network.responses.MissingResponse
import com.teckathon.missingpeopleapp.util.Coroutines
import com.teckathon.missingpeopleapp.util.hide
import com.teckathon.missingpeopleapp.util.show
import com.teckathon.missingpeopleapp.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainFragment : Fragment(), DIAware {

    private lateinit var viewModel: MainViewModel

    override val di: DI by lazy { (context?.applicationContext as DIAware).di }
    private val factory: MainViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = Coroutines.main {
        progressBar.show()
        viewModel.missing.await().observe(viewLifecycleOwner,  Observer {
            progressBar.hide()
            initRecycler(it.toPost())
        })
    }

    private fun initRecycler(post: List<Post>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(post)
        }
        postRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    private fun Array<Missing>.toPost(): List<Post> {
        return this.map {
            Post(it)
        }
    }

}