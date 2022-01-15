package ir.arinateam.shopadmin

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
import ir.arinateam.shopadmin.databinding.LoginStep2FragmentBinding


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
        bindingFragment = DataBindingUtil.inflate(inflater, R.layout.login_step2_fragment, container, false)
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

    private fun login(){

        btnLogin.setOnClickListener {

            checkInputs()

        }

    }

    private fun checkInputs() {

        val phoneNumber = edPhoneNumber.text.toString().trim()
        val password = edPassword.text.toString().trim()

        if (phoneNumber.length == 11 && password.length > 4){

            Toast.makeText(requireActivity(), "با موفقیت وارد شدید", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(requireActivity(), "لطفا تمامی ورودی ها را وارد نمایید", Toast.LENGTH_SHORT).show()

        }

    }

}