package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.activities.ContactListActivity;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public final class ContactListAdapter extends BaseAdapter{

    private List<Contact> contactList;
    private Activity context;

    public ContactListAdapter(Activity context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        View contactListItemView = context.getLayoutInflater().inflate(R.layout.activity_item_list, parent, false);


        TextView textViewName = (TextView) contactListItemView.findViewById(R.id.textViewName);
        textViewName.setText(contact.getName());

        TextView textViewStreet = (TextView) contactListItemView.findViewById(R.id.textViewStreet);
        textViewStreet.setText(contact.getAddress().getStreet());

        TextView textViewCity = (TextView) contactListItemView.findViewById(R.id.textViewCity);
        textViewCity.setText(contact.getAddress().getCity());


        return contactListItemView;
    }

    public void setDataValues(List<Contact> values) {
        contactList.clear();
        contactList.addAll(values);
    }
}
