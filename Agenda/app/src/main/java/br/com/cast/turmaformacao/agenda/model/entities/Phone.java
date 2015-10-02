package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable{
    private Integer id;
    private String number;
    private Contact contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", contact=" + contact +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.number == null ? "" : this.number);
        dest.writeParcelable(this.contact,flags);
    }

    public Phone() {
        contact = new Contact();
    }

    protected Phone(Parcel in) {
        Integer id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = id == -1 ? null : id;
        this.number = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
