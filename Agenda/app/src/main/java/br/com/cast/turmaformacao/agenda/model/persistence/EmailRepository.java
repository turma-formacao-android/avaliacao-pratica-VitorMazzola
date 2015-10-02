package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */
public final class EmailRepository {

    private EmailRepository() {
        super();
    }

    public static void save(Email email) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = EmailContract.getContentValues(email);

        if (email.getId() == null) {

            db.insert(EmailContract.TABLE, null, values);

        } else {

            String where = EmailContract.ID + " = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = EmailContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(EmailContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Email> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, null, null, null, null, EmailContract.ID);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }
}
