package com.example.demoexpandaleadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.demoexpandaleadapter.R

class ExpandableAdapter(
    private val list: MutableList<ExpandableModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ExpandableModel.TYPE_HEADER -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                )
            }
            ExpandableModel.TYPE_CONTENT -> {
                ContentViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_content, parent, false)
                )
            }
            else -> ContentViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_content, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position].getType()) {
            ExpandableModel.TYPE_HEADER -> (holder as HeaderViewHolder).bind(list[position] as HeaderModel)
            ExpandableModel.TYPE_CONTENT -> (holder as ContentViewHolder).bind(list[position] as ContentModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getType()
    }

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var header: TextView? = null
        private var content: TextView? = null
        private var arrow: ImageView? = null

        init {
            header = view.findViewById(R.id.tvHeader)
            content = view.findViewById(R.id.tvContent)
            arrow = view.findViewById(R.id.ivArrow)
            header!!.setOnClickListener {
                val model = list[adapterPosition] as HeaderModel
                model.isExpanded = !model.isExpanded
                notifyItemRangeChanged(adapterPosition, model.contentSize + 1)
            }
        }

        fun bind(item: HeaderModel) {
            header?.text = item.title
            if (item.isExpanded) {
                arrow?.setImageResource(R.drawable.ic_arrow_drop_up_24)
            }
            else arrow?.setImageResource(R.drawable.ic_arrow_drop_down_24)
        }
    }

    inner class ContentViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private var content: TextView? = null

        init {
            content = view.findViewById(R.id.tvContent)
        }

        fun bind(item: ContentModel) {
            view.isVisible = item.header.isExpanded
            view.layoutParams.height = if (item.header.isExpanded) ViewGroup.LayoutParams.WRAP_CONTENT else 0
            content?.text = item.content
        }
    }
}