package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */
public class PhoneListAdapter extends BaseAdapter{
    private List<Phone> phones;
    private Activity context;

    public PhoneListAdapter(Activity context,List<Phone> phones) {
        this.phones = phones;
        this.context = context;
    }

    @Override
    public int getCount() {
        return phones.size();
    }

    @Override
    public Phone getItem(int position) {
        return phones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Phone phone = getItem(position);
        View view = context.getLayoutInflater().inflate(R.layout.list_item_phone, parent, false);


        TextView textViewNumber = (TextView) view.findViewById(R.id.list_item_Number);
        textViewNumber.setText(phone.getNumber());
        return view;
    }
}
