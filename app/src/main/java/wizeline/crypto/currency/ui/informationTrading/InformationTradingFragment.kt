package wizeline.crypto.currency.ui.informationTrading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import wizeline.crypto.currency.R
import wizeline.crypto.currency.databinding.ActivityTradingInformationConstrainBinding
import wizeline.crypto.currency.databinding.FragmentHomeBinding
import wizeline.crypto.currency.databinding.FragmentInformationTradingBinding
import wizeline.crypto.currency.ui.informationTrading.adapters.OrderBookAdapter
import wizeline.crypto.currency.ui.informationTrading.viewModel.TradingInformationViewModel
import wizeline.crypto.currency.utils.getDrawable

@AndroidEntryPoint
class InformationTradingFragment : Fragment() {

    private lateinit var binding: FragmentInformationTradingBinding
    private val tradingViewModel by viewModels<TradingInformationViewModel>()

    var book: String = ""
    private var asksAdapter = OrderBookAdapter()
    private var bidsAdapter = OrderBookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationTradingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            book = arguments?.getString("book").toString()
        }


        binding.apply {

            rcvAsks.apply {
                adapter = asksAdapter
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            }

            rcvBids.apply {
                adapter = bidsAdapter
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            }

            val currencyPrincipal = book.split("_")
            imgCurrency.setImageDrawable(
                getDrawable(
                    requireActivity(), "ic_${currencyPrincipal[0]}"
                )
            )
            imgCurrencyTo.setImageDrawable(
                getDrawable(
                    requireActivity(), "ic_${currencyPrincipal[1]}"
                )
            )


        }

        tradingViewModel.state.observe(requireActivity()) { uiState ->
            binding.apply {
                txtVolume.text = uiState.information?.volume
                txtHighn.text = uiState.information?.high
                txtLast.text = uiState.information?.last
                txtLow.text = uiState.information?.low
                txtVwap.text = uiState.information?.wap
                txtAsk.text = uiState.information?.ask
                txtBid.text = uiState.information?.bid
                txtCreated.text = uiState.information?.createdAt
            }

            asksAdapter.submitList(uiState.orderBook?.asks)
            bidsAdapter.submitList(uiState.orderBook?.bids)

            if (uiState.error.isNotEmpty())
                Snackbar.make(binding.root, uiState.error, Snackbar.LENGTH_SHORT).show()
        }
        tradingViewModel.getTradingInformationRXJ(book)
        tradingViewModel.getOrderBook(book)
    }
}