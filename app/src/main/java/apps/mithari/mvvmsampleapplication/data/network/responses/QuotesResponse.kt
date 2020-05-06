package apps.mithari.mvvmsampleapplication.data.network.responses

import apps.mithari.mvvmsampleapplication.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)