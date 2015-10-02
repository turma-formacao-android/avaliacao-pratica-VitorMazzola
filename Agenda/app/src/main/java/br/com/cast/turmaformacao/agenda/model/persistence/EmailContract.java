package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */
public final class EmailContract {
    public static final String TABLE = "email";
    public static final String ID = "id";
    public static final String EMAIL = "email";

    public static final String[] COLUNS = {ID, EMAIL};

    private EmailContract() {
        super();
    }

    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(EMAIL + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Email email) {
        ContentValues values = new ContentValues();

        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAIL, email.getEmail());

        return values;
    }

    public static Email getEmail(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();


            email.setId(cursor.getInt(cursor.getColumnIndex(EmailContract.ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));


            return email;
        }

        return null;

    }

    public static List<Email> getEmails(Cursor cursor) {

        Email email = new Email();

        List<Email> emails = new ArrayList<>();

        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }

        return emails;

    }
}
