package apps.mithari.mvvmsampleapplication

import android.app.Application
import apps.mithari.mvvmsampleapplication.data.db.AppDatabase
import apps.mithari.mvvmsampleapplication.data.network.MyApi
import apps.mithari.mvvmsampleapplication.data.network.NetworkConnectionInterceptor
import apps.mithari.mvvmsampleapplication.data.preferences.PreferenceProvider
import apps.mithari.mvvmsampleapplication.data.repositories.QuotesRepository
import apps.mithari.mvvmsampleapplication.data.repositories.UserRepository
import apps.mithari.mvvmsampleapplication.ui.auth.AuthViewModelFactory
import apps.mithari.mvvmsampleapplication.ui.home.profile.ProfileViewModelFactory
import apps.mithari.mvvmsampleapplication.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

// this is an application class and it needs to be notified to android manifest as well
class MVVMApplication() : Application(), KodeinAware {
    //    this class is an application class. Application class are those classes which are instantiated
//    before anything else in the app. Here we will instantiate all other classes
//    which we might need to instantiate in app itslef. This is part of dependency injection
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

//        now we are instantiating our classes one by one here.
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
//      we created singleton of interceptor and passed instance as parameter. this instance parameter is providing context to interceptor

        bind() from singleton { MyApi(instance()) }
//        here instance means passing interceptor which we defined above

        bind() from singleton { AppDatabase(instance()) }
//        here instance means myApi instance which we created above

        bind() from singleton { UserRepository(instance(), instance()) }

        bind() from singleton { PreferenceProvider(instance()) }
//        here instances are myApi and database instances

        bind() from provider { AuthViewModelFactory(instance()) }
//         here instance means repository. also we didnot want singleton of it.

        bind() from provider { ProfileViewModelFactory(instance()) }

        bind() from singleton {
            QuotesRepository(instance(), instance(), instance())
        }

        bind() from provider { QuotesViewModelFactory(instance()) }
    }

}