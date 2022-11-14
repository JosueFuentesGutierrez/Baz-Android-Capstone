package wizeline.crypto.currency.ui.informationTrading.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import wizeline.crypto.currency.R
import wizeline.crypto.currency.databinding.AsksBidsAdapterBinding
import wizeline.crypto.currency.domain.model.AsksBidsModel


class OrderBookAdapter():ListAdapter<AsksBidsModel, OrderBookAdapter.ViewHolder>(
    DelegateDiffCallBack
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_asks_bids_constrain,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=getItem(position)
        holder.binding.apply {
            txtAmount.text="${root.context.getString(R.string.amount)}\n${data.amount}"
            textPrice.text="${root.context.getString(R.string.price)}\n${data.price}"
        }
    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = AsksBidsAdapterBinding .bind(itemView)
    }

    object DelegateDiffCallBack:DiffUtil.ItemCallback<AsksBidsModel>(){
        override fun areItemsTheSame(oldItem: AsksBidsModel, newItem: AsksBidsModel): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: AsksBidsModel, newItem: AsksBidsModel): Boolean {
            return oldItem.book==newItem.book
        }
    }


}
