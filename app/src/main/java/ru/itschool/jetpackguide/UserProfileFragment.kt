package ru.itschool.jetpackguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import dagger.Module
import dagger.hilt.InstallIn
import ru.itschool.jetpackguide.databinding.UserProfileLayoutBinding

//@AndroidEntryPoint
@Module
@InstallIn(ViewModelProviderFactory::class)
class UserProfileFragment : Fragment() {

    private var _binding: UserProfileLayoutBinding? = null
    private val binding get() = _binding!!

    /*private fun defaultBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("uid", "1")
        return bundle
    }*/

    private fun savedStateHandle() : SavedStateHandle {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle.set("uid", "1")
        return savedStateHandle
    }

    private val viewModel : UserProfileViewModel
        by viewModels {
            ViewModelProviderFactory(savedStateHandle(), requireContext())
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserProfileLayoutBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.user.observe(viewLifecycleOwner) {
            binding.idTV.text = it.id
            binding.nameTV.text = it.name
            binding.ageTV.text = it.age.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}