package com.tecsup.edu.restaurantetrujillo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class LoginActivity: AppCompatActivity() {
    private  var mInterstitialAd:  InterstitialAd? = null
    private var mRewardedAd: RewardedAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        val buttonLogin = findViewById<Button>(R.id.btnIngresar)
        buttonLogin.setOnClickListener {
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


        }


    }}