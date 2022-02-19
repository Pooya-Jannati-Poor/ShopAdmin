package ir.arinateam.shopadmin.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.LayoutRecOrderDetailBinding
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetail2
import ir.arinateam.shopadmin.utils.NumbersSeparator

class AdapterRecOrderDetail(
    private val context: Context,
    private val lsModelRecOrder: List<ModelRecOrderDetail2>
) : RecyclerView.Adapter<AdapterRecOrderDetail.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecOrderDetailBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(context)
        bindingAdapter =
            DataBindingUtil.inflate(inflater, R.layout.layout_rec_order_detail, parent, false)
        return ItemAdapter(bindingAdapter)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {

        val model = lsModelRecOrder[position]

        Glide.with(context).load("http://applicationfortests.ir/" + model.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter().placeholder(
                R.drawable.ic_admin_image_test
            ).into(holder.imgBook)
        holder.tvBookName.text = model.bookName
        holder.tvBookCount.text = model.amount.toString()
        holder.tvUsername.text = model.username
        holder.tvOrderDate.text = model.date

        val numbersSeparator = NumbersSeparator()

        holder.tvBookPrice.text =
            numbersSeparator.doubleToStringNoDecimal(model.price.toDouble())

        holder.tvOrderState.text = model.delivered

    }

    override fun getItemCount(): Int = lsModelRecOrder.size

    inner class ItemAdapter(binding: LayoutRecOrderDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgBook: ShapeableImageView = binding.imgBook
        val tvBookName: TextView = binding.tvBookName
        val tvBookCount: TextView = binding.tvBookCount
        val tvUsername: TextView = binding.tvUsername
        val tvBookPrice: TextView = binding.tvBookPrice
        val tvOrderDate: TextView = binding.tvOrderDate
        val tvOrderState: TextView = binding.tvOrderState

    }

}