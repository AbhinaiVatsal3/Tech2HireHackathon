package com.example.techolutionatm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ATMRepository {
    private ATMDao atmDao;
    private LiveData<List<ATMEntity>> atmList;

    ATMRepository(Application application){
        ATMDatabase database = ATMDatabase.getAtmDatabase(application);
        atmDao = database.atmDao();
        atmList = atmDao.selectAll();
    }

    LiveData<List<ATMEntity>> getAtmList(){
        return atmList;
    }

    LiveData<ATMEntity> getATMEntity(double i){
        return atmDao.selectId(i);
    }

    public void insert(ATMEntity entity){
        new insertATMTask(atmDao).execute(entity);
    }

    static class insertATMTask extends AsyncTask<ATMEntity, Void, Void>{

        private ATMDao atmDao;

        insertATMTask(ATMDao atmDao){
            this.atmDao = atmDao;
        }

        @Override
        protected Void doInBackground(ATMEntity... atmEntities) {
            atmDao.insert(atmEntities[0]);
            return null;
        }
    }

}
