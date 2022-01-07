package ir.arinateam.shopadmin.shop.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.LayoutRecProductBinding
import ir.arinateam.shopadmin.shop.model.ModelRecProduct

class AdapterRecProduct(
    private val context: Context,
    private val lsModelRecProduct: ArrayList<ModelRecProduct>
) : RecyclerView.Adapter<AdapterRecProduct.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecProductBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(parent.context)
        bindingAdapter =
            DataBindingUtil.inflate(inflater, R.layout.layout_rec_product, parent, false)
        return ItemAdapter(bindingAdapter)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {

        val model = lsModelRecProduct[position]

        Glide.with(context).load(model.img).diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter().placeholder(
                R.drawable.ic_admin_image_test
            ).into(holder.imgBook)
        holder.tvBookName.text = model.bookName
        holder.tvBookWriter.text = model.bookWriter
        if (model.isAvailable) {

            holder.tvBookAvailable.text = context.resources.getString(R.string.book_available)

        } else {

            holder.tvBookAvailable.text = context.resources.getString(R.string.book_unavailable)

        }

        holder.llhEdit.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("productId", model.id)

            Navigation.findNavController(it)
                .navigate(R.id.action_productsFragment_to_addBookFragment, bundle)

        }

        holder.imgDelete.setOnClickListener {

            lsModelRecProduct.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)

        }


    }

    override fun getItemCount(): Int = lsModelRecProduct.size

    inner class ItemAdapter(binding: LayoutRecProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgBook: ShapeableImageView = binding.imgBook
        val tvBookName: TextView = binding.tvBookName
        val tvBookWriter: TextView = binding.tvBookWriter
        val tvBookAvailable: TextView = binding.tvBookAvailable
        val llhEdit: LinearLayout = binding.llhEdit
        val imgDelete: ImageView = binding.imgDelete

    }


}