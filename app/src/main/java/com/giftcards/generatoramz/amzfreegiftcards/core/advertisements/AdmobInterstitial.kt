package com.giftcards.generatoramz.amzfreegiftcards.core.advertisements

import android.content.Context
import com.giftcards.generatoramz.amzfreegiftcards.core.analytics.Analytics
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.PreferencesManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class AdmobInterstitial(preferencesManager: PreferencesManager,
        context: Context) {

    private var intertitialAd: InterstitialAd? = null

    init {
            intertitialAd = InterstitialAd(context)
            intertitialAd?.adUnitId = "ca-app-pub-7065666432812754/9834287649"
            loadAd()
    }

    private fun loadAd() {
        intertitialAd?.loadAd(AdRequest.Builder().build())
    }

    fun show(notloaded: () -> Unit) {
        if (intertitialAd != null) {
            if (intertitialAd?.isLoaded!!) {
                intertitialAd?.show()
                Analytics.report(Analytics.INTERSTITIAL, Analytics.ADMOB, Analytics.OPEN)
                loadAd()
            } else {
                notloaded()
                loadAd()
            }
        } else {
            notloaded()
            loadAd()
        }
    }
}