package com.example.techolutionatm;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

public class ATMEditPage extends AppCompatActivity {

    EditText bankName, location;
    Switch aSwitch;
    CheckBox deposite , locker;
    String bank, loc, facilities, status;
    double id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atm_edit_layout);
        bankName = findViewById(R.id.bankName);
        location = findViewById(R.id.location);
        aSwitch = findViewById(R.id.statusSwitch);
        deposite = findViewById(R.id.depositeCheck);
        locker = findViewById(R.id.lockerCheck);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getDouble("ID", 0);
        }
        if(id != 0) {
            ATMViewModel viewModel = new ATMViewModel(getApplication());
            viewModel.getATMEntity(id).observe(this, new Observer<ATMEntity>() {
                @Override
                public void onChanged(@Nullable ATMEntity entity) {
                    bankName.setText(entity.associated_Bank);
                    location.setText(entity.location);
                    if("Active".equals(entity.status)){
                        aSwitch.setChecked(true);
                    }else
                        aSwitch.setChecked(false);
                }
            });

        }
    }

    public void saveData(View view) {
        ATMEntity entity = new ATMEntity();
        entity.ATM_Id = Math.random();
        entity.associated_Bank = bankName.getText().toString();
        entity.location = location.getText().toString();
        entity.status = aSwitch.isChecked()?"Active":"Inactive";
        entity.facilities = (deposite.isChecked()?"Deposite machine, ":"")+
                (locker.isChecked()?"locker":"");
        ATMViewModel viewModel = new ATMViewModel(getApplication());
        viewModel.insert(entity);
        this.finish();
    }

    public void goBack(View view) {
        this.finish();
    }
}
