package com.example.vypt.demorealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
public class MainActivity extends AppCompatActivity {
    Realm myRealm;
    Button b_view, b_fil, b_order, b_add;
    TextView tv;
    EditText et_name, et_pop;
    int x = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_add = (Button) findViewById(R.id.b_add);
        b_fil = (Button) findViewById(R.id.b_fil);
        b_order = (Button) findViewById(R.id.b_order);
        b_view = (Button) findViewById(R.id.b_view);
        tv = (TextView) findViewById(R.id.tv);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pop = (EditText) findViewById(R.id.et_pop);

        myRealm = Realm.getInstance(this);
        // cach 1
        myRealm.beginTransaction();

        Country country1 = myRealm.createObject(Country.class);
        country1.setName("Viet Nam");
        country1.setCode("1");
        country1.setPopulation(10000000);

        Country country3 = myRealm.createObject(Country.class);
        country3.setName("TQ");
        country3.setCode("2");
        country3.setPopulation(100000000);

        Country country4 = myRealm.createObject(Country.class);
        country4.setName("Canada");
        country4.setCode("3");
        country4.setPopulation(30000000);

        myRealm.commitTransaction();
        //cach 2
        Country country2 = new Country();
        country2.setName("USA");
        country2.setCode("4");
        country2.setPopulation(50000000);

        myRealm.beginTransaction();
        Country copyofCountry2 = myRealm.copyToRealm(country2);
        myRealm.commitTransaction();

        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x+1;
                myRealm.beginTransaction();
                Country country = myRealm.createObject(Country.class);
                country.setName(et_name.getText().toString());
                country.setCode(String.valueOf(x));
                country.setPopulation(Integer.valueOf(et_pop.getText().toString()));
                myRealm.commitTransaction();
                Toast.makeText(MainActivity.this, "Add successful",
                        Toast.LENGTH_LONG).show();
            }
        });

        b_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Country> results1 =
                        myRealm.where(Country.class).findAll();
                tv.setText(results1.toString());
            }
        });

        b_fil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Country> results2 =
                        myRealm.where(Country.class).greaterThan("population",50000000).findAll();
                tv.setText(results2.toString());
            }
        });

        b_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Country> results3 =
                        myRealm.where(Country.class).findAllSorted("name", true);
                tv.setText(results3.toString());
            }
        });

    }
}
