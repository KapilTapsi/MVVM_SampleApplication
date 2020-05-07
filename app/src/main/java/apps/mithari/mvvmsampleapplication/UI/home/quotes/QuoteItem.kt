package apps.mithari.mvvmsampleapplication.ui.home.quotes

import apps.mithari.mvvmsampleapplication.R
import apps.mithari.mvvmsampleapplication.data.db.entities.Quote
import apps.mithari.mvvmsampleapplication.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote: Quote) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}