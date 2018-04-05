package com.example.valentina.valentina_libruary.RealmObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Valentina on 01.04.2018.
 */

abstract class DbHelper {
    protected void config(){
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("mySuperRealm.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
