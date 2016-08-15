IndexedListViewForAndroid
=========================

IndexedListView is a ListView with Index like iOS.

![Screen Shot](https://raw.githubusercontent.com/hitting1024/IndexedListViewForAndroid/master/imgs/ss.png)


## How To Get Started

### Installation with Gradle

```gradle
repositories {
    maven {
        url 'https://hitting1024.github.io/IndexedListViewForAndroid/repository'
    }
}

dependencies {
    compile 'jp.hitting.android:indexed-listview:0.0.1'
}
```

### Manual Installation

* Download source code.
* Add IndexedListView.kt and attrs.xml to your project.


## Requirements

Android 2.2.x or higher.


## Usage

### Set up

Define a layout of jp.hitting.android.indexedlist.IndexedListView in layout.xml.

IndexedListView has the following attributes.

* index_color: the color of index labels (default White)
* index_size: the size of index labels (default 12sp)

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">

    <jp.hitting.android.indexedlist.IndexedListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:index_color="#0000ff"
        app:index_size="8sp" />

</RelativeLayout>
```

Set the pair (index label, its position) list

```java
// ListView has "apple", "ape", "animal", "android", "boy", "ball", "cat", "cow", ...
List<android.util.Pair<String, Integer>> indexList = new ArrayList<>();
indexList.add(new Pair<String, Integer>("a", 0));
indexList.add(new Pair<String, Integer>("b", 4));
indexList.add(new Pair<String, Integer>("c", 6));
  ...
IndexedListView listView = (IndexedListView)this.findViewById(R.id.listView);
listView.setIndex(indexList);
```

## Example

See the sample module.



## Credits

IndexedListView was created by [IOKA Masakazu](http://www.hitting.jp).



## License

The MIT License (MIT)

Copyright (c) 2014 IOKA

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
