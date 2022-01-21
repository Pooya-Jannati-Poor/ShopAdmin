package ir.arinateam.shopadmin.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.adapter.AdapterRecShop
import ir.arinateam.shopadmin.admin.interfaces.ChangeShopState
import ir.arinateam.shopadmin.admin.model.ModelAdminShopsInfoBase
import ir.arinateam.shopadmin.databinding.ShopsFragmentBinding
import ir.arinateam.shopadmin.admin.model.ModelRecShop
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.shop.model.ModelSpCategory
import ir.arinateam.shopadmin.shop.model.ModelSpCategoryBase
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShopsFragment : Fragment(), ChangeShopState {

    private lateinit var bindingFragment: ShopsFragmentBinding


    private lateinit var tvShopsCount: TextView
    private lateinit var recShops: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.shops_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        getShopList()

        setRecShop()

    }

    private fun initView() {

        tvShopsCount = bindingFragment.tvShopsCount
        recShops = bindingFragment.recShops

    }

    private lateinit var apiClient: ApiClient

    private fun getShopList() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.getShopsList("", 1)

        callLoading.enqueue(object : Callback<ModelAdminShopsInfoBase> {

            override fun onResponse(
                call: Call<ModelAdminShopsInfoBase>,
                response: Response<ModelAdminShopsInfoBase>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    var available = 0

                    data.shops.forEach {
                        if (it.isActivated) {
                            available++
                        }
                    }

                    tvShopsCount.text = available.toString().plus(" فروشگاه فعال")

                    modelRecShop = ArrayList()

                    modelRecShop.addAll(data.shops)

                    setRecShop()


                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelAdminShopsInfoBase>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

    private lateinit var modelRecShop: ArrayList<ModelRecShop>
    private lateinit var adapter: AdapterRecShop
    private var enabledCounts = 0

    private fun setRecShop() {

        modelRecShop = ArrayList()

        modelRecShop.add(ModelRecShop(1, "فروشگاه 1", true))
        modelRecShop.add(ModelRecShop(2, "فروشگاه 2", true))
        modelRecShop.add(ModelRecShop(3, "فروشگاه 3", true))
        modelRecShop.add(ModelRecShop(4, "فروشگاه 4", true))
        modelRecShop.add(ModelRecShop(5, "فروشگاه 5", false))
        modelRecShop.add(ModelRecShop(6, "فروشگاه 6", true))
        modelRecShop.add(ModelRecShop(7, "فروشگاه 7", false))
        modelRecShop.add(ModelRecShop(8, "فروشگاه 8", true))
        modelRecShop.add(ModelRecShop(8, "فروشگاه 9", true))
        modelRecShop.add(ModelRecShop(8, "فروشگاه 10", true))
        modelRecShop.add(ModelRecShop(8, "فروشگاه 11", true))
        modelRecShop.add(ModelRecShop(8, "فروشگاه 12", true))
        modelRecShop.add(ModelRecShop(8, "فروشگاه 13", true))

        adapter = AdapterRecShop(requireActivity(), modelRecShop, this)

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recShops.layoutManager = linearLayoutManager
        recShops.adapter = adapter



        modelRecShop.forEach {

            if (it.isActivated) {
                enabledCounts++
            }

        }

        tvShopsCount.text = "$enabledCounts".plus(" فروشگاه فعال")


    }

    override fun enabled() {

        enabledCounts++
        tvShopsCount.text = "$enabledCounts".plus(" فروشگاه فعال")

    }

    override fun disabled() {

        enabledCounts--
        tvShopsCount.text = "$enabledCounts".plus(" فروشگاه فعال")

    }


}