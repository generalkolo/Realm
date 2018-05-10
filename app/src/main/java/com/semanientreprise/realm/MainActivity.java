package com.semanientreprise.realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.semanientreprise.realm.Model.Profiles;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.profiles_recView)
    RecyclerView profilesRecView;
    @BindView(R.id.get_profiles)
    Button getProfilesBtn;
    private Call<List<Profiles>> profiles;
    Realm realm;
    private RecyclerView.Adapter profilesAdapter;
    private RecyclerView.LayoutManager profilesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        profilesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        profilesRecView.setLayoutManager(profilesLayoutManager);
        profilesRecView.setHasFixedSize(true);
    }

    @OnClick(R.id.get_profiles)
    public void onViewClicked() {
        getProfilesFromServer();
    }

    private void getProfilesFromServer() {

        APISERVICE service = ApiClient.getClient().create(APISERVICE.class);
        profiles = service.getProfiles();

        profiles.enqueue(new Callback<List<Profiles>>() {
            @Override
            public void onResponse(Call<List<Profiles>> call, Response<List<Profiles>> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    final List<Profiles> rowListItem = response.body();

                    int i = 0;
                    while(i < response.body().size()){
                        realm.beginTransaction();

                        Profiles profiles = realm.createObject(Profiles.class);

                        profiles.setName(rowListItem.get(i).getName());
                        profiles.setImage(rowListItem.get(i).getImage());
                        profiles.setDiscoveries(rowListItem.get(i).getDiscoveries());
                        profiles.setDob(rowListItem.get(i).getDob());
                        profiles.setPob(rowListItem.get(i).getPob());
                        profiles.setTitle(rowListItem.get(i).getTitle());

                        realm.commitTransaction();
                        i++;
                    }

                    realm.beginTransaction();

                    final RealmResults<Profiles> results = realm.where(Profiles.class).findAll();

                    realm.commitTransaction();

                    profilesAdapter = new ProfilesAdapter(results, MainActivity.this, new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            getDetailsAndSend(results,position);
                        }
                    });

                    profilesRecView.setVisibility(View.VISIBLE);
                    getProfilesBtn.setVisibility(View.GONE);
                    profilesRecView.setAdapter(profilesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Profiles>> call, Throwable t) {
            }
        });
    }

    private void getDetailsAndSend(RealmResults<Profiles> results, int position) {
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);

        intent.putExtra("Name",results.get(position).getName());
        intent.putExtra("Image",results.get(position).getImage());
        intent.putExtra("Title",results.get(position).getTitle());
        intent.putExtra("Dob",results.get(position).getDob());
        intent.putExtra("Pob",results.get(position).getPob());
        intent.putExtra("Discoveries",results.get(position).getDiscoveries());

        startActivity(intent);
    }
}