package com.example.internationaljobs.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.buntyapps.adhelper.AdmobIntersial;
import com.buntyapps.adhelper.AdmobIntersial;
import com.example.internationaljobs.Handlers.PrograssHandler;
import com.example.internationaljobs.R;
import com.example.internationaljobs.Adapter.RecyclerAdapter;
import com.example.internationaljobs.Handlers.TimeHandler;
import com.example.internationaljobs.databinding.JobsActivityBinding;
import com.example.internationaljobs.modles.JobModle;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JobsActivity extends AppCompatActivity {

    JobsActivityBinding binding;
    List<Object> filterList=new ArrayList<>();
    List<JobModle> list=new ArrayList<>();
    RecyclerAdapter adapter;
     public static AdmobIntersial admobIntersial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=JobsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        admobIntersial=new AdmobIntersial(this, new AdmobIntersial.InsListners() {
            @Override
            public void onAdDismes() {

            }
        });
        admobIntersial.loadInsAd(getString(R.string.ins));

        binding.catatext.setText(getIntent().getStringExtra("text"));


        adapter=new RecyclerAdapter(filterList,JobsActivity.this,2);
        binding.subjobTxt.setAdapter(adapter);

        getJobs(getIntent().getStringExtra("id"));


        checkBoxFiler();

        keyWordSearch();

        countryAndTimeFilter();

    }

    private void countryAndTimeFilter() {

        binding.countryPiker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                binding.lnSelectCountry.setVisibility(View.GONE);
                filterList.clear();
                for (int i = 0; i < list.size(); i++) {
                    JobModle modle=list.get(i);
                    if (modle.getCountry().toLowerCase().contains(selectedCountry.getName().toLowerCase()))
                    {
                        filterList.add(modle);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void checkBoxFiler() {
        binding.fulltimeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxChecking();
            }
        });
        binding.contactCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxChecking();
            }
        });
        binding.parttimeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxChecking();
            }
        });
        binding.inteerneCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxChecking();
            }
        });
    }

    private void checkBoxChecking()
    {
        filterList.clear();

        if (binding.fulltimeCheck.isChecked()) {
            for (int i = 0; i < list.size(); i++) {
                JobModle modle = list.get(i);
                if (modle.getDescription().toLowerCase().contains("full time") || modle.getRequirment().toLowerCase().contains("full time")) {
                    filterList.add(modle);
                }
            }
        }
        if (binding.inteerneCheck.isChecked())
        {
            for (int i = 0; i < list.size(); i++) {
                JobModle modle=list.get(i);
                if (modle.getDescription().toLowerCase().contains("interne")||modle.getRequirment().toLowerCase().contains("interne"))
                {
                    filterList.add(modle);
                }
            }
        }
        if (binding.contactCheck.isChecked())
        {
            for (int i = 0; i < list.size(); i++) {
                JobModle modle=list.get(i);
                if (modle.getDescription().toLowerCase().contains("contract")||modle.getRequirment().toLowerCase().contains("contract"))
                {
                    filterList.add(modle);
                }
            }
        }
        if (binding.parttimeCheck.isChecked())
        {
            for (int i = 0; i < list.size(); i++) {
                JobModle modle=list.get(i);
                if (modle.getDescription().toLowerCase().contains("part time")||modle.getRequirment().toLowerCase().contains("part time"))
                {
                    filterList.add(modle);
                }
            }
        }

        if (!binding.fulltimeCheck.isChecked()&&!binding.parttimeCheck.isChecked()&&!binding.contactCheck.isChecked()&&!binding.inteerneCheck.isChecked())
            filterList.addAll(list);

        adapter.notifyDataSetChanged();

    }

    private void keyWordSearch() {
        binding.editTextSubcat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (TextUtils.isEmpty(charSequence))
                {
                    filterList.clear();
                    filterList.addAll(list);
                    adapter.notifyDataSetChanged();
                }
                else {


                    filterList.clear();
                    Log.d("jobsKey",String.valueOf(charSequence));
                    for (int j = 0; j < list.size(); j++) {
                        JobModle modle=list.get(j);
//                        Log.d("jobsKey",modle.getTitle());
                        if (modle.getTitle().toLowerCase().contains(String.valueOf(charSequence).toLowerCase())||
                                modle.getCompany().toLowerCase().contains(String.valueOf(charSequence).toLowerCase())||
                                modle.getCountry().toLowerCase().contains(String.valueOf(charSequence).toLowerCase())||
                                modle.getDescription().toLowerCase().contains(String.valueOf(charSequence).toLowerCase())||
                                modle.getRequirment().toLowerCase().contains(String.valueOf(charSequence).toLowerCase()))
                        {
                            Log.d("jobsKey",modle.getTitle());
                            filterList.add(modle);

                        }

                    }
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void getJobs(String cataId)
    {
        PrograssHandler.showPrograssDialog("loading...",this);
        String url="https://developer-codeworks.com/ijobs/getJobs.php?table=jobs&cataID="+cataId;

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object=jsonArray.getJSONObject(i);

                        JobModle jobModle=new JobModle(object.getString("id"),object.getString("job_title"),object.getString("job_description"),
                                object.getString("job_requirements"),object.getString("company"),object.getString("apply_link"),
                                object.getString("start_date"),object.getString("expiry_date"),object.getString("country"),object.getString("status"),
                                object.getString("user_id"),object.getString("category_id"),object.getString("created_at"));


                        String[] separated = jobModle.getEndDDAte().split("-");

                        Log.d("tag",separated[0].trim()+"/"+separated[1].trim()+"/"+separated[2].trim());

                        int ey= Integer.parseInt(separated[0].trim());
                        int em=Integer.parseInt(separated[1].trim());
                        int ed=Integer.parseInt(separated[2].trim());
                        em=em-1;

                        String[] statseparated = jobModle.getStartDate().split("-");

                        Log.d("tag",statseparated[0].trim()+"/"+statseparated[1].trim()+"/"+statseparated[2].trim());

                        int sy= Integer.parseInt(statseparated[0].trim());
                        int sm=Integer.parseInt(statseparated[1].trim());
                        int sd=Integer.parseInt(statseparated[2].trim());
                        sm=sm-1;
                        if ( TimeHandler.remaingTimeInMills(TimeHandler.getMillesOfGivenDate(ed,em,ey))>0&&
                                TimeHandler.remaingTimeInMills(TimeHandler.getMillesOfGivenDate(sd,sm,sy))<=0)
                        {
                            filterList.add(jobModle);
                            list.add(jobModle);
                        }
                        else if (TimeHandler.remaingTimeInMills(TimeHandler.getMillesOfGivenDate(ed,em,ey))<=0)
                        {
//                            del expire date
                            delJob(jobModle.getId());

                        }


//                        Collections.sort(list,new AssendingComporater());
                        filterList.clear();
                        filterList.addAll(list);


                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PrograssHandler.hidePrograssDialog();
                Toast.makeText(getApplicationContext(), "check internet", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

    public void backBtn(View view) {
//        admobIntersial.showInsAd();
        finish();
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


    private void delJob(String id)
    {
        String url="https://wzappstech.com/ijobs/delJob.php?id="+id;
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("delcheck",response+"    = "+id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }


    public void JobCata(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
//        admobIntersial.showInsAd();
        super.onBackPressed();
    }
}