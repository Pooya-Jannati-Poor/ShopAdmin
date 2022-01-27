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

    private lateinit var apiClient: ApiClient

    private fun getDashboardInfo() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.adminDashboardInfo("")

        callLoading.enqueue(object : Callback<ModelAdminDashboardInfo> {

            override fun onResponse(
                call: Call<ModelAdminDashboardInfo>,
                response: Response<ModelAdminDashboardInfo>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    Glide.with(requireActivity()).load(data.img)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter().placeholder(
                            R.drawable.ic_admin_image_test
                        ).into(imgAdmin)

                    tvAdminName.text = "Hi ${data.name}"

                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelAdminDashboardInfo>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

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

            Toast.makeText(requireActivity(), "به زودی...", Toast.LENGTH_SHORT).show()

        }

        llvBooks.setOnClickListener {

            Toast.makeText(requireActivity(), "به زودی...", Toast.LENGTH_SHORT).show()

        }

    }

}