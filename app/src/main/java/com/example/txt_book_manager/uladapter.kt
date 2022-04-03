package com.example.txt_book_manager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ULAdapter(context: Context, arrayOfData: ArrayList<EmpModelClass>)  : BaseAdapter() {
    var arrayOfData : ArrayList<EmpModelClass>
    private val mInflator: LayoutInflater

    init {
        this.arrayOfData = arrayOfData
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): Any {
        return arrayOfData[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayOfData.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.row_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.isbn.text = ""+arrayOfData[position].isbn
        vh.title.text = ""+arrayOfData[position].title
        vh.author.text = ""+arrayOfData[position].author
        vh.course.text = ""+arrayOfData[position].course
        return view
    }

    private class ListRowHolder(row: View?) {
        public val isbn: TextView
        public val title : TextView
        public val author :TextView
        public val course :TextView

        init {
            this.isbn = row?.findViewById(R.id.isbn) as TextView
            this.title = row?.findViewById(R.id.title) as TextView
            this.author = row?.findViewById(R.id.author) as TextView
            this.course = row?.findViewById(R.id.course) as TextView

        }
    }
}