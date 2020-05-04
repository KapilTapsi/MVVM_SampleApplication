package apps.mithari.mvvmsampleapplication.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import apps.mithari.mvvmsampleapplication.R
import apps.mithari.mvvmsampleapplication.data.db.entities.User
import apps.mithari.mvvmsampleapplication.databinding.ActivityLoginBinding
import apps.mithari.mvvmsampleapplication.util.hide
import apps.mithari.mvvmsampleapplication.util.show
import apps.mithari.mvvmsampleapplication.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
//        the type of binding is ActivityLoginBinding because it is
//        generated from the activity_login xml layout file
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
