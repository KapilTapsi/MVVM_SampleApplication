package apps.mithari.mvvmsampleapplication.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import apps.mithari.mvvmsampleapplication.R
import apps.mithari.mvvmsampleapplication.databinding.ProfileFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
//    we need to import import org.kodein.di.android.x.kodein for above line to work

    private lateinit var viewModel: ProfileViewModel
    private val factory: ProfileViewModelFactory by instance<ProfileViewModelFactory>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProfileFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
//        this returns the binding as layout/view
    }
}