package com.example.internationaljobs.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//import com.buntyapps.adhelper.AdmobIntersial;
import com.example.internationaljobs.databinding.ActivityJobDetailsBinding;
import com.example.internationaljobs.modles.JobModle;
import com.google.gson.Gson;

public class JobDetails extends AppCompatActivity {

    ActivityJobDetailsBinding binding;
    JobModle modle;
    String tag="JobDetails";
//    AdmobIntersial admobIntersial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJobDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        admobIntersial=new AdmobIntersial(this, new AdmobIntersial.InsListners() {
//            @Override
//            public void onAdDismes() {
//
//            }
//        });
//        admobIntersial.loadInsAd(getString(R.string.ins));

        Gson gson = new Gson();
        modle = gson.fromJson(getIntent().getStringExtra("modle"), JobModle.class);
        binding.companyDescription.setText(modle.getCompany());
        binding.title.setText(modle.getTitle());
        binding.description.setText(modle.getDescription());
        binding.requirements.setText(modle.getRequirment());
//        binding.link.setText(modle.getApplyLink());
        binding.update.setText(modle.getStartDate());
        binding.exdate.setText(modle.getEndDDAte());
        binding.country.setText(modle.getCountry());
//        binding.jobCatTxt.setText(getIntent().getStringExtra("text"));


        String[] separated = modle.getStartDate().split("-");

        Log.d(tag,separated[0].trim()+"/"+separated[1].trim()+"/"+separated[2].trim());


    }

    public void JobCata(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finishAffinity();
    }

    public void applYLink(View view) {
        String url=modle.getApplyLink();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void backBtn(View view) {
//        admobIntersial.showInsAd();
        finish();
    }

    @Override
    public void onBackPressed() {
//        admobIntersial.showInsAd();
        super.onBackPressed();
    }
}