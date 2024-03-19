package borbon.emir.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var btn_login:Button
    lateinit var btn_sigin:Button
    lateinit var tv_recuperar:TextView
    lateinit var email: EditText
    lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login = findViewById(R.id.btn_login);
        btn_sigin = findViewById(R.id.btn_sigin);
        tv_recuperar = findViewById(R.id.tv_recuperar);
        email = findViewById(R.id.email_login)
        password = findViewById(R.id.password_login)

        auth = Firebase.auth

        btn_login.setOnClickListener {
          var email:String = email.text.toString()
          var psw: String = password.text.toString()
        if(!email.isNullOrEmpty() && !psw.isNullOrEmpty()){
            auth.signInWithEmailAndPassword(email, psw)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)

                        var intent = Intent(this,MainActivity::class.java)
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Datos incorrectos",
                            Toast.LENGTH_SHORT,
                        ).show()
                        //updateUI(null)
                    }
                }
        }
        }

        btn_sigin.setOnClickListener {
            var intent = Intent(this,RegistroActivity::class.java)
            startActivity(intent);
        }

        tv_recuperar.setOnClickListener {
            var intent = Intent(this,RecuperacionActivity::class.java)
            startActivity(intent);
        }
    }
}