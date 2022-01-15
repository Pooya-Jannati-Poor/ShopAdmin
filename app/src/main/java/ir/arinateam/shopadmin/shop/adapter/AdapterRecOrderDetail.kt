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
import ir.arinateam.shopadmin.databinding.LayoutRecOrderDetailBinding
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetail
import ir.arinateam.shopadmin.utils.NumbersSeparator

class AdapterRecOrderDetail(
    private val context: Context,
    private val lsModelRecOrder: List<ModelRecOrderDetail>
) : RecyclerView.Adapter<AdapterRecOrderDetail.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecOrderDetailBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(context)
        bindingAdapter = DataBindingUtil.inflate(inflater, R.layout.layout_rec_order_detail, parent, false)
        return ItemAdapter(bindingAdapter)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {

        val model = lsModelRecOrder[position]

        Glide.with(context).load(model.img).diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter().placeholder(
                R.drawable.ic_admin_image_test
            ).into(holder.imgBook)
        holder.tvBookName.text = model.bookName
        holder.tvBookCount.text = model.bookCount.toString()
        holder.tvBookSeller.text = model.bookSeller

        val numbersSeparator = NumbersSeparator()

        holder.tvBookPrice.text =
            numbersSeparator.doubleToStringNoDecimal(model.bookPrice.toDouble())

        if (model.orderState) {

            holder.tvOrderState.text = context.getText(R.string.payment_successful)

        } else {

            holder.tvOrderState.text = context.getText(R.string.payment_failed)
            holder.tvOrderState.setTextColor(context.resources.getColor(R.color.red))

        }

    }

    override fun getItemCount(): Int = lsModelRecOrder.size

    inner class ItemAdapter(binding: LayoutRecOrderDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgBook: ShapeableImageView = binding.imgBook
        val tvBookName: TextView = binding.tvBookName
        val tvBookCount: TextView = binding.tvBookCount
        val tvBookSeller: TextView = binding.tvBookSeller
        val tvBookPrice: TextView = binding.tvBookPrice
        val tvOrderState: TextView = binding.tvOrderState

    }

}