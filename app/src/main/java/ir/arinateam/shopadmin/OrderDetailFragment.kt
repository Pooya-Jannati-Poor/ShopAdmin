package ir.arinateam.shopadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import ir.arinateam.shopadmin.databinding.OrderDetailFragmentBinding

class OrderDetailFragment : Fragment() {

    private lateinit var bindingFragment: OrderDetailFragmentBinding

    private lateinit var imgBack: ImageView

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

        backToDashboard()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack

    }


    private fun backToDashboard() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}