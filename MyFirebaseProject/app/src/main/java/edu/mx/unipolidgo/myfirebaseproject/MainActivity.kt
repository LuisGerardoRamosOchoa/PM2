package edu.mx.unipolidgo.myfirebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail : EditText
    private lateinit var editTextPass: EditText
    private lateinit var editTextConf: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLoginRe: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.textEmail)
        editTextPass = findViewById(R.id.editTextPassword)
        editTextConf = findViewById(R.id.editTextConfirmPassword)
        btnSignUp = findViewById(R.id.buttonRegister)
        btnLoginRe = findViewById(R.id.buttonRedirectLogin)

        auth = Firebase.auth

        btnSignUp.setOnClickListener{
            signUpUser()
        }

        btnLoginRe.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            
        }

    }

    private fun signUpUser(){

        val email = editTextEmail.text.toString()
        val pass = editTextPass.text.toString()
        val confirm = editTextConf.text.toString()

        if(email.isBlank() || pass.isBlank() || confirm.isBlank()){
            Toast.makeText(this,"Email y password no pueden estar vacíos",Toast.LENGTH_LONG).show()
            return
        }

        if(pass!=confirm){
            Toast.makeText(this,"La contraseña no coincide",Toast.LENGTH_LONG).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Se ha registrado correctamente", Toast.LENGTH_SHORT).show()
                //finish()
            }else{
                Toast.makeText(this,"El registro no se ha podido realizar",Toast.LENGTH_LONG).show()
            }
        }

    }

}