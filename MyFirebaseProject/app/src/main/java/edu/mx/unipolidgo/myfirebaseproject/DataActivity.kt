package edu.mx.unipolidgo.myfirebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import edu.mx.unipolidgo.myfirebaseproject.databinding.ActivityDataBinding
const val TAG = "FIRESTORE"
class DataActivity : AppCompatActivity() {

    private var binding: ActivityDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_data)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        uploadData()
        readData()

    }
    class FirebaseUtils {
        val firestoreDatabase = FirebaseFirestore.getInstance()
    }

    private fun uploadData(){

        binding!!.btnUploadData.setOnClickListener {
            val editName = findViewById<EditText>(R.id.editName)
            val editDescription = findViewById<EditText>(R.id.editDescription)
            val editPrice = findViewById<EditText>(R.id.editPrice)
            val editSize = findViewById<EditText>(R.id.editSize)
            val editImage = findViewById<EditText>(R.id.editImage)

            val editNameS = editName.text.toString()
            val editDescriptionS = editDescription.text.toString()
            val editPriceS = editPrice.text.toString()
            val editSizeS = editSize.text.toString()
            val editImageS = editImage.text.toString()

            if (editNameS.isNotEmpty() && editDescriptionS.isNotEmpty() && editPriceS.isNotEmpty() && editSizeS.isNotEmpty() && editImageS.isNotEmpty() ) {
                val hashMap = hashMapOf<String, Any>(
                    "Name" to editNameS,
                    "Description" to editDescriptionS,
                    "Size" to editPriceS,
                    "Price" to editSizeS,
                    "Image" to editImageS
                )
                FirebaseUtils().firestoreDatabase.collection("platillos")
                    .add(hashMap)
                    .addOnSuccessListener {
                        Log.d(TAG, "Se añadió el producto con ID {$it.id}")
                        Toast.makeText(this, "Se ha creado un nuevo producto", Toast.LENGTH_LONG).show()
                        editName.setText("")
                        editDescription.setText("")
                        editPrice.setText("")
                        editSize.setText("")
                        editImage.setText("")
                    }
                    .addOnFailureListener{
                        Log.d(TAG, "El documento no se pudo agregar {$it}")
                    }

            } else {
                Toast.makeText(this, "No dejes campos vacíos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(){
        binding!!.btnReadData.setOnClickListener {
            val intent = Intent(this, ViewMenuActivity::class.java)
            startActivity(intent)

            FirebaseUtils().firestoreDatabase.collection("platillos")
                .get()
                .addOnSuccessListener { QuerySnapshot ->
                    QuerySnapshot.forEach{ Document ->
                        Log.d(TAG, "Se lee el documento con el ID: {${Document.data}}")
                        Toast.makeText(this,"Los documentos se publican en la consola",
                        Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener{
                    Log.w(TAG,"Los documentos no se pudieron recuperar {${it.message}}")
                    Toast.makeText(this, "Los documentos no se pudieron recuperar",
                    Toast.LENGTH_LONG).show()
                }
        }
    }




}
