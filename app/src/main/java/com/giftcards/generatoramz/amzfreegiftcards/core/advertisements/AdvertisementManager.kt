package com.giftcards.generatoramz.amzfreegiftcards.core.advertisements

import android.app.Activity
import android.content.Context
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.CoinsManager
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.PreferencesManager

class AdvertisementManager {

    private var preferencesManager: PreferencesManager

    var house: HouseOffers? = null
    var adcolony: AdcolonyVideo? = null
    var offertoro: OffertoroOfferwall? = null
    var vungle: VungleVideo? = null
    var unity: UnityVideo? = null
    var admob: AdmobVideo? = null

    private var isActivated = false
    private var resuming = false

    constructor(preferencesManager: PreferencesManager, coinsManager: CoinsManager, context: Context) {
        this.preferencesManager = preferencesManager

        this.house = HouseOffers(context, "Coins", coinsManager)

        this.adcolony = AdcolonyVideo(preferencesManager, coinsManager)

        this.offertoro = OffertoroOfferwall(coinsManager, preferencesManager)

        this.vungle = VungleVideo(preferencesManager, coinsManager)


        unity = UnityVideo(preferencesManager, coinsManager)


        admob = AdmobVideo(coinsManager, preferencesManager, context)

    }

    fun init(activity: Activity) {
        if (!isActivated) {
            isActivated = true
            if (vungle != null) {
                vungle?.init(activity)
            }

            if (offertoro != null) {
                offertoro?.init(activity)
            }
            if (house != null) {
                house?.init()
            }
            Thread {
                if (unity != null) {
                    unity?.init(activity)
                }
                if (adcolony != null) {
                    adcolony?.init(activity)
                }
                onResume(activity, false)
            }.start()
        }
    }

    fun onResume(activity: Activity, thread: Boolean) {
        if (!resuming) {
            resuming = true
            if (thread) {
                Thread { resume(activity) }.start()
            } else {
                resume(activity)
            }
            try {
                if (vungle != null) {
                    vungle?.onResume()
                }
            } catch (ex: Exception) {
            }
        }
    }

    private fun resume(activity: Activity) {
        try {
            if (adcolony != null) {
                adcolony?.onResume(activity)
            }
        } catch (ex: Exception) {
        }

        resuming = false
    }
}