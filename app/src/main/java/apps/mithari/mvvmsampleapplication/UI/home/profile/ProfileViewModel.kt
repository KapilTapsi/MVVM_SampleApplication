package apps.mithari.mvvmsampleapplication.ui.home.profile

import androidx.lifecycle.ViewModel
import apps.mithari.mvvmsampleapplication.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {
    val user = repository.getUser()
}
