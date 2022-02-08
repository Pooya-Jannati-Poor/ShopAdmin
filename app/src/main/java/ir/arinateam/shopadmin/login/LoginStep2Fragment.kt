package ir.arinateam.shopadmin.login

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.AdminActivity
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiClient.Companion.retrofit
import ir.arinateam.shopadmin.databinding.LoginStep2FragmentBinding
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.login.model.ModelLogin
import ir.arinateam.shopadmin.shop.ShopActivity
import ir.arinateam.shopadmin.utils.Loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginStep2Fragment : Fragment() {

    private lateinit var bindingFragment: LoginStep2FragmentBinding

    private lateinit var btnLogin: Button
    private lateinit var edPhoneNumber: TextInputEditText
    private lateinit var edPassword: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.login_step2_fragment, container, false
        )
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        login()

    }

    private fun initView() {

        btnLogin = bindingFragment.btnLogin
        edPhoneNumber = bindingFragment.edPhoneNumber
        edPassword = bindingFragment.edPassword

    }

    private fun login() {

        btnLogin.setOnClickListener {

            checkInputs()

        }

    }

    private lateinit var phoneNumber: String
    private lateinit var password: String

    private fun checkInputs() {

        phoneNumber = edPhoneNumber.text.toString().trim()
        password = edPassword.text.toString().trim()

        if (phoneNumber.length == 11 && password.isNotEmpty()) {

            loginApi()

        } else {

            Toast.makeText(
                requireActivity(),
                "لطفا تمامی ورودی ها را وارد نمایید",
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    private lateinit var apiClient: ApiClient
    private lateinit var loading: Loading
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun loginApi() {

        loading = Loading(requireActivity())

        apiClient = ApiClient()

        val mobileModel = Build.MODEL

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.login(phoneNumber, password, mobileModel)

        callLoading.enqueue(object : Callback<ModelLogin> {

            override fun onResponse(call: Call<ModelLogin>, response: Response<ModelLogin>) {

                loading.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    Log.d("dataTest", data.type)
                    Log.d("dataTest", data.typeName)

                    sharedPreferences = requireActivity().getSharedPreferences("data", MODE_PRIVATE)

                    val edSharedPreferences = sharedPreferences.edit()
                    edSharedPreferences.putString("token", data.token)
                    edSharedPreferences.putString("type", data.type.lowercase())
                    edSharedPreferences.apply()

                    token = data.token

                    Toast.makeText(requireActivity(), "با موفقیت وارد شدید", Toast.LENGTH_SHORT)
                        .show()


                    if (data.type == "SHOP") {

                        val intent = Intent(requireActivity(), ShopActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        requireActivity().startActivity(intent)

                    } else if (data.type == "ADMIN") {

                        val intent = Intent(requireActivity(), AdminActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        requireActivity().startActivity(intent)

                    }


                } else {

                    Toast.makeText(
                        requireActivity(),
                        "رمز عبور یا نام کاربری خود را اشتباه وارد کرده اید",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }

            }

            override fun onFailure(call: Call<ModelLogin>, t: Throwable) {

                loading.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

}