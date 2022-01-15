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
import ir.arinateam.shopadmin.databinding.OrdersFragmentBinding
import ir.arinateam.shopadmin.shop.adapter.AdapterRecOrder
import ir.arinateam.shopadmin.shop.model.ModelRecOrder


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

        setRecOrders()

        backToDashboard()

    }



    private fun initView() {

        imgBack = bindingFragment.imgBack
        recOrder = bindingFragment.recOrder

    }

    private lateinit var adapterRecOrder: AdapterRecOrder
    private lateinit var lsModelRecOrder: ArrayList<ModelRecOrder>

    private fun setRecOrders() {

        lsModelRecOrder = ArrayList()

        lsModelRecOrder.add(
            ModelRecOrder(
                1,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "رضا جمشیدی",
                "1400/12/20",
                4,
                "41000"
            )
        )
        lsModelRecOrder.add(
            ModelRecOrder(
                2,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                "اصغر فرهادی",
                "1400/04/12",
                1,
                "4000"
            )
        )
        lsModelRecOrder.add(
            ModelRecOrder(
                3,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                "ناشناس",
                "1399/08/1",
                2,
                "31000"
            )
        )
        lsModelRecOrder.add(
            ModelRecOrder(
                4,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "مصطفی کاظمی",
                "1399/07/28",
                5,
                "300000"
            )
        )
        lsModelRecOrder.add(
            ModelRecOrder(
                5,
                "https://www.incimages.com/uploaded_files/image/1920x1080/getty_655998316_2000149920009280219_363765.jpg",
                "مرتضی فراهانی",
                "1400/12/20",
                4,
                "41000"
            )
        )
        lsModelRecOrder.add(
            ModelRecOrder(
                6,
                "https://en.tehran.ir/Portals/0/newsfile/books/b.jpg",
                "کاظم سبزواری",
                "1400/12/20",
                2,
                "12000"
            )
        )

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