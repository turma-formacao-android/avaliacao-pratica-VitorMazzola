package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;


public final class PhoneRepository {
    private PhoneRepository() {
        super();
    }

    public static void save(Phone phone) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = PhoneContract.getContentValues(phone);

        if (phone.getId() == null) {

            db.insert(PhoneContract.TABLE, null, values);

        } else {

            String where = PhoneContract.ID + " = ? ";
            String[] params = {phone.getId().toString()};
            db.update(PhoneContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = PhoneContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(PhoneContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Phone> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(PhoneContract.TABLE, PhoneContract.COLUNS, null, null, null, null, PhoneContract.ID);
        List<Phone> values = PhoneContract.getPhones(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }
}
