package com.example.internationaljobs.Handlers;

import android.app.ProgressDialog;
import android.content.Context;

public class PrograssHandler {
    private static ProgressDialog progressDialog;

    public static void showPrograssDialog(String msg, Context context)
    {
        if (progressDialog==null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.show();
        }
    }
    public static void hidePrograssDialog()
    {
        if (progressDialog!=null)
        {
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }

}
