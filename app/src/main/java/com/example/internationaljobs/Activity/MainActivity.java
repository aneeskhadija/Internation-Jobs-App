package com.example.internationaljobs.Activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.buntyapps.adhelper.AdmobIntersial;
import com.example.internationaljobs.Adapter.NewJobAdapter;
import com.example.internationaljobs.Handlers.PrograssHandler;
import com.example.internationaljobs.R;
import com.example.internationaljobs.Handlers.TimeHandler;
import com.example.internationaljobs.databinding.ActivityMainBinding;
import com.example.internationaljobs.modles.CataModle;
import com.example.internationaljobs.modles.JobModle;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NewJobAdapter jobAdapter;
    List<Object> filterList=new ArrayList<>();
    List<CataModle> cataList=new ArrayList<>();
    ArrayAdapter spinerAdapter;
    List<String> spinerList=new ArrayList<>();
    List<JobModle> jObslist =new ArrayList<>();
    List<JobModle> alljObslist =new ArrayList<>();
//    AdmobIntersial admobIntersial;
    Context context;
    private int REQUEST_CODE = 11;




    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {

                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this, REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (!isconnector()){
            Dialog dialog = new Dialog(this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.nointernet_dialouge);
            dialog.show();
            Button button = dialog.findViewById(R.id.btnSpinAndWinRedeem);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        else {

//            Toast.makeText(getApplicationContext(), " Internet Access Granted", Toast.LENGTH_SHORT).show();

        }


//        admobIntersial=new AdmobIntersial(this, new AdmobIntersial.InsListners() {
//            @Override
//            public void onAdDismes() {
//
//            }
//        });

//        admobIntersial.loadInsAd(getString(R.string.ins));
        spinerAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinerList);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(spinerAdapter);

        spinerList.add("Select Category");

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0)
                {
                    CataModle modle= (CataModle) cataList.get((i-1));
                    Intent intent=new Intent(getApplicationContext(),JobsActivity.class);
                    intent.putExtra("text",modle.getTitle());
                    intent.putExtra("id",modle.getId());
                    startActivity(intent);
//                    admobIntersial.showInsAd();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

         jobAdapter=new NewJobAdapter(filterList,MainActivity.this);
        binding.jobcatRcr.setAdapter(jobAdapter);

        loadCata();
        loadnewJobs();

        keyWordSearch();

    }

    private void keyWordSearch() {

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=binding.edittext.getText().toString();


                if (TextUtils.isEmpty(text))
                {
                    filterList.clear();
                    filterList.addAll(jObslist);
                    jobAdapter.notifyDataSetChanged();
                }
                else {



                    filterList.clear();
                    Log.d("jobsKey",String.valueOf(text));
                    for (int j = 0; j < alljObslist.size(); j++) {
                        JobModle modle=alljObslist.get(j);
//                        Log.d("jobsKey",modle.getTitle());
                        if (modle.getTitle().toLowerCase().contains(text.toLowerCase())||
                                modle.getCompany().toLowerCase().contains(text.toLowerCase())||
                                modle.getCountry().toLowerCase().contains(text.toLowerCase()))
                        {
                            Log.d("jobsKey",modle.getTitle());
                            filterList.add(modle);
                        }

                    }
                    jobAdapter.notifyDataSetChanged();
                }


            }
        });

        binding.edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (TextUtils.isEmpty(charSequence))
                {
                    filterList.clear();
                    filterList.addAll(jObslist);
                    jobAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



    private void loadCata()
    {

        PrograssHandler.showPrograssDialog("loading...",this);
        String url="https://developer-codeworks.com/ijobs/getCata.php?table=categories";


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                PrograssHandler.hidePrograssDialog();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);

                        Log.d("catas",object.getString("id"));
                        Log.d("catas",object.getString("title"));

                        cataList.add(new CataModle(object.getString("id"),object.getString("title")));

                        spinerList.add(object.getString("title"));
                    }

                    spinerAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                PrograssHandler.hidePrograssDialog();
                Toast.makeText(MainActivity.this, "check internet", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void loadnewJobs()
    {
//        PrograssHandler.showPrograssDialog("loading...",this);
        String url="https://developer-codeworks.com/ijobs/getCata.php?table=jobs";


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                PrograssHandler.hidePrograssDialog();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);


                        JobModle jobModle=new JobModle(object.getString("id"),object.getString("job_title"),object.getString("job_description"),
                                object.getString("job_requirements"),object.getString("company"),object.getString("apply_link"),
                                object.getString("start_date"),object.getString("expiry_date"),object.getString("country"),object.getString("status"),
                                object.getString("user_id"),object.getString("category_id"),object.getString("created_at"));

                        Log.d("newJob",jobModle.getStartDate());
                        Log.d("newJob","c = "+ TimeHandler.getCurentDateInString());

                        String[] statseparated = jobModle.getStartDate().split("-");
                        int sy= Integer.parseInt(statseparated[0].trim());
                        int sm=Integer.parseInt(statseparated[1].trim());
                        int sd=Integer.parseInt(statseparated[2].trim());
                        sm=sm-1;



                        String[] separated = jobModle.getEndDDAte().split("-");

                        Log.d("tag",separated[0].trim()+"/"+separated[1].trim()+"/"+separated[2].trim());

                        int ey= Integer.parseInt(separated[0].trim());
                        int em=Integer.parseInt(separated[1].trim());
                        int ed=Integer.parseInt(separated[2].trim());
                        em=em-1;

                        Log.d("newJob","d = "+TimeHandler.remaingDays(TimeHandler.getMillesOfGivenDate(sd,sm,sy)));


                        if ( TimeHandler.remaingTimeInMills(TimeHandler.getMillesOfGivenDate(ed,em,ey))>0)
                        {


                            alljObslist.add(jobModle);

                            if ( TimeHandler.remaingDays(TimeHandler.getMillesOfGivenDate(sd,sm,sy))>-10&&
                                    TimeHandler.remaingDays(TimeHandler.getMillesOfGivenDate(sd,sm,sy))<1)
                            {
                                jObslist.add(jobModle);
                                filterList.add(jobModle);
                                Log.d("newJob","add");

                            }

                        }





                    }


