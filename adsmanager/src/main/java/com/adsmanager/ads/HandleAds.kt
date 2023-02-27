package com.adsmanager.ads

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.adsmanager.admob.AdmobAds
import com.adsmanager.applovin.ApplovinDiscoveryAds
import com.adsmanager.applovin.ApplovinMaxAds
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.rewards.IRewards
import com.adsmanager.core.NetworkAds
import com.adsmanager.core.iadsmanager.IInitialize
import com.adsmanager.core.SizeBanner
import com.adsmanager.core.SizeNative
import com.adsmanager.fan.FanAds
import com.adsmanager.unityAdsModule.UnityAdsModule

class HandleAds(
    private val admobAds: AdmobAds,
    private val fanAds: FanAds,
    private val applovinMaxAds: ApplovinMaxAds,
    private val applovinDiscoveryAds: ApplovinDiscoveryAds,
    private val unityAdsModule: UnityAdsModule,
) {
    fun initialize(
        context: Context,
        appId: String?,
        iInitialize: IInitialize,
        networkAds: NetworkAds
    ) {
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.initialize(context, appId, iInitialize)
            NetworkAds.FAN -> fanAds.initialize(context, appId, iInitialize)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.initialize(context, appId, iInitialize)
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.initialize(context, appId, iInitialize)
            NetworkAds.START_IO -> {}
            NetworkAds.UNITY_ADS -> unityAdsModule.initialize(context, appId,iInitialize)
            NetworkAds.NONE -> {}
        }
    }

    fun setTestDevices(activity: Activity, testDevices: List<String>, networkAds: NetworkAds) {
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.setTestDevices(activity, testDevices)
            NetworkAds.FAN -> fanAds.setTestDevices(activity, testDevices)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.setTestDevices(activity, testDevices)
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.setTestDevices(
                activity,
                testDevices
            )
            NetworkAds.START_IO -> {}
            NetworkAds.UNITY_ADS -> unityAdsModule.setTestDevices(activity, testDevices)
            NetworkAds.NONE -> {}
        }
    }

    fun loadGdpr(activity: Activity, childDirected: Boolean, networkAds: NetworkAds) {
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.loadGdpr(activity, childDirected)
            NetworkAds.FAN -> fanAds.loadGdpr(activity, childDirected)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.loadGdpr(activity, childDirected)
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.loadGdpr(activity, childDirected)
            NetworkAds.START_IO -> {}
            NetworkAds.UNITY_ADS -> unityAdsModule.loadGdpr(activity, childDirected)
            NetworkAds.NONE -> {}
        }
    }

    fun showBanner(
        activity: Activity,
        bannerView: RelativeLayout,
        sizeBanner: SizeBanner,
        networkAds: NetworkAds,
        adUnitId: String,
        callbackAds: CallbackAds?
    ) {
        if(adUnitId.isEmpty()) {
            callbackAds?.onAdFailedToLoad("adUnitId isEmpty")
            return
        }
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.showBanner(
                activity,
                bannerView,
                sizeBanner,
                adUnitId,
                callbackAds
            )
            NetworkAds.FAN -> fanAds.showBanner(
                activity,
                bannerView,
                sizeBanner,
                adUnitId,
                callbackAds
            )
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.showBanner(
                activity,
                bannerView,
                sizeBanner,
                adUnitId,
                callbackAds
            )
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.showBanner(
                activity,
                bannerView,
                sizeBanner,
                adUnitId,
                callbackAds
            )
            NetworkAds.START_IO -> {
                callbackAds?.onAdFailedToLoad("Ads None")
            }
            NetworkAds.UNITY_ADS -> unityAdsModule.showBanner(
                activity,
                bannerView,
                sizeBanner,
                adUnitId,
                callbackAds
            )
            NetworkAds.NONE -> {
                callbackAds?.onAdFailedToLoad("Ads None")
            }
        }
    }

    fun loadInterstitial(
        activity: Activity,
        networkAds: NetworkAds,
        adUnitId: String,
    ) {
        if(adUnitId.isEmpty()) {
            return
        }
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.loadInterstitial(activity, adUnitId)
            NetworkAds.FAN -> fanAds.loadInterstitial(activity, adUnitId)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.loadInterstitial(activity, adUnitId)
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.loadInterstitial(
                activity,
                adUnitId
            )
            NetworkAds.START_IO -> {}
            NetworkAds.UNITY_ADS -> unityAdsModule.loadInterstitial(activity, adUnitId)
            NetworkAds.NONE -> {}
        }
    }

    fun showInterstitial(
        activity: Activity,
        networkAds: NetworkAds,
        adUnitId: String,
        callbackAds: CallbackAds?
    ) {
        if(adUnitId.isEmpty()) {
            callbackAds?.onAdFailedToLoad("adUnitId isEmpty")
            return
        }
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.showInterstitial(activity, adUnitId, callbackAds)
            NetworkAds.FAN -> fanAds.showInterstitial(activity, adUnitId, callbackAds)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.showInterstitial(
                activity,
                adUnitId,
                callbackAds
            )
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.showInterstitial(
                activity,
                adUnitId,
                callbackAds
            )
            NetworkAds.START_IO -> {
                callbackAds?.onAdFailedToLoad("Ads None")
            }
            NetworkAds.UNITY_ADS -> unityAdsModule.showInterstitial(activity, adUnitId, callbackAds)
            NetworkAds.NONE -> {
                callbackAds?.onAdFailedToLoad("Ads None")
            }
        }
    }

    fun showNativeAds(
        activity: Activity,
        nativeView: RelativeLayout,
        sizeNative: SizeNative,
        networkAds: NetworkAds,
        adUnitId: String,
        callbackAds: CallbackAds?
    ) {
        if(adUnitId.isEmpty()) {
            callbackAds?.onAdFailedToLoad("adUnitId isEmpty")
            return
        }
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.showNativeAds(
                activity,
                nativeView,
                sizeNative,
                adUnitId,
                callbackAds
            )
            NetworkAds.FAN -> fanAds.showNativeAds(
                activity,
                nativeView,
                sizeNative,
                adUnitId,
                callbackAds
            )
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.showNativeAds(
                activity,
                nativeView,
                sizeNative,
                adUnitId,
                callbackAds
            )
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.showNativeAds(
                activity,
                nativeView,
                sizeNative,
                adUnitId,
                callbackAds
            )
            NetworkAds.START_IO -> callbackAds?.onAdFailedToLoad("Ads None")
            NetworkAds.UNITY_ADS -> unityAdsModule.showNativeAds(
            activity,
            nativeView,
            sizeNative,
            adUnitId,
            callbackAds
        )
            NetworkAds.NONE -> callbackAds?.onAdFailedToLoad("Ads None")
        }
    }

    fun loadRewards(
        activity: Activity,
        networkAds: NetworkAds,
        adUnitId: String,
    ) {
        if(adUnitId.isEmpty()) {
            return
        }
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.loadRewards(activity, adUnitId)
            NetworkAds.FAN -> fanAds.loadRewards(activity, adUnitId)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.loadRewards(activity, adUnitId)
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.loadRewards(activity, adUnitId)
            NetworkAds.START_IO -> {}
            NetworkAds.UNITY_ADS -> unityAdsModule.loadRewards(activity, adUnitId)
            NetworkAds.NONE -> {}
        }
    }

    fun showRewards(
        activity: Activity,
        networkAds: NetworkAds,
        adUnitId: String,
        callbackAds: CallbackAds?,
        iRewards: IRewards?
    ) {
        if(adUnitId.isEmpty()) {
            callbackAds?.onAdFailedToLoad("adUnitId isEmpty")
            return
        }
        when (networkAds) {
            NetworkAds.ADMOB -> admobAds.showRewards(activity, adUnitId, callbackAds, iRewards)
            NetworkAds.FAN -> fanAds.showRewards(activity, adUnitId, callbackAds, iRewards)
            NetworkAds.APPLOVIN_MAX -> applovinMaxAds.showRewards(
                activity,
                adUnitId,
                callbackAds,
                iRewards
            )
            NetworkAds.APPLOVIN_DISCOVERY -> applovinDiscoveryAds.showRewards(
                activity,
                adUnitId,
                callbackAds,
                iRewards
            )
            NetworkAds.START_IO -> callbackAds?.onAdFailedToLoad("Ads None")
            NetworkAds.UNITY_ADS -> unityAdsModule.showRewards(activity, adUnitId, callbackAds, iRewards)
            NetworkAds.NONE -> callbackAds?.onAdFailedToLoad("Ads None")
        }
    }
}