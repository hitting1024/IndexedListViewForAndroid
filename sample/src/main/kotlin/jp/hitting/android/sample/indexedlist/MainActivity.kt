package jp.hitting.android.sample.indexedlist

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import jp.hitting.android.view.IndexedListView
import java.util.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        val items = createItems()
        val indexList = ArrayList<String>()
        val sectionIndexMap = HashMap<String, Int>()
        loadIndex(items, indexList, sectionIndexMap)

        val listview = this.findViewById(R.id.listview) as IndexedListView
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, items)
        listview.adapter = adapter
        listview.setIndex(indexList, sectionIndexMap)
    }

    private fun createItems(): List<String> {
        val array = arrayOf("apple", "ape", "animal", "android", "boy", "ball", "cat", "cow", "camera", "canvas", "day", "danger", "egg", "eat", "early", "fit", "foot", "food", "good", "git", "goal", "gold", "hit", "head", "item", "ice", "juice", "jump", "jeep", "king", "keep", "knight", "loop", "long", "link", "moon", "monkey", "near", "nail", "open", "orion", "pink", "purple", "question", "queen", "ring", "risk", "rear")
        val items = Arrays.asList(*array)
        Collections.sort(items)
        return items
    }

    private fun loadIndex(items: List<String>, indexList: MutableList<String>,
                          sectionIndexMap: MutableMap<String, Int>) {
        var prevC = ""
        for (i in items.indices) {
            val c = items[i].substring(0, 1)
            if (c != prevC) {
                indexList.add(c)
                sectionIndexMap.put(c, i)
                prevC = c
            }
        }
    }
}
