package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public final class ContactContract {
    public static final String TABLE = "contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STREET_TYPE = "street_type";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";


    public static final String[] COLUNS = {ID, NAME, STREET_TYPE, STREET, NEIGHBORHOOD, CITY, STATE};

    private ContactContract() {
        super();
    }

    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(STREET_TYPE + " TEXT NOT NULL, ");
        create.append(STREET + " TEXT NOT NULL, ");
        create.append(NEIGHBORHOOD + " TEXT NOT NULL, ");
        create.append(CITY + " TEXT NOT NULL, ");
        create.append(STATE + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();

        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        values.put(ContactContract.STREET_TYPE, contact.getAddress().getType());
        values.put(ContactContract.STREET, contact.getAddress().getStreet());
        values.put(ContactContract.NEIGHBORHOOD, contact.getAddress().getNeighborhood());
        values.put(ContactContract.CITY, contact.getAddress().getCity());
        values.put(ContactContract.STATE, contact.getAddress().getState());


        return values;

    }

    public static Contact getContact(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();


            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.getAddress().setType(cursor.getString(cursor.getColumnIndex(ContactContract.STREET_TYPE)));
            contact.getAddress().setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
            contact.getAddress().setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
            contact.getAddress().setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));
            contact.getAddress().setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));

            return contact;
        }

        return null;

    }

    public static List<Contact> getContacts(Cursor cursor) {

        Contact contact = new Contact();

        List<Contact> contacts = new ArrayList<>();

        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }

        return contacts;

    }

}
