package com.tecsup.edu.restaurantetrujillo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var mRewardedAd: RewardedAd? = null
    private var mAuth: FirebaseAuth? = null
    private var mFirestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()

        // Configurar los listeners de los botones de inicio de sesión y registro

        // Configurar los listeners de los botones de inicio de sesión y registro
        val registerButton = findViewById<Button>(R.id.btnRegistrar)

        supportActionBar?.hide()

        registerButton.setOnClickListener { // Lógica de registro
            register()
        }


        //val buttonLogin = findViewById<Button>(R.id.btnIngresar)
        /*buttonLogin.setOnClickListener {
            startActivity(Intent(this, PlatosActivity::class.java))
            /*if (mInterstitialAd != null){
                    mInterstitialAd?.show(this)
                }*/

            if (mRewardedAd != null) {
                mRewardedAd?.show(this) {
                    fun onUserEarnedReward(rewardItem: RewardItem) {
                        val mRewardedAdAmount = rewardItem.amount
                        val mRewardedAdType = rewardItem.type
                        Log.v("Bonificado", mRewardedAdAmount.toString())
                        Log.v("Bonificado", mRewardedAdType.toString())

                    }
                    onUserEarnedReward(it)
                }

            }

            MobileAds.initialize(this)
            //val adView = findViewById<AdView>(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            //adView. loadAd(adRequest)


            /*InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712",
        adRequest, object: InterstitialAdLoadCallback(){

            override  fun onAdFailedToLoad(p0: LoadAdError){
                mInterstitialAd = null

            }
            override  fun onAdLoaded(p0:InterstitialAd){
                mInterstitialAd = p0

            }

        })*/

            RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",

                adRequest, object : RewardedAdLoadCallback() {

                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        mRewardedAd = null


                    }

                    override fun onAdLoaded(p0: RewardedAd) {
                        mRewardedAd = p0
                    }
                })


            supportActionBar?.hide()


        }*/


    }


    private fun register() {
        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        val email = emailEditText.text.toString().trim { it <= ' ' }
        val password = passwordEditText.text.toString().trim { it <= ' ' }
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // El registro fue exitoso
                    Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT)
                        .show()

                    // Guardar los datos del cliente en Firestore
                    saveCustomerData(email)

                    startActivity(Intent(this, LoginActivity::class.java))

                    // Continúa con la lógica de tu aplicación después del registro
                } else {
                    // Error en el registro
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error en el registro",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
    }

    private fun saveCustomerData(email: String) {
        // Obtén la ID del usuario actualmente autenticado
        val userId = mAuth!!.currentUser!!.uid

        // Crea un objeto Map con los datos del cliente
        val customerData: MutableMap<String, Any> = HashMap()
        customerData["email"] = email

        // Guarda los datos del cliente en Firestore
        mFirestore!!.collection("customers")
            .document(userId)
            .set(customerData)
            .addOnSuccessListener {
                // Los datos del cliente se guardaron exitosamente en Firestore
            }
            .addOnFailureListener {
                // Error al guardar los datos del cliente en Firestore
            }
    }


}