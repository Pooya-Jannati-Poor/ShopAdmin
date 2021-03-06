package ir.arinateam.shopadmin.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.model.ModelAdminDashboardInfo
import ir.arinateam.shopadmin.admin.model.ModelAdminShopsInfoBase
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.AdminDashboardFragmentBinding
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdminDashboardFragment : Fragment() {

    private lateinit var bindingFragment: AdminDashboardFragmentBinding


    private lateinit var imgAdmin: ShapeableImageView
    private lateinit var tvAdminName: TextView
    private lateinit var llvShops: LinearLayout
    private lateinit var llvUsers: LinearLayout
    private lateinit var llvSells: LinearLayout
    private lateinit var llvBooks: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.admin_dashboard_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        openShopsFragment()

        openSellsFragment()

        comingSoon()

    }

    private fun initView() {

        imgAdmin = bindingFragment.imgAdmin
        tvAdminName = bindingFragment.tvAdminName
        llvShops = bindingFragment.llvShops
        llvUsers = bindingFragment.llvUsers
        llvSells = bindingFragment.llvSells
        llvBooks = bindingFragment.llvBooks

    }

    private fun openShopsFragment() {

        llvShops.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_adminDashboardFragment_to_shopsFragment)

        }

    }

    private fun openSellsFragment() {

        llvSells.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_adminDashboardFragment_to_sellsFragment)

        }

    }

    private fun comingSoon() {

        llvUsers.setOnClickListener {

            Toast.makeText(requireActivity(), "???? ????????...", Toast.LENGTH_SHORT).show()

        }

        llvBooks.setOnClickListener {

            Toast.makeText(requireActivity(), "???? ????????...", Toast.LENGTH_SHORT).show()

        }

    }

}