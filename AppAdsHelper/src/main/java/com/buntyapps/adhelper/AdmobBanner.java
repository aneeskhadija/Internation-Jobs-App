package com.buntyapps.adhelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Map;

public class AdmobBanner extends RelativeLayout {



    public AdmobBanner(Context context) {
        super(context);
        getAtter(null,0);
        init();
    }

    public AdmobBanner(Context context, AttributeSet attrs) {
        super(context, attrs);

        getAtter(attrs,0);
        init();
    }

    public AdmobBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAtter(attrs,defStyleAttr);
        init();
    }
    String bannerID="ca-app-pub-3940256099942544/6300978111";
    private void getAtter(AttributeSet attributeSet,int defStyleAttr)
    {
        if (attributeSet==null)
            return;

        TypedArray typedArray=getContext().obtainStyledAttributes(attributeSet,R.styleable.AdmobBanner,defStyleAttr,0);

        bannerID=typedArray.getString(R.styleable.AdmobBanner_AdMobBannerID);
        if (TextUtils.isEmpty(bannerID))
            bannerID="ca-app-pub-3940256099942544/6300978111";
    }

    private void init()
    {
        inflate(getContext(),R.layout.admob_banner,this);
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }

//                // Start loading ads here...

                loadAd();
            }
        });

    }

    private void loadAd()
    {
        ShimmerFrameLayout shimmerFrameLayout=this.findViewById(R.id.shimmer);
        RelativeLayout banrContainer=this.findViewById(R.id.admobBanrContainer);
        AdView adView = new AdView(getContext());

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId(bannerID);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("MyApp","loaded");
                shimmerFrameLayout.setVisibility(GONE);
                banrContainer.removeAllViews();
                banrContainer.addView(adView);

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("MyApp","not"+adError.toString());
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }
}
