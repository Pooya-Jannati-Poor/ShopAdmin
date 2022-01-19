package ir.arinateam.shopadmin.shop

import android.os.Bundle
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
import ir.arinateam.shopadmin.shop.model.ModelRecOrderBase
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

        setRecProducts()

        addNewBook()

        backToDashboard()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack
        llhAddNewBook = bindingFragment.llhAddNewBook
        recProduct = bindingFragment.recProduct

    }

    private lateinit var apiClient: ApiClient

    private fun getProductList() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.productList("", 1)

        callLoading.enqueue(object : Callback<ModelRecProductBase> {

            override fun onResponse(
                call: Call<ModelRecProductBase>,
                response: Response<ModelRecProductBase>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    lsModelRecProduct.addAll(data.products)

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
    private lateinit var lsModelRecProduct: ArrayList<ModelRecProduct>

    private fun setRecProducts() {

        lsModelRecProduct = ArrayList()

        lsModelRecProduct.add(
            ModelRecProduct(
                1,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                1,
                "ارباب حلقه ها",
                "جی ار ار تاکین",
                4,
                "جنگل",
                1,
                25000,
                152,
                1998,
                "9865-545644-56151-564",
                0,
                "کتاب بسیار خوب و جالبی است."
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                2,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                2,
                "دو برج",
                "جی ار ار تاکین",
                6,
                "کویر",
                2,
                40000,
                320,
                2012,
                "9865-545644-56151-564",
                10,
                "کتاب بسیار خوب و جالبی است."
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                3,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                3,
                "هری پاتر و سنگ جادو",
                "اصغر فرهادی",
                1,
                "جنگل",
                4,
                25000,
                152,
                1998,
                "9865-545644-56151-564",
                0,
                "کتاب بسیار خوب و جالبی است."
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                4,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                4,
                "بابا لنگ دراز",
                "جی ار ار تاکین",
                2,
                "جنگل",
                3,
                25000,
                152,
                1998,
                "9865-545644-56151-564",
                0,
                "کتاب بسیار خوب و جالبی است."
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                5,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                5,
                "قم بهتر است یا نیویورک؟:/",
                "یه نویسنده",
                0,
                "جنگل",
                1,
                25000,
                152,
                1998,
                "9865-545644-56151-564",
                5,
                "کتاب بسیار خوب و جالبی است."
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                6,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                6,
                "اثر مرکب",
                "دان هاردی",
                4,
                "جنگل",
                1,
                25000,
                152,
                1998,
                "9865-545644-56151-564",
                0,
                "کتاب بسیار خوب و جالبی است."
            )
        )

        adapterRecProduct = AdapterRecProduct(requireActivity(), lsModelRecProduct)

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