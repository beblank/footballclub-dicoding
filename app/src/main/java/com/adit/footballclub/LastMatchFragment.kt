package com.adit.footballclub


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.footballclub.viewmodel.EventsViewModel
import com.adit.footballclub.viewmodel.EventsViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment() {

    @Inject
    lateinit var eventsViewModelFactory: EventsViewModelFactory
    lateinit var eventsViewModel: EventsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsViewModel = ViewModelProviders.of(this, eventsViewModelFactory).get(EventsViewModel::class.java)
        eventsViewModel.getListEvents().observe(this, Observer {
            if (it != null){
                Log.d("dodol", "${it}")
            }
        })
        eventsViewModel.getListEventsError().observe(this, Observer {
            Log.d("dodol", "$it")
        })
        eventsViewModel.getEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        eventsViewModel.disposeElements()
    }
}
