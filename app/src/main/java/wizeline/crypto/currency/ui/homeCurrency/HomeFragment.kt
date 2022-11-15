package wizeline.crypto.currency.ui.homeCurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import wizeline.crypto.currency.databinding.FragmentHomeBinding
import wizeline.crypto.currency.ui.homeCurrency.adapters.AvailableBooksAdapter
import wizeline.crypto.currency.ui.homeCurrency.viewModel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    private var avilableAdapter = AvailableBooksAdapter { data ->
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInformationTradingFragment2(book=data.book))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rcvCurrencies.apply {
                setHasFixedSize(true)
                adapter= avilableAdapter
                layoutManager= LinearLayoutManager(requireActivity())
            }
        }

        homeViewModel.getAvailableBook("")

        homeViewModel.state.observe(requireActivity()) { uiState ->
            avilableAdapter.submitList(uiState.book)

            if (!uiState.error.isNullOrEmpty())
                Snackbar.make(binding.root, "${uiState.error}", Snackbar.LENGTH_SHORT).show()
        }
    }
}