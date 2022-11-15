package wizeline.crypto.currency.ui.homeCurrency.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wizeline.crypto.currency.R
import wizeline.crypto.currency.databinding.ItemBooksBinding
import wizeline.crypto.currency.domain.model.AvailableBooksModel
import wizeline.crypto.currency.utils.NAME_CURRENCY
import wizeline.crypto.currency.utils.getDrawable

class AvailableBooksAdapter(private val onClick:(AvailableBooksModel)->Unit):ListAdapter<AvailableBooksModel, AvailableBooksAdapter.ViewHolder>(
    DelegateDiffCallBack
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_books_constrain,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataCurrency=getItem(position)
        holder.binding.apply {
            var currencyPrincipal=dataCurrency.book.split("_")
            txtCurrencyName.text=NAME_CURRENCY[currencyPrincipal[0]]
            txtMinimumPrice.text="${dataCurrency.minimumPrice} ${currencyPrincipal[1]}"
            txtMaximumPrice.text="${dataCurrency.maximumPrice} ${currencyPrincipal[1]}"
            imgMoney.setImageDrawable(getDrawable(root.context,"ic_${currencyPrincipal[0]}"))
            imgMoneyTo.setImageDrawable(getDrawable(root.context,"ic_${currencyPrincipal[1]}"))
            txtCurrencyNameTo.text=NAME_CURRENCY[currencyPrincipal[1]]
            root.setOnClickListener {
                onClick(dataCurrency)
            }
        }
    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding =  ItemBooksBinding.bind(itemView)

    }

    object DelegateDiffCallBack:DiffUtil.ItemCallback<AvailableBooksModel>(){
        override fun areItemsTheSame(oldItem: AvailableBooksModel, newItem: AvailableBooksModel): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: AvailableBooksModel, newItem: AvailableBooksModel): Boolean {
            return oldItem.book==newItem.book
        }
    }


}
