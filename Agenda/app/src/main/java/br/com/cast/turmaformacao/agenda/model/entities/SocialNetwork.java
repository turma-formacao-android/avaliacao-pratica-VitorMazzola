package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class SocialNetwork implements Parcelable{
    private Integer id;
    private String name;
    private Contact contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeParcelable(this.contact, flags);
    }

    public SocialNetwork() {
        contact = new Contact();
    }

    protected SocialNetwork(Parcel in) {
        Integer id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = id == -1 ? null : id;
        this.name = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());

    }

    public static final Parcelable.Creator<SocialNetwork> CREATOR = new Parcelable.Creator<SocialNetwork>() {
        public SocialNetwork createFromParcel(Parcel source) {
            return new SocialNetwork(source);
        }

        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    };
}
