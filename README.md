# ViewDragFrameLayout
拖拽布局...

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="me.msile.train.MainActivity">

    <TextView
        android:id="@+id/tv_click"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:background="#ff0"
        android:text="可点击"
        android:gravity="center"
        android:textColor="#000" />

    <me.msile.train.ViewDragFrameLayout
        android:id="@+id/fl_drag"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/tv_drag"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:background="#ff0"
            android:text="可拖动"
            android:gravity="center"
            android:textColor="#000" />
    </me.msile.train.ViewDragFrameLayout>

</FrameLayout>

![Image](https://github.com/msilemsile/ViewDragFrameLayout/blob/master/demo.gif) 
