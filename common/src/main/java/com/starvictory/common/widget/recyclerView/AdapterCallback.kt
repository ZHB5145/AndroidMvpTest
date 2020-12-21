package com.starvictory.common.widget.recyclerView

interface AdapterCallback<Data> {
    fun update(data: Data,viewHolder: BasisRecyclerViewAdapter.ViewHolder<Data>)
}