//                    if (jObslist!=null) {
//                        Collections.sort(jObslist, new AssendingComporater());
                        filterList.clear();
                        filterList.addAll(jObslist);
//                    }
//                    if (alljObslist!=null){
//                        Collections.sort(alljObslist,new AssendingComporater());
//                    }


                    jobAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                PrograssHandler.hidePrograssDialog();
                Toast.makeText(MainActivity.this, "check internet", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
    public class AssendingComporater implements Comparator<JobModle>
    {

        @Override
        public int compare(JobModle jobModle, JobModle t1) {
            String[] statseparated1 = jobModle.getStartDate().split("-");
            String[] statseparated2 = t1.getStartDate().split("-");

            int sy= Integer.parseInt(statseparated1[0].trim());
            int sm=Integer.parseInt(statseparated1[1].trim());
            int sd=Integer.parseInt(statseparated1[2].trim());
            sm=sm-1;

            int sy2= Integer.parseInt(statseparated2[0].trim());
            int sm2=Integer.parseInt(statseparated2[1].trim());
            int sd2=Integer.parseInt(statseparated2[2].trim());
            sm2=sm2-1;




            return (int) (TimeHandler.remaingTimeInMills(TimeHandler.getMillesOfGivenDate(sd2,sm2,sy2))-TimeHandler.remaingTimeInMills(TimeHandler.getMillesOfGivenDate(sd,sm,sy)));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.spinner.setSelection(0);
    }

    private boolean isconnector(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileData = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected()) {
            return true;
        }

        if (mobileData.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== REQUEST_CODE){
//            Toast.makeText(getApplicationContext(), "Your App is Up to date", Toast.LENGTH_SHORT).show();

            if (requestCode!=REQUEST_CODE){
//                Log.d("nmmm","Update Flow Failed"+requestCode);


            }
        }

    }
}
