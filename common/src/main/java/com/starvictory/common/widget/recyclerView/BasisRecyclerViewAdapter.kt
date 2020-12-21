package com.starvictory.common.widget.recyclerView

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.starvictory.common.R
import java.util.*
import kotlin.collections.ArrayList

abstract class BasisRecyclerViewAdapter<Data> public constructor(
    dataList: Collection<Data>,
    basisAdapterListener: BasisAdapterListener<Data>
) :
    RecyclerView.Adapter<BasisRecyclerViewAdapter.ViewHolder<Data>>(), AdapterCallback<Data>,
    View.OnClickListener, View.OnLongClickListener {
    private val mDataList: ArrayList<Data> = ArrayList()
    private var basisAdapterListener: BasisAdapterListener<Data>? = null

    /**
     * 规定viewtype必须为子项目的layoutid
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasisRecyclerViewAdapter.ViewHolder<Data> {
        val inflater = LayoutInflater.from(parent.context)
        //规定viewtype必须为子项目的layoutid
        val root = inflater.inflate(viewType, parent, false)
        val holder = onCreateViewHolder(root, viewType)
        root.setOnClickListener(this)
        root.setOnLongClickListener(this)
        //设置view的tag为ViewHolder 进行双向绑定
        root.setTag(R.id.tag_recycler_holder)
        holder.callback = this
        return holder
    }

    protected abstract fun onCreateViewHolder(root: View, viewType: Int): ViewHolder<Data>;

    override fun getItemCount(): Int {
        return mDataList.size
    }

    init {
        this.mDataList.addAll(dataList)
        this.basisAdapterListener = basisAdapterListener
    }

    override fun onClick(v: View?) {
        val viewHolder = v?.getTag(R.id.tag_recycler_holder) as ViewHolder<Data>
        val pos = viewHolder.adapterPosition
        basisAdapterListener?.onItemClick(viewHolder,mDataList[pos])

    }

    override fun onLongClick(v: View?): Boolean {
        val viewHolder = v?.getTag(R.id.tag_recycler_holder) as ViewHolder<Data>
        val pos = viewHolder.adapterPosition
        if (basisAdapterListener !=null){
            basisAdapterListener?.onItemLongClick(viewHolder,mDataList[pos])
            return true
        }


        return false
    }

    public abstract class ViewHolder<Data>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        protected var mData: Data? = null
        public var callback: AdapterCallback<Data>? = null

        /**
         * 用于绑定数据的触发
         * @return data 绑定的数据
         */
        fun bind(data: Data) {
            this.mData = data
            onBind(data)
        }

        /**
         * 用于绑定的数据必须复写
         */
        protected abstract fun onBind(data: Data)
        public fun updateData(data: Data) {
            callback?.update(data, this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<Data>, position: Int) {
        val data = mDataList[position]
        holder.bind(data)
    }

    fun add(data: Data) {
        mDataList.add(data)
//        notifyDataSetChanged()
        notifyItemInserted(mDataList.size - 1)
    }

    fun add(vararg dataList: Data) {
        if (dataList.isNotEmpty()) {
            val startPos = mDataList.size;
            Collections.addAll(mDataList, *dataList)
            notifyItemRangeInserted(startPos, dataList.size)
        }
    }

    fun add(dataList: Collection<Data>) {
        if (dataList.isNotEmpty()) {
            val startPos = mDataList.size;
            mDataList.addAll(dataList)
            notifyItemRangeInserted(startPos, dataList.size)
        }
    }

    public fun clear() {
        mDataList.clear()
        notifyDataSetChanged()
    }

    public fun repalce(dataList: Collection<Data>) {
        mDataList.clear()
        if (dataList.isNotEmpty()) {
            mDataList.addAll(dataList)
            notifyDataSetChanged()
        }
    }


    /**
     * 得到布局类型 ，返回当前xml文件ID用于创建viewHolder
     */
    override fun getItemViewType(position: Int): Int {
        return getItemViewType(position, mDataList[position])
    }

    interface BasisAdapterListener<Data> {
        fun onItemClick(holder: ViewHolder<Data>, data: Data)
        fun onItemLongClick(holder: ViewHolder<Data>, data: Data)
    }

    @LayoutRes
    protected abstract fun getItemViewType(position: Int, data: Data): Int

}