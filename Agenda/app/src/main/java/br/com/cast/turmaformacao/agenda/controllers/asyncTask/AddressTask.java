package br.com.cast.turmaformacao.agenda.controllers.asyncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.http.AddressService;


public final class AddressTask extends AsyncTask<String,Void,Address>{

    Activity activity;
    AddressInterfaceTask addressInterface;
    ProgressDialog progressDialog;

    public AddressTask(Activity activity,AddressInterfaceTask addressInterface){
        this.activity = activity;
        this.addressInterface = addressInterface;
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Searching address by zipcode...");
    }

    @Override
    protected Address doInBackground(String... strings) {

        Address address = AddressService.getAdressByZipCode(strings[0]);
        return address;
    }

    @Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);
        addressInterface.afterSearchZipCode(address);
    }
}
