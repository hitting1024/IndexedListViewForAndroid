package jp.hitting.android.indexedlist

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Pair
import android.util.SparseBooleanArray
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.AdapterView.*

class IndexedListView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    companion object {

        private var indexTextColor: Int = Color.WHITE
        private var indexTextSize: Float = 12f
    }

    private var indexList: List<Pair<String, Int>>? = null

    private val listView: ListView
    private val indexLayout: LinearLayout

    init {

        val typedAry = context.obtainStyledAttributes(attrs, R.styleable.IndexedListView)
        indexTextColor = typedAry.getColor(R.styleable.IndexedListView_index_color, Color.WHITE)
        indexTextSize = typedAry.getDimension(R.styleable.IndexedListView_index_size, 12f)
        typedAry.recycle()

        this.listView = ListView(this.context)
        this.addView(this.listView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))

        this.indexLayout = LinearLayout(this.context)
        this.indexLayout.setPadding(10, 5, 10, 5)
        this.indexLayout.orientation = LinearLayout.VERTICAL
        this.indexLayout.setHorizontalGravity(ALIGN_PARENT_RIGHT)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        params.addRule(ALIGN_PARENT_RIGHT)
        this.indexLayout.setOnTouchListener { view, motionEvent ->
            this.moveSection(motionEvent.getX(), motionEvent.getY())
            return@setOnTouchListener true
        }
        this.addView(this.indexLayout, params)
    }

    /**
     * @param indexList the pair of (index key, jump position) list.
     */
    fun setIndex(indexList: List<Pair<String, Int>>) {
        this.indexList = indexList

        this.indexLayout.removeAllViews()
        for (index in this.indexList!!) {
            val textView = TextView(this.context)
            textView.text = index.first
            textView.gravity = Gravity.CENTER
            textView.textSize = indexTextSize
            textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
            textView.setTextColor(indexTextColor)
            textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            this.indexLayout.addView(textView)
        }
    }

    fun setIndexVisibility(visibility: Int) {
        this.indexLayout.visibility = visibility
    }

    private fun moveSection(x: Float, y: Float) {
        val size = this.indexList!!.size
        var position = (y / (this.listView.height / size)).toInt()
        if (position < 0) {
            position = 0
        } else if (position >= size) {
            position = size - 1
        }
        val sectionPosition = this.indexList!![position].second
        this.listView.setSelection(sectionPosition)
    }

    //
    // ListView
    //
    // method
    val maxScrollAmount: Int
        get() = this.listView.maxScrollAmount

    fun addHeaderView(v: View, data: Any, isSelectable: Boolean) {
        this.listView.addHeaderView(v, data, isSelectable)
    }

    fun addHeaderView(v: View) {
        this.listView.addHeaderView(v)
    }

    val headerViewsCount: Int
        get() = this.listView.headerViewsCount

    fun removeHeaderView(v: View): Boolean {
        return this.listView.removeHeaderView(v)
    }

    fun addFooterView(v: View, data: Any, isSelectable: Boolean) {
        this.listView.addFooterView(v, data, isSelectable)
    }

    fun addFooterView(v: View) {
        this.listView.addFooterView(v)
    }

    val footerViewsCount: Int
        get() = this.listView.footerViewsCount

    fun removeFooterView(v: View): Boolean {
        return this.listView.removeFooterView(v)
    }

    var adapter: ListAdapter
        get() = this.listView.adapter
        set(adapter) {
            this.listView.adapter = adapter
        }

    fun setSelection(position: Int) {
        this.listView.setSelection(position)
    }

    fun setSelectionFromTop(position: Int, y: Int) {
        this.listView.setSelectionFromTop(position, y)
    }

    fun setSelectionAfterHeaderView() {
        this.listView.setSelectionAfterHeaderView()
    }

    var itemsCanFocus: Boolean
        get() = this.listView.itemsCanFocus
        set(itemsCanFocus) {
            this.listView.itemsCanFocus = itemsCanFocus
        }

    fun setCacheColorHint(color: Int) {
        this.listView.cacheColorHint = color
    }

    var divider: Drawable
        get() = this.listView.divider
        set(divider) {
            this.listView.divider = divider
        }

    var dividerHeight: Int
        get() = this.listView.dividerHeight
        set(height) {
            this.listView.dividerHeight = height
        }

    fun setHeaderDividersEnabled(headerDividersEnabled: Boolean) {
        this.listView.setHeaderDividersEnabled(headerDividersEnabled)
    }

    fun setFooterDividersEnabled(footerDividersEnabled: Boolean) {
        this.listView.setFooterDividersEnabled(footerDividersEnabled)
    }

    var choiceMode: Int
        get() = this.listView.choiceMode
        set(choiceMode) {
            this.listView.choiceMode = choiceMode
        }

    fun performItemClick(view: View, position: Int, id: Long): Boolean {
        return this.listView.performItemClick(view, position, id)
    }

    fun setItemChecked(position: Int, value: Boolean) {
        this.listView.setItemChecked(position, value)
    }

    fun isItemChecked(position: Int): Boolean {
        return this.listView.isItemChecked(position)
    }

    val checkedItemPosition: Int
        get() = this.listView.checkedItemPosition

    val checkedItemPositions: SparseBooleanArray
        get() = this.listView.checkedItemPositions

    fun clearChoices() {
        this.listView.clearChoices()
    }

    // listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listView.onItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.listView.onItemLongClickListener = listener
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.listView.onItemSelectedListener = listener
    }

}