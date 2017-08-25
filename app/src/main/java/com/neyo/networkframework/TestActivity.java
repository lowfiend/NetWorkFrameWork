package com.neyo.networkframework;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.neyo.networkframework.common.CustomProgress;
import com.neyo.networkframework.model.User;
import com.neyo.networkframework.network.BackendService;
import com.neyo.networkframework.network.Backend_API;
import com.neyo.networkframework.network.RequestListener;

import static com.neyo.networkframework.common.GlobalConstant.AUTHOR;

public class TestActivity extends AppCompatActivity {

    private TextView resultTv;
    private Dialog dialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        getNonUser();
    }

    public void initView() {
        resultTv = (TextView) findViewById(R.id.hello);
        dialog = CustomProgress.build(this, getString(R.string.progress_loading), true, null);
    }

    public void getNonUser() {
        dialog.show();
        Backend_API.getInstance().getUser(getUser, AUTHOR + "1");
    }

    public void getUser() {
        dialog.show();
        Backend_API.getInstance().getUser(getUser, AUTHOR);
    }

    private BackendService getUser = new BackendService(this, new RequestListener<User>() {
        @Override public void onSuccess(User user) {
            resultTv.setText(user.getName() + "\n" +user.getEmail() + "\n" + user.getCreated_at());
            dialog.dismiss();
        }

        @Override public void onError(String code, String errorMsg) {
            resultTv.setText("onError: code:" + code + "  errorMsg:" + errorMsg);
            dialog.dismiss();
        }
    });

    public void test(View v) {
        getUser();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TestActivity.class);
        activity.startActivity(intent);
    }
}
