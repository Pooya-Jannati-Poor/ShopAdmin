package ir.arinateam.shopadmin.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.ProfileFragmentBinding


class ProfileFragment : Fragment() {

    private lateinit var bindingFragment: ProfileFragmentBinding

    private lateinit var imgBack: ImageView
    private lateinit var imgProfile: ShapeableImageView
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
        edUsername = bindingFragment.edUsername
        edShopName = bindingFragment.edShopName
        edPhoneNumber = bindingFragment.edPhoneNumber
        edAddress = bindingFragment.edAddress
        btnSave = bindingFragment.btnSave
        flLogout = bindingFragment.flLogout

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