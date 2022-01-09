package ir.arinateam.shopadmin.shop.adapter

import android.annotation.SuppressLint
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
import ir.arinateam.shopadmin.databinding.LayoutRecOrderBinding
import ir.arinateam.shopadmin.shop.model.ModelRecOrder
import ir.arinateam.shopadmin.utils.NumbersSeparator

class AdapterRecOrder(
    private val context: Context,
    private val lsModelRecOrder: List<ModelRecOrder>
) : RecyclerView.Adapter<AdapterRecOrder.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecOrderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(context)
        bindingAdapter = DataBindingUtil.inflate(inflater, R.layout.layout_rec_order, parent, false)
        return ItemAdapter(bindingAdapter)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {

        val model = lsModelRecOrder[position]

        Glide.with(context).load(model.img).diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter().placeholder(
                R.drawable.ic_admin_image_test
            ).into(holder.imgBook)
        holder.tvUsername.text = model.username
        holder.tvBookCount.text = "${model.orderCount} سفارش"
        holder.tvOrderDate.text = model.date

        val numbersSeparator = NumbersSeparator()

        holder.tvPrice.text = numbersSeparator.doubleToStringNoDecimal(model.price.toDouble())

        holder.itemView.setOnClickListener {


        }

    }

    override fun getItemCount(): Int = lsModelRecOrder.size

    inner class ItemAdapter(binding: LayoutRecOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgBook: ShapeableImageView = binding.imgBook
        val tvUsername: TextView = binding.tvUsername
        val tvBookCount: TextView = binding.tvBookCount
        val tvOrderDate: TextView = binding.tvOrderDate
        val tvPrice: TextView = binding.tvPrice

    }

}