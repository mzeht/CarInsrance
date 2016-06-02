package com.wpmac.carinsrance.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BasePreference;
import com.wpmac.carinsrance.base.CarInsuranceActivity;
import com.wpmac.carinsrance.service.AVService;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends CarInsuranceActivity {

    @Bind(R.id.user_name_edittext)
    EditText mUserNameEdittext;
    @Bind(R.id.user_password_edittext)
    EditText mUserPasswordEdittext;
    @Bind(R.id.forget_password_textview)
    TextView mForgetPasswordTextview;
    @Bind(R.id.login_button)
    Button mLoginButton;
    @Bind(R.id.register_textview)
    TextView mRegisterTextview;
    @Bind(R.id.finish_textview)
    TextView mFinishTextview;
    private String fromActivityName;
    private View view;
    private ProgressDialog progressDialog;
    private Timer mTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BasePreference.getInstance().getIsLogin().equals("ture")){

//                    progressDialogDismiss();
                    Intent mainIntent = new Intent(activity,
                            MainActivity.class);
                    startActivity(mainIntent);
                    activity.finish();

        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        fromActivityName=getIntent().getStringExtra("FromActivityName");
        AVAnalytics.trackAppOpened(getIntent());
        AVService.initPushService(this);
        setListener();

    }

    private void setListener() {
        //忘记密码
        mForgetPasswordTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //随便逛逛

        mFinishTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(fromActivityName.equals("SplashActivity")){
//                    Intent intent=new Intent();
//                    intent.setClass(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//                LoginActivity.this.finish();
            }
        });
        //注册
        mRegisterTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //登陆
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = mUserNameEdittext.getText().toString();
                String password = mUserPasswordEdittext.getText().toString();
                if (username.isEmpty()) {
                    showUserNameEmptyError();
                    return;
                }
                if (password.isEmpty()) {
                    showUserPasswordEmptyError();
                    return;
                }
                progressDialogShow();
                AVUser.logInInBackground(username,
                        password,
                        new LogInCallback() {
                            public void done(AVUser user, AVException e) {
                                if (user != null) {
                                    BasePreference.getInstance().saveIsLogin("ture");
                                    BasePreference.getInstance().saveUserName(username);
                                    mTimer = new Timer();
                                    mTimer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            progressDialogDismiss();
                                            Intent mainIntent = new Intent(activity,
                                                    MainActivity.class);
                                            startActivity(mainIntent);
                                            activity.finish();
                                        }
                                    }, 2000);

                                } else {
                                    BasePreference.getInstance().saveIsLogin("false");
                                    BasePreference.getInstance().saveUserName("");
                                    progressDialogDismiss();
                                    showLoginError();
                                }
                            }
                        });
            }
        });

    }

    private void progressDialogDismiss() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    private void progressDialogShow() {
        progressDialog = ProgressDialog
                .show(activity,
                        activity.getResources().getText(
                                R.string.dialog_message_title),
                        activity.getResources().getText(
                                R.string.dialog_text_wait), true, false);
    }
    private void showLoginError() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_error_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.error_login_error))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void showUserPasswordEmptyError() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_error_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.error_register_password_null))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void showUserNameEmptyError() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_error_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.error_register_user_name_null))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

}
