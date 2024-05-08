package com.buntyapps.adhelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.Map;

public class AdmobNative extends RelativeLayout {
    public AdmobNative(Context context) {
        super(context);
        getAtter(null,0);
        init();
    }

    public AdmobNative(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAtter(attrs,0);
        init();
    }

    public AdmobNative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAtter(attrs,defStyleAttr);
        init();
    }

    private String nativeID="ca-app-pub-3940256099942544/2247696110";
    private boolean showMediumLayout=true;

    private void getAtter(AttributeSet attributeSet,int defStyleAttr)
    {
        if (attributeSet==null)
            return;

        TypedArray typedArray=getContext().obtainStyledAttributes(attributeSet,R.styleable.AdmobNative,defStyleAttr,0);

        nativeID=typedArray.getString(R.styleable.AdmobNative_nativeID);
        showMediumLayout=typedArray.getBoolean(R.styleable.AdmobNative_showMedimAd,true);
        if (TextUtils.isEmpty(nativeID))
            nativeID="ca-app-pub-3940256099942544/2247696110";
    }
    private void init()
    {
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

                // Start loading ads here...
            }
        });

        if (showMediumLayout)
            inflate(getContext(),R.layout.admob_native_medium,this);
        else
            inflate(getContext(),R.layout.admob_native_small,this);


        ShimmerFrameLayout shimmerFrameLayout=this.findViewById(R.id.shimmer);
        TemplateView templateView=this.findViewById(R.id.myNativeTemplate);
        AdLoader adLoader = new AdLoader.Builder(getContext(),nativeID)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(new ColorDrawable(getContext().getResources().getColor(R.color.white))).build();

                        shimmerFrameLayout.hideShimmer();
                        templateView.setVisibility(VISIBLE);
                        templateView.setStyles(styles);
                        templateView.setNativeAd(nativeAd);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("haider", String.valueOf(loadAdError));
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

}
