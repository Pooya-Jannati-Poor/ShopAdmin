package ir.arinateam.shopadmin.login

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiClient.Companion.retrofit
import ir.arinateam.shopadmin.databinding.LoginStep2FragmentBinding
import ir.arinateam.shopadmin.api.ApiInterface
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

    private fun checkInputs() {

        val phoneNumber = edPhoneNumber.text.toString().trim()
        val password = edPassword.text.toString().trim()

        if (phoneNumber.length == 11 && password.length > 4) {

            Toast.makeText(requireActivity(), "با موفقیت وارد شدید", Toast.LENGTH_SHORT).show()

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

    /*private fun loadingWithUUID() {

        val loading_lottie = Loading(this)

        apiClient = ApiClient()

        val mobileModel = Build.MODEL

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.loadingWithUUID(mobileModel, uuid)

        callLoading.enqueue(object : Callback<ModelLoading> {

            override fun onResponse(call: Call<ModelLoading>, response: Response<ModelLoading>) {

                loading_lottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    val edSharedPreferences = sharedPrefreces.edit()
                    edSharedPreferences.putString("token", data.token)
                    edSharedPreferences.apply()

                    token = data.token

                    if (data.isAdmin) {
                        changeActivityToDashboardActivity()
                    } else {

                        if (data.cafeForMe) {

                            changeActivityToMainActivity(
                                1,
                                data.cafeId,
                                data.cafeName,
                                data.cafeAdmin,
                                data.cafeScore,
                                data.cafeAddress,
                                data.cafeState,
                                data.cafeDistance,
                                data.cafeIcon
                            )

                        } else {

                            changeActivityToMainActivity(
                                0,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null
                            )

                        }
                    }


                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelLoading>, t: Throwable) {

                loading_lottie.hideDialog()

                val customToast = CustomToast()

                customToast.creatToast(
                    "خطا",
                    resources.getText(R.string.error_send_data).toString(),
                    2,
                    this@SplashActivity
                )

                Thread {
                    runOnUiThread {

                        Thread.sleep(1000)
                        finish()

                    }

                }
            }

        })

    }*/

}