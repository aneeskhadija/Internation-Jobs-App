// Generated by view binder compiler. Do not edit!
package com.example.internationaljobs.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.internationaljobs.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NointernetDialougeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnSpinAndWinRedeem;

  @NonNull
  public final LinearLayout dialogParent;

  @NonNull
  public final TextView spinAndWinReward;

  private NointernetDialougeBinding(@NonNull LinearLayout rootView,
      @NonNull Button btnSpinAndWinRedeem, @NonNull LinearLayout dialogParent,
      @NonNull TextView spinAndWinReward) {
    this.rootView = rootView;
    this.btnSpinAndWinRedeem = btnSpinAndWinRedeem;
    this.dialogParent = dialogParent;
    this.spinAndWinReward = spinAndWinReward;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NointernetDialougeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NointernetDialougeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nointernet_dialouge, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NointernetDialougeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSpinAndWinRedeem;
      Button btnSpinAndWinRedeem = ViewBindings.findChildViewById(rootView, id);
      if (btnSpinAndWinRedeem == null) {
        break missingId;
      }

      LinearLayout dialogParent = (LinearLayout) rootView;

      id = R.id.spinAndWinReward;
      TextView spinAndWinReward = ViewBindings.findChildViewById(rootView, id);
      if (spinAndWinReward == null) {
        break missingId;
      }

      return new NointernetDialougeBinding((LinearLayout) rootView, btnSpinAndWinRedeem,
          dialogParent, spinAndWinReward);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}