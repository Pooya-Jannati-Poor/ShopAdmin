package ir.arinateam.shopadmin.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.SignupFragmentBinding
import ir.arinateam.shopadmin.login.model.ModelSignup
import ir.arinateam.shopadmin.shop.ShopActivity
import ir.arinateam.shopadmin.utils.Loading
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupFragment : Fragment() {

    private lateinit var bindingFragment: SignupFragmentBinding

    private lateinit var btnSignup: Button
    private lateinit var edPhoneNumber: TextInputEditText
    private lateinit var edPassword: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.signup_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        signup()

    }

    private fun initView() {

        btnSignup = bindingFragment.btnSignup
        edPhoneNumber = bindingFragment.edPhoneNumber
        edPassword = bindingFragment.edPassword

    }


    private fun signup() {

        btnSignup.setOnClickListener {

            checkInputs()

        }

    }

    private lateinit var phoneNumber: String
    private lateinit var password: String

    private fun checkInputs() {

        phoneNumber = edPhoneNumber.text.toString().trim()
        password = edPassword.text.toString().trim()

        if (phoneNumber.length == 11 && password.length > 4) {

            signupApi()

        } else {

            Toast.makeText(
                requireActivity(),
                "لطفا تمامی ورودی ها را وارد نمایید",
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    private lateinit var loading: Loading
    private lateinit var apiClient: ApiClient

    private fun signupApi() {

        loading = Loading(requireActivity())

        apiClient = ApiClient()

        val mobileModel = Build.MODEL

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)
        val signupApi = apiInterface.signup(phoneNumber, password, "SHOP", mobileModel)

        signupApi.enqueue(object : Callback<ModelSignup> {
            override fun onResponse(
                call: Call<ModelSignup>,
                response: Response<ModelSignup>
            ) {
                loading.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()

                    if (data != null) {

                        val intent = Intent(requireActivity(), ShopActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        requireActivity().startActivity(intent)

                    }

                    Toast.makeText(requireActivity(), "با موفقیت ثبت نام کردید", Toast.LENGTH_SHORT)
                        .show()


                } else {

                    Toast.makeText(requireActivity(), "لطفا مجددا سعی نمایید", Toast.LENGTH_SHORT)
                        .show()

                }

            }

            override fun onFailure(
                call: Call<ModelSignup>,
                t: Throwable
            ) {
                loading.hideDialog()

                Toast.makeText(requireActivity(), "لطفا مجددا سعی نمایید", Toast.LENGTH_SHORT)
                    .show()

            }

        })

    }


}