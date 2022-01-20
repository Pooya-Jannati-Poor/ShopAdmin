package ir.arinateam.shopadmin.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.ProfileFragmentBinding
import ir.arinateam.shopadmin.shop.model.ModelGetShopInfo
import ir.arinateam.shopadmin.shop.model.ModelSpCategoryBase
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var bindingFragment: ProfileFragmentBinding

    private lateinit var imgBack: ImageView
    private lateinit var imgProfile: ShapeableImageView
    private lateinit var tvShopName: TextView
    private lateinit var edUsername: TextInputEditText
    private lateinit var edShopName: TextInputEditText
    private lateinit var edPhoneNumber: TextInputEditText
    private lateinit var edAddress: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var flLogout: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        saveEdit()

        backToDashboard()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack
        imgProfile = bindingFragment.imgProfile
        tvShopName = bindingFragment.tvShopName
        edUsername = bindingFragment.edUsername
        edShopName = bindingFragment.edShopName
        edPhoneNumber = bindingFragment.edPhoneNumber
        edAddress = bindingFragment.edAddress
        btnSave = bindingFragment.btnSave
        flLogout = bindingFragment.flLogout

    }

    private lateinit var apiClient: ApiClient

    private fun getShopInfo() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.shopInfo("", 1)

        callLoading.enqueue(object : Callback<ModelGetShopInfo> {

            override fun onResponse(
                call: Call<ModelGetShopInfo>,
                response: Response<ModelGetShopInfo>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    if (data.shopImage != null) {

                        Glide.with(requireActivity()).load(data.shopImage).diskCacheStrategy(
                            DiskCacheStrategy.ALL
                        )
                            .fitCenter().placeholder(
                                R.drawable.ic_admin_image_test
                            ).into(imgProfile)

                    }

                    if (data.shopName != null) {

                        edShopName.setText(data.shopName)
                        tvShopName.text = data.shopName

                    }

                    if (data.username != null) {

                        edUsername.setText(data.username)

                    }

                    if (data.address != null) {

                        edAddress.setText(data.address)

                    }

                    if (data.phoneNumber != null) {

                        edPhoneNumber.setText(data.phoneNumber)

                    }

                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelGetShopInfo>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

    private fun saveEdit() {

        btnSave.setOnClickListener {

            validateInputs()

        }

    }

    private lateinit var username: String
    private lateinit var shopName: String
    private lateinit var phoneNumber: String
    private lateinit var address: String

    private fun validateInputs() {

        username = edUsername.text.toString()
        shopName = edShopName.text.toString()
        phoneNumber = edPhoneNumber.text.toString()
        address = edAddress.text.toString()

        if (username != "" && shopName != "" && phoneNumber != "" && phoneNumber.length == 11 && address != "") {

            Toast.makeText(requireActivity(), "اطلاعات شما با موفقیت ذخیره شد", Toast.LENGTH_SHORT)
                .show()

            edUsername.setText("")
            edShopName.setText("")
            edPhoneNumber.setText("")
            edAddress.setText("")

        } else {

            Toast.makeText(requireActivity(), "تمامی اطلاعات را وارد نمایید!", Toast.LENGTH_SHORT)
                .show()

        }

    }

    private fun backToDashboard() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}