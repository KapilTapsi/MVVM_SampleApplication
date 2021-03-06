package apps.mithari.mvvmsampleapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import apps.mithari.mvvmsampleapplication.R
import apps.mithari.mvvmsampleapplication.data.db.entities.User
import apps.mithari.mvvmsampleapplication.databinding.ActivityLoginBinding
import apps.mithari.mvvmsampleapplication.ui.home.HomeActivity
import apps.mithari.mvvmsampleapplication.util.hide
import apps.mithari.mvvmsampleapplication.util.show
import apps.mithari.mvvmsampleapplication.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
//    you need to import import org.kodein.di.android.kodein in this activity otherwise there would be an error

    private val factory: AuthViewModelFactory by instance<AuthViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        in order to pass this repository to the viewmodel we need viewmodel factory.
//        ViewModel factory can send constructor to the required viewmodel

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
//        the type of binding is ActivityLoginBinding because it is
//        generated from the activity_login xml layout file

        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
//        we passed reference to this class to the authlistener

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                this will make the home activity to be treated as new activity,
//                that means whenever user press back button one should not go
//                again to auth screen or login screen

                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
