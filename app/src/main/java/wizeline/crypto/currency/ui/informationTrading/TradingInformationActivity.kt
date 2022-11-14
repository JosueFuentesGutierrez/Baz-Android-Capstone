package wizeline.crypto.currency.ui.informationTrading

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import wizeline.crypto.currency.databinding.ActivityTradingInformationConstrainBinding
import wizeline.crypto.currency.ui.informationTrading.adapters.OrderBookAdapter
import wizeline.crypto.currency.ui.informationTrading.viewModel.TradingInformationViewModel
import wizeline.crypto.currency.utils.getDrawable

@AndroidEntryPoint
class TradingInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTradingInformationConstrainBinding
    private val tradingViewModel by viewModels<TradingInformationViewModel>()

    var book: String = ""
    private var asksAdapter = OrderBookAdapter()
    private var bidsAdapter = OrderBookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        book = intent.getStringExtra("book") ?: ""

        binding = ActivityTradingInformationConstrainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.apply {

           rcvAsks.apply {
                adapter = asksAdapter
                layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            }

           rcvBids.apply {
                adapter = bidsAdapter
                layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            }

            val currencyPrincipal = book.split("_")
            imgcurrency.setImageDrawable(
                getDrawable(
                    applicationContext, "ic_${currencyPrincipal[0]}"
                )
            )
            imgcurrento.setImageDrawable(
                getDrawable(
                    applicationContext, "ic_${currencyPrincipal[1]}"
                )
            )



        }

        tradingViewModel.state.observe(this) { uiState ->
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



        tradingViewModel.getTradingInformation(book)
        tradingViewModel.getOrderBook(book)
    }
}