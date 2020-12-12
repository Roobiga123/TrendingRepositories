package com.example.repo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.repo.Activity.MainActivity;
import com.example.repo.R;

public class MyReceiver extends BroadcastReceiver {
    Dialog dialog;
    TextView nettext;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        dialog = new Dialog(context,android.R.style.Theme_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.internet_connection);
        Button btn_retry = (Button)dialog.findViewById(R.id.btn_retry);
        nettext =(TextView)dialog.findViewById(R.id.tv_internet_connection);

        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((Activity) context).finish();

                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });

        if(status.isEmpty()||status.equals("No internet is available")||status.equals("No Internet Connection")) {
            status="No Internet Connection";
          dialog.show();
        }

    }


}