package ir.arinateam.shopadmin.admin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.angads25.toggle.widget.LabeledSwitch
import com.google.android.material.switchmaterial.SwitchMaterial
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.interfaces.ChangeShopState
import ir.arinateam.shopadmin.databinding.LayoutRecShopBinding
import ir.arinateam.shopadmin.admin.model.ModelRecShop

class AdapterRecShop(
    private val context: Context,
    private val lsModelRecShop: List<ModelRecShop>,
    private val changeShopState: ChangeShopState
) :
    RecyclerView.Adapter<AdapterRecShop.ItemAdapter>() {

    private lateinit var bindingAdapter: LayoutRecShopBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val inflater = LayoutInflater.from(parent.context)
        bindingAdapter = DataBindingUtil.inflate(inflater, R.layout.layout_rec_shop, parent, false)
        return ItemAdapter(bindingAdapter)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {


        val model = lsModelRecShop[position]

        holder.tvShopName.text = model.shopName

        holder.sbShopAvailable.isOn = model.isActivated

        holder.sbShopAvailable.setOnToggledListener { _, isOn ->

            if (isOn) {

                changeShopState.enabled()

            } else {

                changeShopState.disabled()

            }

        }


    }

    override fun getItemCount(): Int = lsModelRecShop.size

    inner class ItemAdapter(binding: LayoutRecShopBinding) : RecyclerView.ViewHolder(binding.root) {

        val tvShopName: TextView = binding.tvShopName
        val sbShopAvailable: LabeledSwitch = binding.sbShopAvailable

    }


}