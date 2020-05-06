package apps.mithari.mvvmsampleapplication.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import apps.mithari.mvvmsampleapplication.data.db.AppDatabase
import apps.mithari.mvvmsampleapplication.data.db.entities.Quote
import apps.mithari.mvvmsampleapplication.data.network.MyApi
import apps.mithari.mvvmsampleapplication.data.network.SafeApiRequest
import apps.mithari.mvvmsampleapplication.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
//            above we used observeForever because this will observe the data forever irrespective of
//            activity/fragment in which we are in.
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        if (isFetchNeeded()) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
//            setValue() method must be called from the main thread.
//            But if you need set a value from a background thread, postValue() should be used.
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io { db.getQuoteDao().saveAllQuotes(quotes) }
    }
}