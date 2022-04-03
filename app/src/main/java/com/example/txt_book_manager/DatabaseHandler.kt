package com.example.txt_book_manager
//databasehandler
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {

    companion object {

        val DATABASE_NAME = "TxtBook_Database.db"
        val TABLE_BOOKS = "Book_Table"
        val id = "id"
        val isbn = "isbn"
        val title = "title"
        val author = "author"
        val course = "course"
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table $TABLE_BOOKS ( ID INTEGER PRIMARY KEY AUTOINCREMENT,ISBN TEXT,TITLE TEXT,AUTHOR TEXT,COURSE TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS)
        onCreate(db)
    }

    fun addRecord(isbn: String, title: String, author: String, course: String) {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(isbn, isbn)
            contentValues.put(title, title)
            contentValues.put(author, author)
            contentValues.put(course, course)
        db.insert(TABLE_BOOKS,null,contentValues)
        }


    fun book_listing(): ArrayList<EmpModelClass>  {
        val db = this.writableDatabase
        val res = db.rawQuery("select * from " + TABLE_BOOKS, null)
        val useList = ArrayList<EmpModelClass>()
        while (res.moveToNext()) {
            val EmpModelClass = EmpModelClass()
            EmpModelClass.id = Integer.valueOf(res.getString(0))
            EmpModelClass.isbn = res.getString(1)
            EmpModelClass.title =res.getString(2)
            EmpModelClass.author = res.getString(3)
            EmpModelClass.course = res.getString(4)
            useList.add(EmpModelClass)


            useList.add(EmpModelClass)
        }
        return useList
    }

    @SuppressLint("Range")
    fun getAllBookData(s: String): ArrayList<EmpModelClass> {
        val stuList: ArrayList<EmpModelClass> = arrayListOf<EmpModelClass>()
        val cursor: Cursor = getReadableDatabase().query(TABLE_BOOKS, arrayOf(id, isbn,title, author, course), null, null, null, null, null)
        cursor.use { cursor ->
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val id : Int = cursor.getInt(cursor.getColumnIndex(id))
                        val isbn: String = cursor.getString(cursor.getColumnIndex(isbn))
                        val title: String = cursor.getString(cursor.getColumnIndex(title))
                        val author: String = cursor.getString(cursor.getColumnIndex(author))
                        val course: String =  cursor.getString(cursor.getColumnIndex(course))
                        var EmpModelClass = EmpModelClass()
                        EmpModelClass.id = id
                        EmpModelClass.isbn = isbn
                        EmpModelClass.title = title
                        EmpModelClass.author = author
                        EmpModelClass.course = course
                        stuList.add(EmpModelClass)
                    } while ((cursor.moveToNext()))
                }
            }
        }

        return stuList
    }
    @SuppressLint("Range")
    fun getaddRecord(id: String): EmpModelClass {
        var empModelClass  = EmpModelClass()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_BOOKS + " WHERE " + ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        empModelClass.id = cursor.getInt(cursor.getColumnIndex(ID))
                        empModelClass.isbn = cursor.getString(cursor.getColumnIndex(isbn))
                        empModelClass.title = cursor.getString(cursor.getColumnIndex(title))
                        empModelClass.author = cursor.getString(cursor.getColumnIndex(author))
                        empModelClass.course = cursor.getString(cursor.getColumnIndex(course))

                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }
        return empModelClass
    }

    fun updateData(id: String, isbn: String, title: String, author: String, course: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put(isbn, isbn)
        contentValues.put(title, title)
        contentValues.put(author, author)
        contentValues.put(course,course)
        db.update(TABLE_BOOKS, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_BOOKS,"ID = ?", arrayOf(id))

    }
        }


