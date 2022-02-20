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
import ir.arinateam.shopadmin.databinding.OrderDetailFragmentBinding
import ir.arinateam.shopadmin.shop.adapter.AdapterRecOrderDetail
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetail
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetail2
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetailBase
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailFragment : Fragment() {

    private lateinit var bindingFragment: OrderDetailFragmentBinding

    private lateinit var imgBack: ImageView
    private lateinit var recOrderDetail: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.order_detail_fragment, container, false)
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

        getOrderDetail()

        backToDashboard()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack
        recOrderDetail = bindingFragment.recOrderDetail

    }

    private lateinit var apiClient: ApiClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun getOrderDetail() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading =
            apiInterface.orderDetail("Bearer $token", requireArguments().getInt("orderId"))

        callLoading.enqueue(object : Callback<ModelRecOrderDetailBase> {

            override fun onResponse(
                call: Call<ModelRecOrderDetailBase>,
                response: Response<ModelRecOrderDetailBase>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    lsModelRecOrder = ArrayList()

                    var username = ""

                    username = data.order.user.name ?: "کاربر ${data.order.user.id}"

                    data.orderDetails.data.forEach {

                        val bookName = it.product.name
                        val amount = it.amount
                        val price = it.bookPrice
                        val image = it.product.image
                        val state = data.order.stateName
                        val date = data.order.createdJal.substring(0, 10)

                        lsModelRecOrder.add(
                            ModelRecOrderDetail2(
                                username,
                                bookName,
                                date,
                                amount,
                                price,
                                state,
                                image
                            )
                        )

                    }

                    setRecOrderDetail()

                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelRecOrderDetailBase>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }


    private lateinit var adapterRecOrder: AdapterRecOrderDetail
    private lateinit var lsModelRecOrder: ArrayList<ModelRecOrderDetail2>

    private fun setRecOrderDetail() {

        adapterRecOrder = AdapterRecOrderDetail(requireActivity(), lsModelRecOrder)

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recOrderDetail.layoutManager = linearLayoutManager
        recOrderDetail.adapter = adapterRecOrder

    }


    private fun backToDashboard() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}