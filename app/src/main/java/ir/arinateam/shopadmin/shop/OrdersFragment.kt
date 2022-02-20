package ir.arinateam.shopadmin.shop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.OrdersFragmentBinding
import ir.arinateam.shopadmin.shop.adapter.AdapterRecOrder
import ir.arinateam.shopadmin.shop.model.ModelGetOrdersBase
import ir.arinateam.shopadmin.shop.model.ModelRecOrder
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrdersFragment : Fragment() {

    private lateinit var bindingFragment: OrdersFragmentBinding

    private lateinit var imgBack: ImageView
    private lateinit var recOrder: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.orders_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        sharedPreferences = requireActivity().getSharedPreferences(
            "data",
            Context.MODE_PRIVATE
        )

        token = sharedPreferences.getString("token", "")!!

        getOrderList()

        backToDashboard()

    }


    private fun initView() {

        imgBack = bindingFragment.imgBack
        recOrder = bindingFragment.recOrder

    }

    private lateinit var apiClient: ApiClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun getOrderList() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callOrderList = apiInterface.orderList("Bearer $token", true, true, true)

        callOrderList.enqueue(object : Callback<ModelGetOrdersBase> {

            override fun onResponse(
                call: Call<ModelGetOrdersBase>,
                response: Response<ModelGetOrdersBase>
            ) {

                loadingLottie.hideDialog()

                Log.d("dataTest", response.code().toString())
                Log.d("dataTest", response.body().toString())

                if (response.code() == 200) {

                    val data = response.body()!!

                    lsModelRecOrder = ArrayList()

                    data.ordersBase.data.forEach {

                        lsModelRecOrder.add(
                            ModelRecOrder(
                                it.id,
                                "it.details[0].product.Image",
                                "username",
                                it.createdJal.substring(0, 10),
                                it.total_amount,
                                it.total_price,
                                it.stateName
                            )
                        )

                    }

                    setRecOrders()

                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelGetOrdersBase>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

    private lateinit var adapterRecOrder: AdapterRecOrder
    private lateinit var lsModelRecOrder: ArrayList<ModelRecOrder>

    private fun setRecOrders() {

        adapterRecOrder = AdapterRecOrder(requireActivity(), lsModelRecOrder)

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recOrder.layoutManager = linearLayoutManager
        recOrder.adapter = adapterRecOrder

    }

    private fun backToDashboard() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}