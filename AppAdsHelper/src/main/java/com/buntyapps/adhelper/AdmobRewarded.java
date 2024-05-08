package com.buntyapps.adhelper;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
//import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Map;

public class AdmobRewarded {
    Context context;

    String adUnitID;
    public AdmobRewarded(Context context) {
        this.context = context;
    }
    private RewardedAd mRewardedAd;
    public void loadRewarded(String adID,RewardListner rewardListner) {
        if (TextUtils.isEmpty(adID))
            adID = "ca-app-pub-3940256099942544/5224354917";

        adUnitID=adID;
        if (mRewardedAd == null) {

//            KProgressHUD progressHUD= KProgressHUD.create(context)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setLabel("Loading Ad")
//                    .setCancellable(true)
//                    .setAnimationSpeed(2)
//                    .setDimAmount(0.5f)
//                    .show();

            MobileAds.initialize(context, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                    Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                    for (String adapterClass : statusMap.keySet()) {
                        AdapterStatus status = statusMap.get(adapterClass);
                        Log.d("MyApp", String.format(
                                "Adapter name: %s, Description: %s, Latency: %d",
                                adapterClass, status.getDescription(), status.getLatency()));
                    }

                    // Start loading ads here...

                    AdRequest adRequest = new AdRequest.Builder().build();

                    RewardedAd.load(context, adUnitID,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    // Handle the error.
//                                    if (progressHUD!=null) {
//                                        if (progressHUD.isShowing()) {
//                                            progressHUD.dismiss();
//                                        }
//                                    }
                                    Toast.makeText(context, "Ad Loading Failed, Try Again Later", Toast.LENGTH_SHORT).show();
                                    mRewardedAd = null;

                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
//                                    if (progressHUD!=null) {
//                                        if (progressHUD.isShowing()) {
//                                            progressHUD.dismiss();
//                                        }
//                                    }
                                    mRewardedAd = rewardedAd;
                                    showRewarded(rewardListner);
                                }
                            });
                }
            });

        }
    }

    public void showRewarded(RewardListner rewardListner)
    {
        if (mRewardedAd != null) {
            Activity activityContext = ((Activity) context);
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    rewardListner.OnReceived();
                    mRewardedAd=null;
                }
            });
        } else {
            Toast.makeText(context, "The rewarded ad wasn't ready yet", Toast.LENGTH_SHORT).show();
        }
    }

    public interface RewardListner{
        void OnReceived();
    }
}
