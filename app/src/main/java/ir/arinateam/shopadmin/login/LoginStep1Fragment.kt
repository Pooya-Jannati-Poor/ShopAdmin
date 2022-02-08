package ir.arinateam.shopadmin.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.AdminActivity
import ir.arinateam.shopadmin.databinding.LoginStep1FragmentBinding
import ir.arinateam.shopadmin.shop.ShopActivity


class LoginStep1Fragment : Fragment() {

    private lateinit var bindingFragment: LoginStep1FragmentBinding

    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.login_step1_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        checkLogin()

        goToLoginFragment()

        goToSignupFragment()

    }

    private fun initView() {

        btnLogin = bindingFragment.btnLogin
        btnSignup = bindingFragment.btnSignup

    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun checkLogin() {

        sharedPreferences = requireActivity().getSharedPreferences(
            "data",
            Context.MODE_PRIVATE
        )

        token = sharedPreferences.getString("token", "")!!

        Log.d("dataTest", token)

        if (token.isNotEmpty()) {

            if (sharedPreferences.getString("type", "") == "shop") {

                val intent = Intent(requireActivity(), ShopActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                requireActivity().startActivity(intent)

            } else if (sharedPreferences.getString("type", "") == "admin") {

                val intent = Intent(requireActivity(), AdminActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                requireActivity().startActivity(intent)

            }

        }


    }

    private fun goToLoginFragment() {

        btnLogin.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_loginStep1Fragment_to_loginStep2Fragment)

        }

    }


    private fun goToSignupFragment() {

        btnSignup.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_loginStep1Fragment_to_signupFragment)

        }

    }


}