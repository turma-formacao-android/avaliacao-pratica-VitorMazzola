package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetwork;

public final class NetworkContract {
    public static final String TABLE = "phone";
    public static final String ID = "id";
    public static final String NAME = "name";

    public static final String[] COLUNS = {ID, NAME};

    private NetworkContract() {
        super();
    }

    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(SocialNetwork socialNetwork) {
        ContentValues values = new ContentValues();

        values.put(NetworkContract.ID, socialNetwork.getId());
        values.put(NetworkContract.NAME, socialNetwork.getName());

        return values;
    }

    public static SocialNetwork getNetwork(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            SocialNetwork socialNetwork = new SocialNetwork();


            socialNetwork.setId(cursor.getInt(cursor.getColumnIndex(NetworkContract.ID)));
            socialNetwork.setName(cursor.getString(cursor.getColumnIndex(NetworkContract.NAME)));


            return socialNetwork;
        }

        return null;

    }

    public static List<SocialNetwork> getNetworks(Cursor cursor) {

        SocialNetwork socialNetwork = new SocialNetwork();

        List<SocialNetwork> networks = new ArrayList<>();

        while (cursor.moveToNext()) {
            networks.add(getNetwork(cursor));
        }

        return networks;

    }
}
