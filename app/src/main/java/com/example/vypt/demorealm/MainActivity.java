package com.example.vypt.demorealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
public class MainActivity extends AppCompatActivity {
    Realm myRealm;
    Button b1, b2, b3;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.b_1);
        b2 = (Button) findViewById(R.id.b_2);
        b3 = (Button) findViewById(R.id.b_3);
        tv = (TextView) findViewById(R.id.tv);

        myRealm = Realm.getInstance(this);
        // cach 1
        myRealm.beginTransaction();

        Country country1 = myRealm.createObject(Country.class);
        country1.setName("Viet Nam");
        country1.setCode("1001");
        country1.setPopulation(10000000);

        Country country3 = myRealm.createObject(Country.class);
        country3.setName("TQ");
        country3.setCode("1003");
        country3.setPopulation(100000000);

        Country country4 = myRealm.createObject(Country.class);
        country4.setName("Canada");
        country4.setCode("1004");
        country4.setPopulation(30000000);

        myRealm.commitTransaction();
        //cach 2
        Country country2 = new Country();
        country2.setName("USA");
        country2.setCode("1002");
        country2.setPopulation(50000000);

        myRealm.beginTransaction();
        Country copyofCountry2 = myRealm.copyToRealm(country2);
        myRealm.commitTransaction();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Country> results1 =
                        myRealm.where(Country.class).findAll();
                tv.setText(results1.toString());
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Country> results2 =
                        myRealm.where(Country.class).greaterThan("population",50000000).findAll();
                tv.setText(results2.toString());
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Country> results3 =
                        myRealm.where(Country.class).findAllSorted("name", true);
                tv.setText(results3.toString());
            }
        });

    }
}
