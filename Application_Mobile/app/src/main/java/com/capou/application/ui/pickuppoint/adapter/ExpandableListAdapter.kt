package com.capou.application.ui.pickuppoint.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.capou.application.databinding.ListGroupBinding
import com.capou.application.databinding.ListItemsBinding
import com.capou.application.helper.FonctionHelper

class ExpandableListAdapter(
    private val titleList: List<String?>?,
    private val dataList: HashMap<String?, List<String?>> ?
) :BaseExpandableListAdapter() {

    private lateinit var groupBinding: ListGroupBinding
    private lateinit var itemBinding: ListItemsBinding

    override fun getChild(listPosition: Int, expandedListPosition: Int): String? {
        return this.dataList?.get(this.titleList?.get(listPosition))!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup
    ): View {
        var convertView = view
        val holder: ItemViewHolder
        if (convertView == null) {
            itemBinding = ListItemsBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
            convertView = itemBinding.root
            holder = ItemViewHolder()
            holder.label = itemBinding.expandedListItem
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemViewHolder
        }
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        val text = FonctionHelper().helpterText(expandedListText)
        holder.label!!.text = text
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList?.get(this.titleList?.get(listPosition))!!.size
    }

    override fun getGroup(listPosition: Int): String? {
        return this.titleList?.get(listPosition)
    }

    override fun getGroupCount(): Int {
        var test = 0
        if(this.titleList?.size !=null){
            test = this.titleList.size
        }
        return test
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        view: View?,
        parent: ViewGroup
    ): View {
        var convertView = view
        val holder: GroupViewHolder
        if (convertView == null) {
            groupBinding = ListGroupBinding.inflate( LayoutInflater.from(parent.context),
                parent,
                false)
            convertView = groupBinding.root
            holder = GroupViewHolder()
            holder.label = groupBinding.listTitle
            convertView.tag = holder
        } else {
            holder = convertView.tag as GroupViewHolder
        }
        val listTitle = getGroup(listPosition) as String
        holder.label!!.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    inner class ItemViewHolder {
        internal var label: TextView? = null
    }

    inner class GroupViewHolder {
        internal var label: TextView? = null
    }

}