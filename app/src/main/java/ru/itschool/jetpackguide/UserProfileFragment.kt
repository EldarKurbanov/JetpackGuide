package ru.itschool.jetpackguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import ru.itschool.jetpackguide.databinding.UserProfileLayoutBinding

//@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private var _binding: UserProfileLayoutBinding? = null
    private val binding get() = _binding!!

    private fun defaultBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("uid", "1")
        return bundle
    }

    private val viewModel : UserProfileViewModel
        by viewModels(
            factoryProducer = {SavedStateViewModelFactory(activity?.application, this, defaultBundle())}
    )

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
            binding.nameTV.text = it.name
            binding.ageTV.text = it.age.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}