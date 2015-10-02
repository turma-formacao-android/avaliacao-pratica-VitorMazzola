package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */
public final class PhoneContract {
    public static final String TABLE = "phone";
    public static final String ID = "id";
    public static final String NUMBER = "number";

    public static final String[] COLUNS = {ID, NUMBER};

    private PhoneContract() {
        super();
    }

    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NUMBER + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Phone phone) {
        ContentValues values = new ContentValues();

        values.put(PhoneContract.ID, phone.getId());
        values.put(PhoneContract.NUMBER, phone.getNumber());

        return values;
    }

    public static Phone getPhone(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Phone phone = new Phone();


            phone.setId(cursor.getInt(cursor.getColumnIndex(PhoneContract.ID)));
            phone.setNumber(cursor.getString(cursor.getColumnIndex(PhoneContract.NUMBER)));


            return phone;
        }

        return null;

    }

    public static List<Phone> getPhones(Cursor cursor) {

        Phone phone = new Phone();

        List<Phone> phones = new ArrayList<>();

        while (cursor.moveToNext()) {
            phones.add(getPhone(cursor));
        }

        return phones;

    }

}
