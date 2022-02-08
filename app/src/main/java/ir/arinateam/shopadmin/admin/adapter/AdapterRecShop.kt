package ir.arinateam.shopadmin.admin.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.angads25.toggle.widget.LabeledSwitch
import com.google.android.material.switchmaterial.SwitchMaterial
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.interfaces.ChangeShopState
import ir.arinateam.shopadmin.admin.model.ModelAdminShopsInfoBase
import ir.arinateam.shopadmin.databinding.LayoutRecShopBinding
import ir.arinateam.shopadmin.admin.model.ModelRecShop
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.utils.Loading
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterRecShop(
    private val context: Context,
    private val lsModelRecShop: List<ModelRecShop>,
    private val changeShopState: ChangeShopState
) :
    RecyclerView.Adapter<AdapterRecShop.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecShopBinding

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(parent.context)
        bindingAdapter = DataBindingUtil.inflate(inflater, R.layout.layout_rec_shop, parent, false)
        return ItemAdapter(bindingAdapter)
    }


    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {


        val model = lsModelRecShop[position]

        holder.tvShopName.text = model.shopName

        if (model.isActivated == 1) {

            holder.sbShopAvailable.isOn = true

        } else {

            holder.sbShopAvailable.isOn = false

        }

        holder.sbShopAvailable.setOnToggledListener { _, isOn ->

            if (isOn) {

                val loadingLottie = Loading(context)

                val apiClient = ApiClient()

                sharedPreferences = context.getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )

                token = sharedPreferences.getString("token", "")!!

                val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

                val callLoading = apiInterface.changeShopState("Bearer $token", model.id)

                callLoading.enqueue(object : Callback<ResponseBody> {

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        loadingLottie.hideDialog()

                        if (response.code() == 204) {

                            changeShopState.enabled()


                        } else {

                            Toast.makeText(
                                context,
                                context.getText(R.string.error_receive_data).toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                        loadingLottie.hideDialog()

                        Toast.makeText(
                            context,
                            context.getText(R.string.error_send_data).toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                })

            } else {

                val loadingLottie = Loading(context)

                sharedPreferences = context.getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )

                token = sharedPreferences.getString("token", "")!!

                val apiClient = ApiClient()

                val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

                val callLoading = apiInterface.changeShopState("Bearer $token", model.id)

                callLoading.enqueue(object : Callback<ResponseBody> {

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        loadingLottie.hideDialog()

                        if (response.code() == 204) {

                            changeShopState.disabled()

                        } else {

                            Toast.makeText(
                                context,
                                context.getText(R.string.error_receive_data).toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                        loadingLottie.hideDialog()

                        Toast.makeText(
                            context,
                            context.getText(R.string.error_send_data).toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                })


            }

        }


    }

    override fun getItemCount(): Int = lsModelRecShop.size

    inner class ItemAdapter(binding: LayoutRecShopBinding) : RecyclerView.ViewHolder(binding.root) {

        val tvShopName: TextView = binding.tvShopName
        val sbShopAvailable: LabeledSwitch = binding.sbShopAvailable

    }


}