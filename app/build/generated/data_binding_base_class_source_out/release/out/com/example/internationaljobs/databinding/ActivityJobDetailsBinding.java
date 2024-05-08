// Generated by view binder compiler. Do not edit!
package com.example.internationaljobs.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.internationaljobs.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityJobDetailsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView CompanyTxt;

  @NonNull
  public final TextView DescriptionTxt;

  @NonNull
  public final TextView ExpirydateTxt;

  @NonNull
  public final TextView JobsDetailTxt;

  @NonNull
  public final TextView RequirementsTxt;

  @NonNull
  public final TextView UploadDateTxt;

  @NonNull
  public final ImageView backbtn;

  @NonNull
  public final TextView companyDescription;

  @NonNull
  public final TextView country;

  @NonNull
  public final TextView countryTxt;

  @NonNull
  public final TextView description;

  @NonNull
  public final TextView exdate;

  @NonNull
  public final ImageView imagelog;

  @NonNull
  public final RelativeLayout jobdetFirstlayout;

  @NonNull
  public final LinearLayout jobdetSecondlayout;

  @NonNull
  public final View linetxt1;

  @NonNull
  public final View linetxt2;

  @NonNull
  public final View linetxt3;

  @NonNull
  public final View linetxt4;

  @NonNull
  public final TextView requirements;

  @NonNull
  public final LinearLayout thirdLayoutJobdet;

  @NonNull
  public final TextView title;

  @NonNull
  public final TextView titleTxt;

  @NonNull
  public final TextView update;

  private ActivityJobDetailsBinding(@NonNull LinearLayout rootView, @NonNull TextView CompanyTxt,
      @NonNull TextView DescriptionTxt, @NonNull TextView ExpirydateTxt,
      @NonNull TextView JobsDetailTxt, @NonNull TextView RequirementsTxt,
      @NonNull TextView UploadDateTxt, @NonNull ImageView backbtn,
      @NonNull TextView companyDescription, @NonNull TextView country, @NonNull TextView countryTxt,
      @NonNull TextView description, @NonNull TextView exdate, @NonNull ImageView imagelog,
      @NonNull RelativeLayout jobdetFirstlayout, @NonNull LinearLayout jobdetSecondlayout,
      @NonNull View linetxt1, @NonNull View linetxt2, @NonNull View linetxt3,
      @NonNull View linetxt4, @NonNull TextView requirements,
      @NonNull LinearLayout thirdLayoutJobdet, @NonNull TextView title, @NonNull TextView titleTxt,
      @NonNull TextView update) {
    this.rootView = rootView;
    this.CompanyTxt = CompanyTxt;
    this.DescriptionTxt = DescriptionTxt;
    this.ExpirydateTxt = ExpirydateTxt;
    this.JobsDetailTxt = JobsDetailTxt;
    this.RequirementsTxt = RequirementsTxt;
    this.UploadDateTxt = UploadDateTxt;
    this.backbtn = backbtn;
    this.companyDescription = companyDescription;
    this.country = country;
    this.countryTxt = countryTxt;
    this.description = description;
    this.exdate = exdate;
    this.imagelog = imagelog;
    this.jobdetFirstlayout = jobdetFirstlayout;
    this.jobdetSecondlayout = jobdetSecondlayout;
    this.linetxt1 = linetxt1;
    this.linetxt2 = linetxt2;
    this.linetxt3 = linetxt3;
    this.linetxt4 = linetxt4;
    this.requirements = requirements;
    this.thirdLayoutJobdet = thirdLayoutJobdet;
    this.title = title;
    this.titleTxt = titleTxt;
    this.update = update;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityJobDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityJobDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_job_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityJobDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.CompanyTxt;
      TextView CompanyTxt = ViewBindings.findChildViewById(rootView, id);
      if (CompanyTxt == null) {
        break missingId;
      }

      id = R.id.DescriptionTxt;
      TextView DescriptionTxt = ViewBindings.findChildViewById(rootView, id);
      if (DescriptionTxt == null) {
        break missingId;
      }

      id = R.id.ExpirydateTxt;
      TextView ExpirydateTxt = ViewBindings.findChildViewById(rootView, id);
      if (ExpirydateTxt == null) {
        break missingId;
      }

      id = R.id.JobsDetail_txt;
      TextView JobsDetailTxt = ViewBindings.findChildViewById(rootView, id);
      if (JobsDetailTxt == null) {
        break missingId;
      }

      id = R.id.RequirementsTxt;
      TextView RequirementsTxt = ViewBindings.findChildViewById(rootView, id);
      if (RequirementsTxt == null) {
        break missingId;
      }

      id = R.id.UploadDateTxt;
      TextView UploadDateTxt = ViewBindings.findChildViewById(rootView, id);
      if (UploadDateTxt == null) {
        break missingId;
      }

      id = R.id.backbtn;
      ImageView backbtn = ViewBindings.findChildViewById(rootView, id);
      if (backbtn == null) {
        break missingId;
      }

      id = R.id.companyDescription;
      TextView companyDescription = ViewBindings.findChildViewById(rootView, id);
      if (companyDescription == null) {
        break missingId;
      }

      id = R.id.country;
      TextView country = ViewBindings.findChildViewById(rootView, id);
      if (country == null) {
        break missingId;
      }

      id = R.id.countryTxt;
      TextView countryTxt = ViewBindings.findChildViewById(rootView, id);
      if (countryTxt == null) {
        break missingId;
      }

      id = R.id.description;
      TextView description = ViewBindings.findChildViewById(rootView, id);
      if (description == null) {
        break missingId;
      }

      id = R.id.exdate;
      TextView exdate = ViewBindings.findChildViewById(rootView, id);
      if (exdate == null) {
        break missingId;
      }

      id = R.id.imagelog;
      ImageView imagelog = ViewBindings.findChildViewById(rootView, id);
      if (imagelog == null) {
        break missingId;
      }

      id = R.id.jobdet_firstlayout;
      RelativeLayout jobdetFirstlayout = ViewBindings.findChildViewById(rootView, id);
      if (jobdetFirstlayout == null) {
        break missingId;
      }

      id = R.id.jobdet_secondlayout;
      LinearLayout jobdetSecondlayout = ViewBindings.findChildViewById(rootView, id);
      if (jobdetSecondlayout == null) {
        break missingId;
      }

      id = R.id.linetxt1;
      View linetxt1 = ViewBindings.findChildViewById(rootView, id);
      if (linetxt1 == null) {
        break missingId;
      }

      id = R.id.linetxt2;
      View linetxt2 = ViewBindings.findChildViewById(rootView, id);
      if (linetxt2 == null) {
        break missingId;
      }

      id = R.id.linetxt3;
      View linetxt3 = ViewBindings.findChildViewById(rootView, id);
      if (linetxt3 == null) {
        break missingId;
      }

      id = R.id.linetxt4;
      View linetxt4 = ViewBindings.findChildViewById(rootView, id);
      if (linetxt4 == null) {
        break missingId;
      }

      id = R.id.requirements;
      TextView requirements = ViewBindings.findChildViewById(rootView, id);
      if (requirements == null) {
        break missingId;
      }

      id = R.id.third_layout_jobdet;
      LinearLayout thirdLayoutJobdet = ViewBindings.findChildViewById(rootView, id);
      if (thirdLayoutJobdet == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      id = R.id.titleTxt;
      TextView titleTxt = ViewBindings.findChildViewById(rootView, id);
      if (titleTxt == null) {
        break missingId;
      }

      id = R.id.update;
      TextView update = ViewBindings.findChildViewById(rootView, id);
      if (update == null) {
        break missingId;
      }

      return new ActivityJobDetailsBinding((LinearLayout) rootView, CompanyTxt, DescriptionTxt,
          ExpirydateTxt, JobsDetailTxt, RequirementsTxt, UploadDateTxt, backbtn, companyDescription,
          country, countryTxt, description, exdate, imagelog, jobdetFirstlayout, jobdetSecondlayout,
          linetxt1, linetxt2, linetxt3, linetxt4, requirements, thirdLayoutJobdet, title, titleTxt,
          update);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
