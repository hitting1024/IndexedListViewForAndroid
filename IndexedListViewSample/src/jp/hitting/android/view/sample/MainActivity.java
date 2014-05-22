package jp.hitting.android.view.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.hitting.android.view.IndexedListView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		List<String> items = createItems();
		List<String> indexList = new ArrayList<String>();
		Map<String, Integer> sectionIndexMap = new HashMap<String, Integer>();
		loadIndex(items, indexList, sectionIndexMap);

		IndexedListView listview = (IndexedListView) this
				.findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		listview.setAdapter(adapter);
		listview.setIndex(indexList, sectionIndexMap);
	}

	private static List<String> createItems() {
		final String[] array = { "apple", "ape", "animal", "android", "boy",
				"ball", "cat", "cow", "camera", "canvas", "day", "danger",
				"egg", "eat", "early", "fit", "foot", "food", "good", "git",
				"goal", "gold", "hit", "head", "item", "ice", "juice", "jump",
				"jeep", "king", "keep", "knight", "loop", "long", "link",
				"moon", "monkey", "near", "nail", "open", "orion", "pink",
				"purple", "question", "queen", "ring", "risk", "rear" };
		List<String> items = Arrays.asList(array);
		Collections.sort(items);
		return items;
	}

	private static void loadIndex(List<String> items, List<String> indexList,
			Map<String, Integer> sectionIndexMap) {
		String prevC = "";
		for (int i = 0; i < items.size(); i++) {
			String c = items.get(i).substring(0, 1);
			if (!c.equals(prevC)) {
				indexList.add(c);
				sectionIndexMap.put(c, i);
				prevC = c;
			}
		}
	}
}
