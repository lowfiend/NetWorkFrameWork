package com.neyo.networkframework.common;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import com.google.gson.Gson;
import com.neyo.networkframework.R;

/**
 * Created by Neyo on 2017/8/24.
 */

public class Utility {

    public static void appDebugLog(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void appDebugLog(String tag, Object msg) {
        Log.d(tag, new Gson().toJson(msg));
    }

    public static void showCommonDialog(Context context, String title, String msg) {
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.g_ok, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            dialogInterface.dismiss();
                        } catch (Exception ignored) {
                        }
                    }
                })
                .create();
        dialog.show();
    }
}
