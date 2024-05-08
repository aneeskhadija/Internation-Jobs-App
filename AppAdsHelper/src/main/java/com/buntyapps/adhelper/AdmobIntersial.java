package com.buntyapps.adhelper;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AdmobIntersial {

    private Context context;

    private InsListners insListners;
    private Timer timer;
//    private KProgressHUD progressHUD;
    private String adID;
    public AdmobIntersial(Context context, InsListners insListners ) {
        this.context = context;
        this.insListners=insListners;

    }

    private InterstitialAd mInterstitialAd;

    public void loadInsAd(String adUnitID)
    {

        adID=adUnitID;

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

                if (mInterstitialAd==null) {

                    if (TextUtils.isEmpty(adID))
                    {
                        adID="ca-app-pub-3940256099942544/1033173712";
                    }
                    AdRequest adRequest = new AdRequest.Builder().build();


                    InterstitialAd.load(context, adID,
                            adRequest, new InterstitialAdLoadCallback() {
                                @Override
                                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                    // The mInterstitialAd reference will be null until
                                    // an ad is loaded.
                                    mInterstitialAd = interstitialAd;
                                    Log.i("ins", "onAdLoaded");
                                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                                        @Override
                                        public void onAdDismissedFullScreenContent() {
                                            // Called when fullscreen content is dismissed.
                                            Log.d("TAG", "The ad was dismissed.");

                                            insListners.onAdDismes();
                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                                            // Called when fullscreen content failed to show.
                                            Log.d("TAG", "The ad failed to show.");

                                            insListners.onAdDismes();


                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                            // Called when fullscreen content is shown.
                                            // Make sure to set your reference to null so you don't
                                            // show it a second time.
                                            mInterstitialAd = null;
                                            Log.d("TAG", "The ad was shown.");
                                        }
                                    });
                                }

                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    // Handle the error
                                    Log.i("ins", loadAdError.getMessage());
                                    mInterstitialAd = null;
                                }
                            });
                }
            }
        });

    }


    public void showInsAd()
    {
        if (mInterstitialAd!=null)
        {
//            progressHUD= KProgressHUD.create(context)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setLabel("Showing Ad")
//                    .setCancellable(true)
//                    .setAnimationSpeed(2)
//                    .setDimAmount(0.5f)
//                    .show();
            timeCount();
        }
    }

    private void timeCount()
    {

        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (timer!=null)
                        {
                            timer.cancel();
//                            if (progressHUD!=null) {
//                                if (progressHUD.isShowing()) {
//                                    progressHUD.dismiss();
//                                }
//                            }
                            timer=null;
                            mInterstitialAd.show(((Activity)context));

                        }

                    }
                });

            }
        },2000,1000);
    }
    public interface InsListners {
        void onAdDismes();
    }
    public boolean isInsNull()
    {
        if (mInterstitialAd!=null)
            return false;
        else
            return true;
    }

}
