package com.neyo.networkframework;

import android.app.Dialog;
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

public class MainActivity extends AppCompatActivity {

    private TextView resultTv;
    private Dialog dialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getName();
    }

    public void initView() {
        resultTv = (TextView) findViewById(R.id.hello);
        dialog = CustomProgress.build(this, getString(R.string.progress_loading), true, null);
    }

    public void getName() {
        dialog.show();
        Backend_API.getInstance().getEamil(getName, AUTHOR);
    }

    private BackendService getName = new BackendService(this, new RequestListener<User>() {
        @Override public void onSuccess(User user) {
            resultTv.setText(user.getName());
            dialog.dismiss();
        }

        @Override public void onError(String code, String errorMsg) {
            resultTv.setText("onError: code:" + code + "  errorMsg:" + errorMsg);
            dialog.dismiss();
        }
    });

    public void test(View v) {
        TestActivity.launch(this);
    }

}
