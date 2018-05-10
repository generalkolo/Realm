package com.semanientreprise.realm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.semanientreprise.realm.Model.Profiles;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GeneralKolo on 2018/03/21.
 */

public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder> {

    private List<Profiles> arrayList;
    private Context context;
    CustomItemClickListener listener;

    public ProfilesAdapter(List<Profiles> arrayList, Context context, CustomItemClickListener listener) {
        this.arrayList = arrayList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ProfilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_profile_view, null);
        final ProfilesViewHolder profilesViewHolder = new ProfilesViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, profilesViewHolder.getAdapterPosition());
            }
        });

        return profilesViewHolder;
    }

    @Override
    public void onBindViewHolder(ProfilesViewHolder holder, int position) {
        holder.profileTitle.setText(arrayList.get(position).getTitle());
        holder.profileName.setText(arrayList.get(position).getName());
        Picasso.with(context).load(arrayList.get(position).getImage())
                .resize(200,200)
                .into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ProfilesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_title) TextView profileTitle;
        @BindView(R.id.profile_name) TextView profileName;
        @BindView(R.id.profile_img) ImageView profileImage;

        public ProfilesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
