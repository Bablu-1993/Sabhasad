package com.softwarez.technocrew.esabhsad.adminActivityClass;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.softwarez.technocrew.esabhsad.AllConstants;
import com.softwarez.technocrew.esabhsad.R;
import com.softwarez.technocrew.esabhsad.adminAdapterClass.NavigationAdapter;
import com.softwarez.technocrew.esabhsad.adminAdapterClass.RecyclerviewAdminAdapter;
import com.softwarez.technocrew.esabhsad.adminAdapterClass.RecyclerviewAdminDashAdapter;
import com.softwarez.technocrew.esabhsad.model.AdminModel;
import com.softwarez.technocrew.esabhsad.model.DashboardModel;
import com.softwarez.technocrew.esabhsad.prefrence.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView recyclerView_First;
   RecyclerviewAdminAdapter adminAdapter;
    RecyclerviewAdminDashAdapter dashAdapter;
   List<AdminModel> adminModelList;
    List<DashboardModel> modelList;
    RecyclerView.LayoutManager layoutManager1;
    RecyclerView.LayoutManager layoutManager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    TextView txtTitle,txtNumber;
    ListView lisViewDrawer;
    NavigationAdapter navigationAdapter;
    SharedPreferences spf;
    SharedPreferences.Editor editor;
    int [] icon = {R.drawable.sahyogi, R.drawable.sahyogi, R.drawable.sahyogisebat};
    String [] drawerTitle ={"मेरा प्रोफाइल", "गैलरी", "सहयोगी से बात करे"};
   // FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
       recyclerView=findViewById(R.id.recyclerview_admin);
        recyclerView_First=findViewById(R.id.recyclerview_dashboard_admin);
        modelList=new ArrayList<>();
        lisViewDrawer=findViewById(R.id.lisViewDrawer);
        layoutManager1=new LinearLayoutManager(this);
        recyclerView_First.setLayoutManager(layoutManager1);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        drawerLayout=findViewById(R.id.drawerLayout);
        toolbar=findViewById(R.id.toolbar);
        txtTitle=findViewById(R.id.txtTitle);
        txtNumber=findViewById(R.id.txtNumber);
        txtTitle.setText("राम कुमार वर्मा");
        txtNumber.setText("वार्ड नं");
         setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        spf=getSharedPreferences(AllConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor=spf.edit();
        String adminMobile=spf.getString(AllConstants.KEY_Number,null);
        Log.e( "Admin Mobile  ",""+adminMobile);
        adminModelList=new ArrayList<>();
        adminDashboardList();
        addAdminList();
        navigationAdapter=new NavigationAdapter(AdminActivity.this,drawerTitle,icon);
        lisViewDrawer.setAdapter(navigationAdapter);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
       // mAuth = FirebaseAuth.getInstance();
       lisViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if (position==2){
                   FirebaseAuth mAuth = FirebaseAuth.getInstance();
                   try {
                       mAuth.signOut();
                       Intent i=new Intent(AdminActivity.this, SendOTPActivity.class);
                       i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(i);
                       Toast.makeText(AdminActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                   }catch (Exception e) {
                       //Log.e("","onClick: Exception "+e.getMessage(),e );
                   }
               }
           }
       });

    }

    private void adminDashboardList() {
        modelList.add(new DashboardModel(R.drawable.samsay,"समस्याये","100"));
        modelList.add(new DashboardModel(R.drawable.niwaran,"निवारण","132"));
        modelList.add(new DashboardModel(R.drawable.sahyogi,"शेष","10"));
        modelList.add(new DashboardModel(R.drawable.sahyogi,"सहयोगी","20"));
        modelList.add(new DashboardModel(R.drawable.samsay,"निवासी","400"));

        dashAdapter=new RecyclerviewAdminDashAdapter(modelList,this);
        recyclerView_First.setAdapter(dashAdapter);
    }
    private void addAdminList() {
        adminModelList.add(new AdminModel("समस्याये",R.drawable.samsay,R.drawable.rightarrow));
        adminModelList.add(new AdminModel("निवारण",R.drawable.niwaran,R.drawable.rightarrow));
        adminModelList.add(new AdminModel("सहयोगी",R.drawable.sahyogi,R.drawable.rightarrow));

         adminAdapter=new RecyclerviewAdminAdapter(this,adminModelList);
         recyclerView.setAdapter(adminAdapter);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}