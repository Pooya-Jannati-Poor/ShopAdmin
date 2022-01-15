package ir.arinateam.shopadmin.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.OrderDetailFragmentBinding
import ir.arinateam.shopadmin.shop.adapter.AdapterRecOrder
import ir.arinateam.shopadmin.shop.adapter.AdapterRecOrderDetail
import ir.arinateam.shopadmin.shop.model.ModelRecOrder
import ir.arinateam.shopadmin.shop.model.ModelRecOrderDetail

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

        setRecOrderDetail()

        backToDashboard()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack
        recOrderDetail = bindingFragment.recOrderDetail

    }


    private lateinit var adapterRecOrder: AdapterRecOrderDetail
    private lateinit var lsModelRecOrder: ArrayList<ModelRecOrderDetail>

    private fun setRecOrderDetail() {

        lsModelRecOrder = ArrayList()

        lsModelRecOrder.add(
            ModelRecOrderDetail(
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "دو برج",
                2,
                140000,
                "مسعودی",
                true
            )
        )
        lsModelRecOrder.add(
            ModelRecOrderDetail(
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "سه برج",
                2,
                140000,
                "مسعودی",
                true
            )
        )
        lsModelRecOrder.add(
            ModelRecOrderDetail(
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "چهار برج",
                2,
                140000,
                "مسعودی",
                true
            )
        )
        lsModelRecOrder.add(
            ModelRecOrderDetail(
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "دو برج",
                2,
                140000,
                "رضایی",
                true
            )
        )
        lsModelRecOrder.add(
            ModelRecOrderDetail(
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "دو برج",
                2,
                140000,
                "ادینه",
                false
            )
        )
        lsModelRecOrder.add(
            ModelRecOrderDetail(
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "دو برج",
                2,
                140000,
                "ادینه",
                false
            )
        )

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