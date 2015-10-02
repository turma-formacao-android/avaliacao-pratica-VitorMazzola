package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetwork;

public final class NetworkRepository {
    private NetworkRepository() {
        super();
    }

    public static void save(SocialNetwork socialNetwork) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = NetworkContract.getContentValues(socialNetwork);

        if (socialNetwork.getId() == null) {

            db.insert(NetworkContract.TABLE, null, values);

        } else {

            String where = NetworkContract.ID + " = ? ";
            String[] params = {socialNetwork.getId().toString()};
            db.update(NetworkContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = NetworkContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(NetworkContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<SocialNetwork> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(NetworkContract.TABLE, NetworkContract.COLUNS, null, null, null, null, NetworkContract.ID);
        List<SocialNetwork> values = NetworkContract.getNetworks(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }
}
