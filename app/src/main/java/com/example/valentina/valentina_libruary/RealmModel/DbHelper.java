package com.example.valentina.valentina_libruary.RealmModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Valentina on 01.04.2018.
 */

abstract class DbHelper {
    protected void config(){
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("myPersonalRealm.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
