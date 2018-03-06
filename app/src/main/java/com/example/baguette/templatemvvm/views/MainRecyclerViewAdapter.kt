package com.example.baguette.templatemvvm.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.baguette.templatemvvm.R
import com.example.baguette.templatemvvm.databinding.EvenNumberRecyclerViewCellBinding
import com.example.baguette.templatemvvm.databinding.OddNumberRecyclerViewCellBinding
import com.example.baguette.templatemvvm.model.EvenNumberListItem
import com.example.baguette.templatemvvm.model.OddNumberListItem
import timber.log.Timber


class MainRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private object ViewType {
        const val EVEN_NUMBER = R.id.even_number
        const val ODD_NUMBER = R.id.odd_number
    }

    private interface ViewItem {
        val recyclerViewType: Int
    }

    private class EvenNumberViewItem(val item: EvenNumberListItem): ViewItem {
        override val recyclerViewType: Int = ViewType.EVEN_NUMBER
    }

    private class OddNumberViewItem(val item: OddNumberListItem): ViewItem {
        override val recyclerViewType: Int = ViewType.ODD_NUMBER
    }

    private class EvenNumberViewHolder(
            val binding: EvenNumberRecyclerViewCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewItem: EvenNumberViewItem) {
            binding.executePendingBindings()
        }
    }

    private class OddNumberViewHolder(
            val binding: OddNumberRecyclerViewCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewItem: OddNumberViewItem) {
            binding.executePendingBindings()
        }
    }

    private var items: List<ViewItem> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return items[position].recyclerViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        return when(viewType) {
            ViewType.EVEN_NUMBER ->
                    EvenNumberViewHolder(EvenNumberRecyclerViewCellBinding.inflate(inflater, parent, false))
            ViewType.ODD_NUMBER ->
                    OddNumberViewHolder(OddNumberRecyclerViewCellBinding.inflate(inflater, parent, false))
            else ->
                    throw RuntimeException("unknownViewType $viewType")
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        Timber.d("hoge view Type$holder")
        when(holder) {
            is EvenNumberViewHolder -> {
                val item = items[position] as? EvenNumberViewItem ?: return
                holder.bind(item)
                Timber.d("hoge even")
            }
            is OddNumberViewHolder -> {
                val item = items[position] as? OddNumberViewItem ?: return
                Timber.d("hoge odd")
                Timber.d("hoge odd $item")
                holder.bind(item)
            }
        }
    }

    fun upDateItems(
            evenNumberListItem: EvenNumberListItem,
            oddNumberListItem: OddNumberListItem
    ) {
        val items = mutableListOf<ViewItem>()
            items.add(EvenNumberViewItem(evenNumberListItem))
            items.add(OddNumberViewItem(oddNumberListItem))

        Timber.d("hoge item $items")

        this.items = items
        notifyDataSetChanged()
    }
}