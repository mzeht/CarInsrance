package com.wpmac.carinsrance.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.CarInsuranceActivity;
import com.wpmac.carinsrance.service.AVService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wpmac on 16/5/23.
 */
public class RegisterActivity extends CarInsuranceActivity {

    @Bind(R.id.user_name_edittext)
    EditText userNameEdittext;
    @Bind(R.id.user_email_edittext)
    EditText userEmailEdittext;
    @Bind(R.id.user_password_edittext)
    EditText userPasswordEdittext;
    @Bind(R.id.user_password_again_edittext)
    EditText userPasswordAgainEdittext;
    @Bind(R.id.login_button)
    Button RegisterButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void onClick() {

        if (userPasswordEdittext.getText().toString()
                .equals(userPasswordAgainEdittext.getText().toString())) {
            if (!userPasswordEdittext.getText().toString().isEmpty()) {
                if (!userNameEdittext.getText().toString().isEmpty()) {
                    if (!userEmailEdittext.getText().toString().isEmpty()) {
                        progressDialogShow();
                        register();
                    } else {
                        showError(activity
                                .getString(R.string.error_register_email_address_null));
                    }
                } else {
                    showError(activity
                            .getString(R.string.error_register_user_name_null));
                }
            } else {
                showError(activity
                        .getString(R.string.error_register_password_null));
            }
        } else {
            showError(activity
                    .getString(R.string.error_register_password_not_equals));
        }

    }

    private void progressDialogShow() {
        progressDialog = ProgressDialog
                .show(activity,
                        activity.getResources().getText(
                                R.string.dialog_message_title),
                        activity.getResources().getText(
                                R.string.dialog_text_wait), true, false);
    }

    public void register() {
        SignUpCallback signUpCallback = new SignUpCallback() {
            public void done(AVException e) {
                progressDialogDismiss();
                if (e == null) {
                    showRegisterSuccess();
//                    Intent mainIntent = new Intent(activity, MainActivity.class);
//                    startActivity(mainIntent);
                    activity.finish();
                } else {
                    switch (e.getCode()) {
                        case 202:
                            showError(activity
                                    .getString(R.string.error_register_user_name_repeat));
                            break;
                        case 203:
                            showError(activity
                                    .getString(R.string.error_register_email_repeat));
                            break;
                        default:
                            showError(activity
                                    .getString(R.string.network_error));
                            break;
                    }
                }
            }
        };
        String username = userNameEdittext.getText().toString();
        String password = userPasswordEdittext.getText().toString();
        String email = userEmailEdittext.getText().toString();

        AVService.signUp(username, password, email, signUpCallback);
    }
    private void progressDialogDismiss() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
    private void showRegisterSuccess() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_message_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.success_register_success))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }


}
