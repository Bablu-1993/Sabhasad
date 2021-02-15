package com.softwarez.technocrew.esabhsad.adminActivityClass;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.softwarez.technocrew.esabhsad.AllConstants;
import com.softwarez.technocrew.esabhsad.R;
import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {
Button buttonGetOTP;
ProgressBar progressBar;
SharedPreferences spf;
SharedPreferences.Editor editor;
FirebaseAuth auth;
//EditText inputMobile;
String number;
  String inputNumber = " ";
  TextInputLayout inputLayoutMobile ;
  TextInputEditText inputMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);
        inputLayoutMobile=findViewById(R.id.inputLayoutMobile);
        inputMobile=findViewById(R.id.edtTxtMobile);
       // inputMobile=findViewById(R.id.inputMobile);
          buttonGetOTP=findViewById(R.id.btnGetOtp);
         progressBar=findViewById(R.id.progressBar);

        spf=getSharedPreferences(AllConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor=spf.edit();
        //AllProfileList();
        Log.e("send OTP Number", ""+inputNumber);
        //StudentLogin();
        SendOTPMobile();
      /// AdminLogin();

    }

    private void SendOTPMobile() {
        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber = inputMobile.getText().toString();
                Log.e("enter number:", "" + inputNumber);
                if (inputMobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SendOTPActivity.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        SendOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                                inputNumber = inputMobile.getText().toString().trim();
                                Log.e("get Mobile2: ", "" + inputNumber);

                                Intent intent = new Intent(SendOTPActivity.this, VerifyOTPActivity.class);
                                intent.putExtra("mobile", inputNumber);
                                intent.putExtra("verificationId", verificationId);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                editor.putString(AllConstants.KEY_Number, inputNumber);
                                editor.putBoolean(AllConstants.IS_USER_LOGIN, true);
                                editor.commit();

                            }
                        }
                );
            }
        });
    }
/*

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            //overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
        }
    }
*/




}