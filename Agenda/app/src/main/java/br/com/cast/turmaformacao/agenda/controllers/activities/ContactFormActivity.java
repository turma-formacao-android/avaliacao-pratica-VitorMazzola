package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.EmailListAdapter;
import br.com.cast.turmaformacao.agenda.controllers.adapters.NetworkListAdapter;
import br.com.cast.turmaformacao.agenda.controllers.adapters.PhoneListAdapter;
import br.com.cast.turmaformacao.agenda.controllers.asyncTask.AddressInterfaceTask;
import br.com.cast.turmaformacao.agenda.controllers.asyncTask.AddressTask;
import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agenda.model.http.AddressService;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.NetworkBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.PhoneBusinessService;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

public class ContactFormActivity extends AppCompatActivity implements AddressInterfaceTask{

    private Spinner spinner;
    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextCity;
    private EditText editTextStreet;
    private EditText editTextType;
    private EditText editTextNeighborhood;
    private EditText editTextState;
    private Button buttonSearch;
    private ImageButton imageButtonAddPhone;
    private ImageButton imageButtonAddNetwork;
    private Contact contact;
    public static final String PARAM_TASK = "PARAM_TASK";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        initContact();
        bindEditTextName();
        bindEditTextZipCode();
        bindEditTextType();
        bindEditTextStreet();
        bindEditTextNeighborhood();
        bindEditTextCity();
        bindEditTextState();
        bindAddPhone();
        bindAddEmail();
        bindAddNetwork();
        bindSpinnerPhone();
        bindSpinnerEmail();
        bindSpinnerNetwork();
        bindButtonSearch();
    }

    private void bindSpinnerPhone() {
        spinner = (Spinner) findViewById(R.id.spinnerPhone);
        onUpdateSpinnerPhoneList();
    }

    private void onUpdateSpinnerPhoneList() {
        List<Phone> phones = PhoneBusinessService.findAll();
        spinner.setAdapter(new PhoneListAdapter(ContactFormActivity.this, phones));
    }

    private void bindSpinnerEmail() {
        spinner = (Spinner) findViewById(R.id.spinnerEmail);
        onUpdateSpinnerEmailList();
    }

    private void onUpdateSpinnerEmailList() {
        List<Email> emails = EmailBusinessService.findAll();
        spinner.setAdapter(new EmailListAdapter(ContactFormActivity.this, emails));
    }

    private void bindSpinnerNetwork() {
        spinner = (Spinner) findViewById(R.id.spinnerSocialNetwork);
        onUpdateSpinnerNetworkList();
    }

    private void onUpdateSpinnerNetworkList() {
        List<SocialNetwork> networks = NetworkBusinessService.findAll();
        spinner.setAdapter(new NetworkListAdapter(ContactFormActivity.this, networks));
    }



    private void bindAddNetwork() {
        imageButtonAddNetwork = (ImageButton) findViewById(R.id.button_saveSocialNetwork);
        imageButtonAddNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNetWork = new Intent(ContactFormActivity.this, NetworkFormActivity.class);
                startActivity(goToNetWork);
            }
        });
    }

    private void bindAddPhone() {
        imageButtonAddPhone = (ImageButton) findViewById(R.id.button_savePhone);
        imageButtonAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPhone = new Intent(ContactFormActivity.this, PhoneFormActivity.class);
                startActivity(goToPhone);
            }
        });

    }

    private void bindAddEmail() {
        ImageButton imageButtonAddEmail = (ImageButton) findViewById(R.id.button_saveEmail);
        imageButtonAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEmail = new Intent(ContactFormActivity.this, EmailFormActivity.class);
                startActivity(goToEmail);
            }
        });

    }

    private void bindAddNetWork() {
        ImageButton imageButtonAddNetwork = (ImageButton) findViewById(R.id.button_saveSocialNetwork);
        imageButtonAddNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNetWork = new Intent(ContactFormActivity.this, NetworkFormActivity.class);
                startActivity(goToNetWork);
            }
        });

    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        Contact contact = null;

        if (extras != null) {
            contact = extras.getParcelable(PARAM_TASK);
        }

        this.contact = contact == null ? new Contact() : contact;
    }

    private void bindContact() {
        contact.setName(editTextName.getText().toString());
        contact.getAddress().setZipCode(editTextZipCode.getText().toString());
        contact.getAddress().setType(editTextType.getText().toString());
        contact.getAddress().setStreet(editTextStreet.getText().toString());
        contact.getAddress().setNeighborhood(editTextNeighborhood.getText().toString());
        contact.getAddress().setCity(editTextCity.getText().toString());
        contact.getAddress().setState(editTextState.getText().toString());
    }


    private void bindEditTextState() {
        editTextState = (EditText) findViewById(R.id.editTextState);
        editTextState.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());
    }

    private void bindEditTextCity() {
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextCity.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());
    }

    private void bindEditTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextNeighborhood.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());
    }

    private void bindEditTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextStreet.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());
    }

    private void bindEditTextType() {
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextType.setText(contact.getAddress().getType() == null ? "" : contact.getAddress().getType());

    }

    private void bindEditTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
        editTextZipCode.setText(contact.getAddress().getZipCode() == null ? "" : contact.getAddress().getZipCode());
    }



    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zipCode = editTextZipCode.getText().toString();
                new AddressTask(ContactFormActivity.this,ContactFormActivity.this).execute(zipCode);
                ;
            }
        });
    }

    private void bindEditTextName(){
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(contact.getName() == null ? "" : contact.getName());
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

        String msg = getResources().getString(R.string.msg_required);

        if (!FormHelper.validateRequired(msg, editTextName)) {
            bindContact();
            ContactBusinessService.save(contact);
            Toast.makeText(ContactFormActivity.this, "Contact save sucessfull", Toast.LENGTH_SHORT).show();
            ContactFormActivity.this.finish();
        }
    }

    @Override
    public void afterSearchZipCode(Address address) {

        if (address == null) {
            Toast.makeText(this, "Cep não encontrado", Toast.LENGTH_SHORT).show();
            FormHelper.clearEditTextFields(editTextCity, editTextType, editTextNeighborhood, editTextState, editTextStreet, editTextZipCode);
            return;
        }

        contact.setAddress(address);
        editTextZipCode.setText(address.getZipCode());
        editTextType.setText(address.getType());
        editTextStreet.setText(address.getStreet());
        editTextCity.setText(address.getCity());
        editTextState.setText(address.getState());
        editTextNeighborhood.setText(address.getNeighborhood());

    }


}
