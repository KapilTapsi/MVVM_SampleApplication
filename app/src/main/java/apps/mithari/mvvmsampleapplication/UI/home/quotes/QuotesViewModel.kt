package apps.mithari.mvvmsampleapplication.ui.home.quotes

import androidx.lifecycle.ViewModel
import apps.mithari.mvvmsampleapplication.data.repositories.QuotesRepository
import apps.mithari.mvvmsampleapplication.util.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {
    val quotes by lazyDeferred { repository.getQuotes() }
//    here lazyDeferred is just a name we gave to a generic type function
}
