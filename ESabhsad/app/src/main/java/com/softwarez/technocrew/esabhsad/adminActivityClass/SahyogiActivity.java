package com.softwarez.technocrew.esabhsad.adminActivityClass;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.softwarez.technocrew.esabhsad.R;

public class SahyogiActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahyogi);

        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahyogiActivity.this,AdminActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
    private void init() {
        toolbar=findViewById(R.id.toolbar);
        txtTitle=findViewById(R.id.txtTitle);
        setSupportActionBar(toolbar);
        txtTitle.setText("सहयोगी");
    }
}