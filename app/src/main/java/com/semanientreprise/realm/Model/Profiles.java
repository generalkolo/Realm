package com.semanientreprise.realm.Model;

import io.realm.RealmObject;

/**
 * Created by GeneralKolo on 2018/05/04.
 */

public class Profiles extends RealmObject{
    String name;
    String image;
    String title;
    String dob;
    String pob;
    String discoveries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getDiscoveries() {
        return discoveries;
    }

    public void setDiscoveries(String discoveries) {
        this.discoveries = discoveries;
    }
}
