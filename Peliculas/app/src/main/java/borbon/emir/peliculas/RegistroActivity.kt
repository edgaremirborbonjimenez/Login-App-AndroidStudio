package borbon.emir.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistroActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirm_password: EditText
    lateinit var btn_sigin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirm_password = findViewById(R.id.confirm_password)
        btn_sigin = findViewById(R.id.sigin)

        auth = Firebase.auth

        btn_sigin.setOnClickListener {
            var email: String = email.text.toString()
            var psw1: String = password.text.toString()
            var psw2: String = confirm_password.text.toString()

            if(!email.isNullOrEmpty() && !psw1.isNullOrEmpty() && !psw2.isNullOrEmpty()){

                if(psw1 == psw2){

                    auth.createUserWithEmailAndPassword(email, psw1)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d("exito", "createUserWithEmail:success")
                                //Log.w("error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    this,
                                    "Se ha registrado ${user?.email}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(user)
                                finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w("error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    this,
                                    "No se pudo registrar al usuario ${email} ${psw1}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(null)
                            }
                        }
                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT)
                }

            }else{
                Toast.makeText(this,"Ingresa datos de correo y contraseña",Toast.LENGTH_SHORT)
            }
        }

    }
}