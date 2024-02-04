package com.example.demoexpandaleadapter.adapter

abstract class ExpandableModel {
    abstract fun getType(): Int

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_CONTENT = 2
    }
}