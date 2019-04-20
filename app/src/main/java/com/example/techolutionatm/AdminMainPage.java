package com.example.techolutionatm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import java.util.List;

public class AdminMainPage extends AppCompatActivity {

    RelativeLayout noAtmlayout;
    RecyclerView atmList;
    ATMListAdapter atmListAdapter;
    static boolean admin = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.admin_main_page);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            admin = bundle.getBoolean("Admin");
        }
        noAtmlayout = findViewById(R.id.noAtmLayout);
        atmList = findViewById(R.id.atmList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        atmList.setLayoutManager(layoutManager);
        atmListAdapter = new ATMListAdapter(this);
        atmList.setAdapter(atmListAdapter);
        if(!admin){
            findViewById(R.id.addButton).setVisibility(View.GONE);
            findViewById(R.id.addButton1).setVisibility(View.GONE);
        } else {
            findViewById(R.id.addButton).setVisibility(View.VISIBLE);
        }
        ATMViewModel viewModel = ViewModelProviders.of(this).get(ATMViewModel.class);
        viewModel.getAtmList().observe(this, new Observer<List<ATMEntity>>() {
            @Override
            public void onChanged(@Nullable List<ATMEntity> atmEntities) {
                if(atmEntities != null && atmEntities.size() > 0){
                    atmList.setVisibility(View.VISIBLE);
                    noAtmlayout.setVisibility(View.GONE);
                    atmListAdapter.setAtmEntities(atmEntities);

                } else {
                    atmList.setVisibility(View.GONE);
                    noAtmlayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void addNew(View view) {
        Intent intent = new Intent(this, ATMEditPage.class);
        startActivity(intent);
    }
}
