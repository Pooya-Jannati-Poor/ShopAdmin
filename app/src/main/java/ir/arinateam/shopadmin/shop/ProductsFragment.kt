package ir.arinateam.shopadmin.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.ProductsFragmentBinding
import ir.arinateam.shopadmin.shop.adapter.AdapterRecProduct
import ir.arinateam.shopadmin.shop.model.ModelRecProduct


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

    private lateinit var adapterRecProduct: AdapterRecProduct
    private lateinit var lsModelRecProduct: ArrayList<ModelRecProduct>

    private fun setRecProducts() {

        lsModelRecProduct = ArrayList()

        lsModelRecProduct.add(
            ModelRecProduct(
                1,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "ارباب حلقه ها",
                "جی ار ار تاکین",
                true
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                2,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                "دو برج",
                "جی ار ار تاکین",
                false
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                3,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                "هری پاتر و سنگ جادو",
                "اصغر فرهادی",
                true
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                4,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "بابا لنگ دراز",
                "جی ار ار تاکین",
                true
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                5,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                "قم بهتر است یا نیویورک؟:/",
                "یه نویسنده",
                false
            )
        )
        lsModelRecProduct.add(
            ModelRecProduct(
                6,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "اثر مرکب",
                "دان هاردی",
                true
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