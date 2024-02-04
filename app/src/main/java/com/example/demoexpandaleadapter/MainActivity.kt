package com.example.demoexpandaleadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.demoexpandaleadapter.adapter.ContentModel
import com.example.demoexpandaleadapter.adapter.ExpandableAdapter
import com.example.demoexpandaleadapter.adapter.ExpandableModel
import com.example.demoexpandaleadapter.adapter.HeaderModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = mutableMapOf<HeaderModel, List<ContentModel>>()
        val header1 = HeaderModel(
            "Header 1"
        )
        items.put(
            header1,
            mutableListOf<ContentModel>().apply {
                add(
                    ContentModel(header1, "item 1")
                )
                add(
                    ContentModel(header1, "item 2")
                )
                add(
                    ContentModel(header1,"item 3")
                )
            }
        )
        val header2 = HeaderModel(
            "Header 2"
        )
        items.put(
            header2,
            mutableListOf<ContentModel>().apply {
                add(
                    ContentModel(header2,"item 1")
                )
                add(
                    ContentModel(header2,"item 2")
                )
                add(
                    ContentModel(header2,"item 3")
                )
            }
        )
        val listItems = mutableListOf<ExpandableModel>()
        for (key in items.keys) {
            key.contentSize = items.getValue(key).size
            listItems.add(key)
            for (value in items.getValue(key)) {
                listItems.add(value)
            }
        }
        val mRecyclerview: RecyclerView = findViewById(R.id.mRecyclerView)
        val adapter = ExpandableAdapter(listItems)
        mRecyclerview.adapter = adapter
    }
}