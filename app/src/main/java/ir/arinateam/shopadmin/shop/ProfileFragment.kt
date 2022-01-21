package ir.arinateam.shopadmin.shop

import android.app.Activity
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import ir.arinateam.imagepicker.ImagePicker
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.ProfileFragmentBinding
import ir.arinateam.shopadmin.shop.model.ModelGetShopInfo
import ir.arinateam.shopadmin.utils.Loading
import ir.arinateam.shopadmin.utils.PrepareImageForUpload
import okhttp3.MultipartBody
import okhttp3.ResponseBody
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

        getShopInfo()

        saveEdit()

        getImage()

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


    private lateinit var callLoading: Call<ResponseBody>

    private fun validateInputs() {

        username = edUsername.text.toString()
        shopName = edShopName.text.toString()
        phoneNumber = edPhoneNumber.text.toString()
        address = edAddress.text.toString()

        if (username != "" && shopName != "" && phoneNumber != "" && phoneNumber.length == 11 && address != "") {

            val loadingLottie = Loading(requireActivity())

            apiClient = ApiClient()

            val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)


            if (imageMultiPartBody != null) {

                callLoading = apiInterface.editShopInfoWithImage(
                    "", 1,
                    imageMultiPartBody,
                    shopName,
                    username,
                    phoneNumber,
                    address
                )

            } else {

                callLoading = apiInterface.editShopInfoWithoutImage(
                    "", 1,
                    shopName,
                    username,
                    phoneNumber,
                    address
                )

            }


            callLoading.enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {

                    loadingLottie.hideDialog()

                    if (response.code() == 200) {

                        Toast.makeText(
                            requireActivity(),
                            "اطلاعات شما با موفقیت ذخیره شد",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        edUsername.setText("")
                        edShopName.setText("")
                        edPhoneNumber.setText("")
                        edAddress.setText("")

                    } else {

                        Toast.makeText(
                            requireActivity(),
                            resources.getText(R.string.error_receive_data).toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    loadingLottie.hideDialog()

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_send_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            })

        } else {

            Toast.makeText(requireActivity(), "تمامی اطلاعات را وارد نمایید!", Toast.LENGTH_SHORT)
                .show()

        }

    }

    private fun getImage() {

        imgProfile.setOnClickListener {

            ImagePicker.with(this)
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    700,
                    800
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }

        }

    }

    private lateinit var imageMultiPartBody: MultipartBody.Part

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                val bitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().contentResolver,
                    fileUri
                )

                imgProfile.setImageURI(fileUri)

                val prepare = PrepareImageForUpload()
                imageMultiPartBody = prepare.buildImageBodyPart(requireActivity(), "book", bitmap)

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    private fun backToDashboard() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}