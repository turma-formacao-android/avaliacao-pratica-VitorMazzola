package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */
public class EmailListAdapter extends BaseAdapter{
    private List<Email> emails;
    private Activity context;

    public EmailListAdapter(Activity context,List<Email> emails) {
        this.emails = emails;
        this.context = context;
    }

    @Override
    public int getCount() {
        return emails.size();
    }

    @Override
    public Email getItem(int position) {
        return emails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Email email = getItem(position);
        View view = context.getLayoutInflater().inflate(R.layout.list_item_email, parent, false);


        TextView textViewEmail = (TextView) view.findViewById(R.id.list_item_email);
        textViewEmail.setText(email.getEmail());
        return view;
    }
}
