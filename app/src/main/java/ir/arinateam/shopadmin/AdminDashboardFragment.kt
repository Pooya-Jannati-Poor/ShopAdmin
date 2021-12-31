package ir.arinateam.shopadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import ir.arinateam.shopadmin.databinding.AdminDashboardFragmentBinding


class AdminDashboardFragment : Fragment() {

    private lateinit var bindingFragment: AdminDashboardFragmentBinding


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

        llvShops = bindingFragment.llvShops
        llvUsers = bindingFragment.llvUsers
        llvSells = bindingFragment.llvSells
        llvBooks = bindingFragment.llvBooks

    }

    private fun openShopsFragment() {

        llvShops.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_adminDashboardFragment_to_shopsFragment)

        }

    }

    private fun openSellsFragment() {

        llvSells.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_adminDashboardFragment_to_sellsFragment)

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