package com.example.techolutionatm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ATMViewModel extends AndroidViewModel {

    private ATMRepository repository;
    private LiveData<List<ATMEntity>> atmList;

    public ATMViewModel(@NonNull Application application) {
        super(application);
        repository = new ATMRepository(application);
        atmList = repository.getAtmList();
    }

    LiveData<ATMEntity> getATMEntity(double id){
        return repository.getATMEntity(id);
    }

    LiveData<List<ATMEntity>> getAtmList(){
        return atmList;
    }

    public void insert(ATMEntity entity){
        repository.insert(entity);
    }
}
