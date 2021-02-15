package com.softwarez.technocrew.esabhsad.adminActivityClass;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.softwarez.technocrew.esabhsad.AllConstants;
import com.softwarez.technocrew.esabhsad.LoginPrefrence;
import com.softwarez.technocrew.esabhsad.R;
import com.softwarez.technocrew.esabhsad.prefrence.SharedPrefManager;

public class VerifyOTPActivity extends AppCompatActivity {
    private EditText inputCode1,inputCode2,inputCode3,inputCode4,inputCode5,inputCode6;
    TextView txtMobile,txtResendOTP;
    ProgressBar progressBar;
    Button btnLogin;
    String verificationId;
   LoginPrefrence loginPrefrence;
    SharedPreferences spf;
    SharedPreferences.Editor editor;
    private boolean isUserLoggedIn;
    FirebaseAuth auth;
    String number;
    TextInputLayout inputLayoutMobile;
    TextInputEditText OTPVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        txtMobile=findViewById(R.id.txtMobile);
        inputLayoutMobile=findViewById(R.id.inputLayoutMobile);
        OTPVerify=findViewById(R.id.etdVerifyOTP);
        auth=FirebaseAuth.getInstance();
        txtMobile.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));
       /* inputCode1=findViewById(R.id.inputCode1);
        inputCode2=findViewById(R.id.inputCode2);
        inputCode3=findViewById(R.id.inputCode3);
        inputCode4=findViewById(R.id.inputCode4);
        inputCode5=findViewById(R.id.inputCode5);
        inputCode6=findViewById(R.id.inputCode6);*/
        progressBar=findViewById(R.id.progressBar);
        btnLogin=findViewById(R.id.btnLogin);
       // txtResendOTP=findViewById(R.id.txtResendOTP);
       loginPrefrence=new  LoginPrefrence(this);
        verificationId=getIntent().getStringExtra("verificationId");
        number=getIntent().getStringExtra("mobile");
        spf=getSharedPreferences(AllConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor=spf.edit();
        String num=spf.getString(AllConstants.KEY_Number,"number");
        Log.e("verifynumber",""+num );

        //setupOTPInputs();
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (OTPVerify.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOTPActivity.this, "please enter valid code", Toast.LENGTH_SHORT).show();
                     return;
                }
               /* String code=inputCode1.getText().toString()+
                           inputCode2.getText().toString()+
                           inputCode3.getText().toString()+
                           inputCode4.getText().toString()+
                           inputCode5.getText().toString()+
                           inputCode6.getText().toString();*/
                String code=OTPVerify.getText().toString();

                if (verificationId !=null) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.INVISIBLE);
                    loginPrefrence.setLoggedIn(true);
                   loginPrefrence.setAuthToken(verificationId);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    btnLogin.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                       // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                        finish();
                                        editor.putString(AllConstants.KEY_Number,number);
                                       editor.commit();
                                        Log.e("verify number ",""+number);

                                    } else {
                                        Toast.makeText(VerifyOTPActivity.this, "The Verification code entered was invalid ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


/*        txtResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS, VerifyOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               newVerificationId=newVerificationId;
                                Toast.makeText(VerifyOTPActivity.this, "OTP Sent", Toast.LENGTH_SHORT).show();

                            }
                        }
                );


            }
        });*/

    }
/*    private void setupOTPInputs(){
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/
}