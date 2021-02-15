package com.softwarez.technocrew.esabhsad.adminActivityClass;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.softwarez.technocrew.esabhsad.R;

public class SamsyeActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samsye);
        toolbar=findViewById(R.id.toolbar);
        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("समस्याये");
        setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         
    }

}