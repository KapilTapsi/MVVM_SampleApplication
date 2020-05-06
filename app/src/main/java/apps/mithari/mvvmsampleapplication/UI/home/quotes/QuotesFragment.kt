package apps.mithari.mvvmsampleapplication.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import apps.mithari.mvvmsampleapplication.R
import apps.mithari.mvvmsampleapplication.util.Coroutines
import apps.mithari.mvvmsampleapplication.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance<QuotesViewModelFactory>()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)
        Coroutines.main {
            val quotes = viewModel.quotes.await()
//            await() is used with deferred function to get the eventual result

            quotes.observe(viewLifecycleOwner, Observer {
//                viewLifeCycleOwner is the context for the fragment
                context?.toast((it.size.toString()))
            })
        }
    }

}
