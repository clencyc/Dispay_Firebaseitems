package com.example.display_clency

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var EDT_Email:EditText
    lateinit var EDT_name:EditText
    lateinit var BTN_Submit: Button
    lateinit var EdT_age:EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        EDT_Email = findViewById(R.id.edtEmail)
        EDT_name= findViewById(R.id.edtname)
        BTN_Submit = findViewById(R.id.btn_submit)
        EdT_age = findViewById(R.id.edt_age)


        BTN_Submit.setOnClickListener {
            var name = EDT_name.text.toString().trim()
            var email = EDT_Email.text.toString().trim()
            var age = EdT_age.text.toString().trim()

            var time_id = System.currentTimeMillis().toString().trim()
            //progress bar
            var progress = ProgressDialog(this)
            progress.setTitle("Saving Data")
            progress.setMessage("Please wait")

            //validation  of data
            if (name.isEmpty() || email.isEmpty() || age.isEmpty()){

                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()

            } else{
                var my_child = FirebaseDatabase.getInstance().reference.child("Names/" + time_id)
                var user_data = User(name, email, age, time_id)
                
                
                //show progress bar
                progress.show()
                
                my_child.setValue(user_data).addOnCompleteListener { 
                    if (it.isSuccessful){

                        Toast.makeText(this, "Data Uploaded successfully", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this, "Failed to upload data", Toast.LENGTH_SHORT).show()
                    }
                }
                
                
            }



        }
    }
}