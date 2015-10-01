package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.http.AddressService;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

public class ContactFormActivity extends AppCompatActivity{

    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextCity;
    private EditText editTextStreet;
    private EditText editTextType;
    private EditText editTextNeighborhood;
    private EditText editTextState;
    private Button buttonSearch;
    private ProgressDialog progressDialog;


    private class GetAddressTask extends AsyncTask<String, Void, Contact> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ContactFormActivity.this);
            progressDialog.setMessage("Carregando");
            progressDialog.show();
        }

        @Override
        protected Contact doInBackground(String... params) {
            return AddressService.getAddressByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Contact contact) {
            super.onPostExecute(contact);

            editTextCity.setText(contact.getCity());
            editTextNeighborhood.setText(contact.getNeighborhood());
            editTextState.setText(contact.getState());
            editTextStreet.setText(contact.getStreet());
            editTextType.setText(contact.getType());
            editTextZipCode.setText(contact.getZipCode());

            progressDialog.dismiss();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        bindAddress();
        bindButtonSearch();
    }

    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zipCode = editTextZipCode.getText().toString();
                new GetAddressTask().execute(zipCode);
            }
        });
    }


    private void bindAddress() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextState = (EditText) findViewById(R.id.editTextState);
    }

    private void bindAgenda() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_form, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_agenda_add:
                onMenuDoneClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuDoneClick() {

        String requiredMessage = getString(R.string.msg_required);

        if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
            bindAgenda();
            //new ProductSyncTaskSave(this).execute(produto);
        }
    }



}
