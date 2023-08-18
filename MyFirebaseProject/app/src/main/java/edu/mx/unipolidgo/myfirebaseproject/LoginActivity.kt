package edu.mx.unipolidgo.myfirebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var editTextLogin : EditText
    lateinit var editPassLogin: EditText

    lateinit var btnLogin:Button

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextLogin = findViewById(R.id.textEmailLogin)
        editPassLogin = findViewById(R.id.editTextPasswordLogin)

        btnLogin = findViewById(R.id.buttonLogin)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            login()
        }

    }

    private fun login(){
        val email = editTextLogin.text.toString()
        val pass = editPassLogin.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Login exitoso",Toast.LENGTH_LONG).show()

                val intent = Intent(this, DataActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this,"Verifica tus datos",Toast.LENGTH_LONG).show()
            }
        }
    }
}