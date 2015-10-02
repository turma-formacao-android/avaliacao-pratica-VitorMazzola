package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetwork;

/**
 * Created by Administrador on 02/10/2015.
 */
public class NetworkListAdapter extends BaseAdapter{
    private List<SocialNetwork> networks;
    private Activity context;

    public NetworkListAdapter(Activity context,List<SocialNetwork> networks) {
        this.networks = networks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return networks.size();
    }

    @Override
    public SocialNetwork getItem(int position) {
        return networks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SocialNetwork socialNetwork = getItem(position);
        View view = context.getLayoutInflater().inflate(R.layout.list_item_network, parent, false);


        TextView textViewNetwork = (TextView) view.findViewById(R.id.list_item_network);
        textViewNetwork.setText(socialNetwork.getName());
        return view;
    }
}
