package com.semanientreprise.realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dob)
    TextView dob;
    @BindView(R.id.pob)
    TextView pob;
    @BindView(R.id.discoveries)
    TextView discoveries;
    @BindView(R.id.profileImageMain)
    ImageView profileImageMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setProfileDetails();
    }

    private void setProfileDetails() {
        Intent intent = getIntent();

        name.setText(intent.getStringExtra("Name"));
        title.setText(intent.getStringExtra("Title"));
        dob.setText(intent.getStringExtra("Dob"));
        pob.setText(intent.getStringExtra("Pob"));
        discoveries.setText(intent.getStringExtra("Discoveries"));
        Picasso.with(this).load(intent.getStringExtra("Image")).into(profileImageMain);
    }

}