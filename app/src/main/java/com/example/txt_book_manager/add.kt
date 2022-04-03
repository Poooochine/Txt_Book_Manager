package com.example.txt_book_manager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Add : AppCompatActivity() {

    private var helper = DatabaseHandler(this)
    var isAdd: Boolean = false
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_add)

        val isbn: EditText = findViewById(R.id.isbn)
        val title : EditText = findViewById(R.id.title)
        val author: EditText = findViewById(R.id.author)
        val course: EditText = findViewById(R.id.course)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        isAdd = intent.getBooleanExtra("ADD", true)
        id = intent.getIntExtra("ID", 0)

        if (!isAdd) {
            val book_listing = helper.getaddRecord("" + id)
            isbn.setText("" + book_listing.isbn)
            title.setText("" + book_listing.title)
            author.setText("" + book_listing.author)
            course.setText("" + book_listing.course)
            btnDelete.visibility = View.VISIBLE
        }

        addData()
    }

    private fun validate(): Boolean {

        val isbn: EditText = findViewById(R.id.isbn)
        val title : EditText = findViewById(R.id.title)
        val author: EditText = findViewById(R.id.author)
        val course: EditText = findViewById(R.id.course)

        when {
            isbn.text.isEmpty() -> {
                Toast.makeText(this@Add, "Enter Name", Toast.LENGTH_SHORT).show()
                return false
            }
            title.text.isEmpty() -> {
                Toast.makeText(this@Add, "Enter Age", Toast.LENGTH_SHORT).show()
                return false
            }
            author.text.isEmpty() -> {
                Toast.makeText(this@Add, "Enter Phone", Toast.LENGTH_SHORT).show()
                return false
            }
            course.text.isEmpty() -> {
                Toast.makeText(this@Add, "Enter Email", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }

    private fun addData() {

        val isbn: EditText = findViewById(R.id.isbn)
        val title: EditText = findViewById(R.id.title)
        val author: EditText = findViewById(R.id.author)
        val course: EditText = findViewById(R.id.course)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        btnAdd.setOnClickListener {
            if (validate()) {
                if(isAdd) {
                    helper.addRecord(
                        isbn.text.toString(),
                        title.text.toString(),
                        author.text.toString(),
                        course.text.toString())
                }else{
                    helper.updateData(""+id,isbn.text.toString(),
                        title.text.toString(),author.text.toString(),
                        course.text.toString())
                }
                clearAllFields()
                finish()
            }
        }
        btnDelete.setOnClickListener {
            helper.deleteData(""+id)
        }
    }

    private fun clearAllFields() {

        val isbn: EditText = findViewById(R.id.isbn)
        val title: EditText = findViewById(R.id.title)
        val author: EditText = findViewById(R.id.author)
        val course: EditText = findViewById(R.id.course)

        isbn.text.clear()
        title.text.clear()
        author.text.clear()
        course.text.clear()
    }
}