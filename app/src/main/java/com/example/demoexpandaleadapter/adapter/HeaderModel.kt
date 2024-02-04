package com.example.demoexpandaleadapter.adapter

class HeaderModel(
    var title: String,
): ExpandableModel() {
    var isExpanded: Boolean = false
    var contentSize: Int = 0

    override fun getType(): Int {
        return TYPE_HEADER
    }
}
