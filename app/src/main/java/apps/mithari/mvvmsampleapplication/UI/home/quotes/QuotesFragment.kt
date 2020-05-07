package apps.mithari.mvvmsampleapplication.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import apps.mithari.mvvmsampleapplication.R
import apps.mithari.mvvmsampleapplication.data.db.entities.Quote
import apps.mithari.mvvmsampleapplication.util.Coroutines
import apps.mithari.mvvmsampleapplication.util.hide
import apps.mithari.mvvmsampleapplication.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
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
        /*
        Earlier we used this
        * Coroutines.main {
            val quotes = viewModel.quotes.await()
//            await() is used with deferred function to get the eventual result

            quotes.observe(viewLifecycleOwner, Observer {
//                viewLifeCycleOwner is the context for the fragment
                context?.toast((it.size.toString()))
            })
        }
        * */
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())

        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {
//        now we create adapter
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(quoteItem)
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    //    now we create funtion to convert list of quotes to list of quoteitem
    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map { QuoteItem(it) }
//    here it represents the particular item of type quote
    }

}
