package ir.arinateam.shopadmin.shop.adapter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.LayoutRecProductBinding
import ir.arinateam.shopadmin.shop.model.ModelRecProduct
import ir.arinateam.shopadmin.shop.model.ModelRecProductInfo
import ir.arinateam.shopadmin.utils.Loading
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterRecProduct(
    private val context: Context,
    private val lsModelRecProductInfo: ArrayList<ModelRecProduct>
) : RecyclerView.Adapter<AdapterRecProduct.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecProductBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(parent.context)
        bindingAdapter =
            DataBindingUtil.inflate(inflater, R.layout.layout_rec_product, parent, false)
        return ItemAdapter(bindingAdapter)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {

        val model = lsModelRecProductInfo[position]

        Glide.with(context).load("http://applicationfortests.ir/" + model.img).diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter().placeholder(
                R.drawable.ic_admin_image_test
            ).into(holder.imgBook)
        holder.tvBookName.text = model.bookName
        holder.tvBookWriter.text = model.bookWriter

        holder.tvBookAvailable.text =
            context.resources.getString(R.string.book_available_count)
                .plus(" " + model.availableCount + " عدد")


        holder.llhEdit.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("productId", model.id)
            bundle.putString("productImage", model.img)

            Navigation.findNavController(it)
                .navigate(R.id.action_productsFragment_to_addBookFragment, bundle)

        }

        holder.imgDelete.setOnClickListener {

            removeProduct(model.id, holder.adapterPosition)

        }


    }

    private lateinit var apiClient: ApiClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun removeProduct(productId: Int, adapterPosition: Int) {

        val loadingLottie = Loading(context)

        apiClient = ApiClient()

        sharedPreferences = context.getSharedPreferences(
            "data",
            Context.MODE_PRIVATE
        )

        token = sharedPreferences.getString("token", "")!!

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.removeProduct("Bearer $token", productId)

        callLoading.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 204) {

                    lsModelRecProductInfo.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                } else {

                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    context,
                    context.resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

    override fun getItemCount(): Int = lsModelRecProductInfo.size

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