package com.example.thii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.*
import com.example.thii.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var  rc: Cursor
    var regex  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var helper = data(applicationContext)
        db = helper.readableDatabase
        binding.dangky.setOnClickListener {
            var cv =ContentValues()
            var b = binding.email.text.toString()
            var c = binding.Tendn.text.toString()
            var d = binding.Matkhau.text.toString()
            var e = binding.nlMatkhau.text.toString()
            cv.put("email",binding.email.text.toString() )
            cv.put("tendangnhap", binding.Tendn.text.toString())
            cv.put("matkhau", binding.Matkhau.text.toString())
            if(b == "") {
                binding.email.error = "Vui lòng nhập email"
            }else
                if(c == "") {
                    binding.Tendn.error = "Vui lòng nhập tên đăng nhập"

                }else
                    if(d == "") {
                        binding.Matkhau.error = "Vui lòng nhập mật khẩu"

                    }else if(e== "") {
                        binding.nlMatkhau.error = "Vui lòng nhập lại mật khẩu"

                    }
                    else
                        if(d!=e) {
                            binding.nlMatkhau.error = "Nhập sai mật khẩu"

                        }else
                            if(b.matches(regex)) {
                                rc = db.rawQuery("SELECT * FROM DANGKY WHERE email = '$b'", null)
                                if(rc.getCount()>0) {
                                    Toast.makeText(this, "Email da ton tai", Toast.LENGTH_SHORT).show()
                                }else {
                                    db.insert("DANGKY", null, cv)
                                    Toast.makeText(this, "đăng ký thành công",Toast.LENGTH_SHORT).show()

                                    binding.email.setText("")
                                    binding.Matkhau.setText("")
                                    binding.Tendn.setText("")
                                    binding.nlMatkhau.setText("")
                                }} else {
                                Toast.makeText(this, "Email sai", Toast.LENGTH_SHORT).show()

                            }


        }
    }
}