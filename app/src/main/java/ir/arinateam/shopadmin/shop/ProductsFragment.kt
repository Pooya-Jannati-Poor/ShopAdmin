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
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.ProductsFragmentBinding
import ir.arinateam.shopadmin.shop.adapter.AdapterRecProduct
import ir.arinateam.shopadmin.shop.model.ModelRecProduct
import ir.arinateam.shopadmin.shop.model.ModelRecProductBase
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsFragment : Fragment() {

    private lateinit var bindingFragment: ProductsFragmentBinding

    private lateinit var imgBack: ImageView
    private lateinit var llhAddNewBook: LinearLayout
    private lateinit var recProduct: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.products_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        getProductList()

        addNewBook()

        backToDashboard()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack
        llhAddNewBook = bindingFragment.llhAddNewBook
        recProduct = bindingFragment.recProduct

    }

    private lateinit var apiClient: ApiClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun getProductList() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        sharedPreferences = requireActivity().getSharedPreferences(
            "data",
            Context.MODE_PRIVATE
        )

        token = sharedPreferences.getString("token", "")!!

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.productList("Bearer $token")

        callLoading.enqueue(object : Callback<ModelRecProductBase> {

            override fun onResponse(
                call: Call<ModelRecProductBase>,
                response: Response<ModelRecProductBase>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    lsModelRecProductInfo = ArrayList()

                    lsModelRecProductInfo.addAll(data.productInfo)

                    setRecProducts()

                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelRecProductBase>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

    private lateinit var adapterRecProduct: AdapterRecProduct
    private lateinit var lsModelRecProductInfo: ArrayList<ModelRecProduct>

    private fun setRecProducts() {

        adapterRecProduct = AdapterRecProduct(requireActivity(), lsModelRecProductInfo)

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recProduct.layoutManager = linearLayoutManager
        recProduct.adapter = adapterRecProduct

    }

    private fun addNewBook() {

        llhAddNewBook.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_productsFragment_to_addBookFragment)

        }

    }

    private fun backToDashboard() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}