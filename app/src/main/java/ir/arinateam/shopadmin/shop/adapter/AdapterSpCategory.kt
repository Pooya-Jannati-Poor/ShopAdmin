package ir.arinateam.shopadmin.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.shop.model.ModelSpCategory
import ir.arinateam.shopadmin.utils.CategorySelected

class AdapterSpCategory(
    private val context: Context,
    private val modelSpCategory: List<ModelSpCategory>,
    private val categorySelected: CategorySelected
) : BaseAdapter() {
    override fun getCount(): Int = modelSpCategory.size

    override fun getItem(position: Int): Any = modelSpCategory[position]

    override fun getItemId(position: Int): Long = position.toLong()


    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.layout_sp_category, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.tvCategoryName.text = modelSpCategory[position].name

        vh.tvCategoryName.setOnClickListener {

            categorySelected.onItemSelected(modelSpCategory[position].id)

        }

        return view

    }

    private class ItemHolder(row: View) {

        val tvCategoryName: TextView = row.findViewById(R.id.text) as TextView

    }

}