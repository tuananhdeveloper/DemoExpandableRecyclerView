package com.example.demoexpandaleadapter.adapter

class ContentModel(
    var header: HeaderModel,
    var content: String
): ExpandableModel() {

    override fun getType(): Int {
        return TYPE_CONTENT
    }
}
