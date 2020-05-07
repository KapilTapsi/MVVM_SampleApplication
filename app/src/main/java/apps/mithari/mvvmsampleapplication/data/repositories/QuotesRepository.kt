package apps.mithari.mvvmsampleapplication.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import apps.mithari.mvvmsampleapplication.data.db.AppDatabase
import apps.mithari.mvvmsampleapplication.data.db.entities.Quote
import apps.mithari.mvvmsampleapplication.data.network.MyApi
import apps.mithari.mvvmsampleapplication.data.network.SafeApiRequest
import apps.mithari.mvvmsampleapplication.data.preferences.PreferenceProvider
import apps.mithari.mvvmsampleapplication.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

private val MINIMUM_INTERVAL = 6 //hours
class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    private val simpleDateFormat = SimpleDateFormat.getDateTimeInstance()

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
        val lastSavedAt = prefs.getLastSaveAt()
        if (lastSavedAt == null || isFetchNeeded(simpleDateFormat.parse(lastSavedAt)!!)) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
//            setValue() method must be called from the main thread.
//            But if you need set a value from a background thread, postValue() should be used.
        }
    }

    private fun isFetchNeeded(lastSavedAt: Date): Boolean {
        return simpleDateFormat.format(Date())
            .compareTo(
                simpleDateFormat.parse(lastSavedAt.toString())!!.toString()
            ) / 3600000 > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.saveLastSavedAt(simpleDateFormat.format(Date()).toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}