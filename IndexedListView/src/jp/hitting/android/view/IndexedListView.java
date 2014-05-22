package jp.hitting.android.view;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class IndexedListView extends RelativeLayout {

	private static int indexTextColor;
	private static float indexTextSize;

	private List<String> indexList;
	private Map<String, Integer> sectionIndexMap;

	private Context context;

	private ListView listview;
	private LinearLayout indexLayout;

	public IndexedListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;

		TypedArray typedAry = context.obtainStyledAttributes(attrs,
				R.styleable.IndexedListView);
		indexTextColor = typedAry.getColor(
				R.styleable.IndexedListView_index_color, Color.WHITE);
		indexTextSize = typedAry.getDimension(
				R.styleable.IndexedListView_index_size, 12);
		typedAry.recycle();

		this.listview = new ListView(this.context);
		this.addView(this.listview, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT));

		this.indexLayout = new LinearLayout(this.context);
		this.indexLayout.setPadding(10, 5, 10, 5);
		this.indexLayout.setOrientation(LinearLayout.VERTICAL);
		this.indexLayout.setHorizontalGravity(ALIGN_PARENT_RIGHT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		params.addRule(ALIGN_PARENT_RIGHT);
		this.indexLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				IndexedListView.this.moveSection(event.getX(), event.getY());
				return true;
			}
		});
		this.addView(this.indexLayout, params);

	}

	public void setIndex(List<String> indexList,
			Map<String, Integer> sectionIndexMap) {
		this.indexList = indexList;
		this.sectionIndexMap = sectionIndexMap;

		this.indexLayout.removeAllViews();
		for (String indexStr : this.indexList) {
			TextView textView = new TextView(this.context);
			textView.setText(indexStr);
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(indexTextSize);
			textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			textView.setTextColor(indexTextColor);
			textView.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT, 1));
			this.indexLayout.addView(textView);
		}
	}

	public void setIndexVisibility(int visibility) {
		this.indexLayout.setVisibility(visibility);
	}

	private void moveSection(float x, float y) {
		int position = (int) (y / (this.listview.getHeight() / this.indexList
				.size()));
		if (position < 0) {
			position = 0;
		} else if (position >= this.indexList.size()) {
			position = this.indexList.size() - 1;
		}
		int sectionPosition = this.sectionIndexMap.get(this.indexList
				.get(position));
		this.listview.setSelection(sectionPosition);
	}

	//
	public int getMaxScrollAmount() {
		return this.listview.getMaxScrollAmount();
	}

	public void addHeaderView(View v, Object data, boolean isSelectable) {
		this.listview.addHeaderView(v, data, isSelectable);
	}

	public void addHeaderView(View v) {
		this.listview.addHeaderView(v);
	}

	public int getHeaderViewsCount() {
		return this.listview.getHeaderViewsCount();
	}

	public boolean removeHeaderView(View v) {
		return this.listview.removeHeaderView(v);
	}

	public void addFooterView(View v, Object data, boolean isSelectable) {
		this.listview.addFooterView(v, data, isSelectable);
	}

	public void addFooterView(View v) {
		this.listview.addFooterView(v);
	}

	public int getFooterViewsCount() {
		return this.listview.getFooterViewsCount();
	}

	public boolean removeFooterView(View v) {
		return this.listview.removeFooterView(v);
	}

	public ListAdapter getAdapter() {
		return this.listview.getAdapter();
	}

	public void setAdapter(ListAdapter adapter) {
		this.listview.setAdapter(adapter);
	}

	public void setSelection(int position) {
		this.listview.setSelection(position);
	}

	public void setSelectionFromTop(int position, int y) {
		this.listview.setSelectionFromTop(position, y);
	}

	public void setSelectionAfterHeaderView() {
		this.listview.setSelectionAfterHeaderView();
	}

	public void setItemsCanFocus(boolean itemsCanFocus) {
		this.listview.setItemsCanFocus(itemsCanFocus);
	}

	public boolean getItemsCanFocus() {
		return this.listview.getItemsCanFocus();
	}

	public void setCacheColorHint(int color) {
		this.listview.setCacheColorHint(color);
	}

	public Drawable getDivider() {
		return this.listview.getDivider();
	}

	public void setDivider(Drawable divider) {
		this.listview.setDivider(divider);
	}

	public int getDividerHeight() {
		return this.listview.getDividerHeight();
	}

	public void setDividerHeight(int height) {
		this.listview.setDividerHeight(height);
	}

	public void setHeaderDividersEnabled(boolean headerDividersEnabled) {
		this.listview.setHeaderDividersEnabled(headerDividersEnabled);
	}

	public void setFooterDividersEnabled(boolean footerDividersEnabled) {
		this.listview.setFooterDividersEnabled(footerDividersEnabled);
	}

	public int getChoiceMode() {
		return this.listview.getChoiceMode();
	}

	public void setChoiceMode(int choiceMode) {
		this.listview.setChoiceMode(choiceMode);
	}

	public boolean performItemClick(View view, int position, long id) {
		return this.listview.performItemClick(view, position, id);
	}

	public void setItemChecked(int position, boolean value) {
		this.listview.setItemChecked(position, value);
	}

	public boolean isItemChecked(int position) {
		return this.listview.isItemChecked(position);
	}

	public int getCheckedItemPosition() {
		return this.listview.getCheckedItemPosition();
	}

	public SparseBooleanArray getCheckedItemPositions() {
		return this.listview.getCheckedItemPositions();
	}

	public void clearChoices() {
		this.listview.clearChoices();
	}

	//
	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listview.setOnItemClickListener(listener);
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		this.listview.setOnItemLongClickListener(listener);
	}

	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		this.listview.setOnItemSelectedListener(listener);
	}

}
