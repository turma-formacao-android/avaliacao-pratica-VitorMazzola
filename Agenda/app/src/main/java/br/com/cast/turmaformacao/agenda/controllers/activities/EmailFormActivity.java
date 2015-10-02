package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.services.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.PhoneBusinessService;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

public class EmailFormActivity extends AppCompatActivity{

    private Email email;
    private Button buttonSaveEmail;
    private EditText editTextEmail;
    private static final String PARAM_EMAIL = "PARAM_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_email);

        initEmail();
        bindEditTextEmail();
        bindButtonSaveEmail();
    }

    private void bindButtonSaveEmail() {
        buttonSaveEmail = (Button) findViewById(R.id.button_saveEmail);
        buttonSaveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = getResources().getString(R.string.msg_required);

                if(!FormHelper.validateRequired(msg, editTextEmail)){
                    bindEmail();
                    EmailBusinessService.save(email);
                    Toast.makeText(EmailFormActivity.this, getString(R.string.msg_save_sucessfull), Toast.LENGTH_SHORT).show();
                    EmailFormActivity.this.finish();
                }
            }
        });
    }

    private void bindEditTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.editTextNumber);
        editTextEmail.setText(email.getEmail() == null ? "" : email.getEmail());
    }

    private void initEmail(){

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.email = (Email) extras.getParcelable(PARAM_EMAIL);
        }

        this.email = this.email == null ? new Email(): this.email;

    }

    private void bindEmail(){
        email.setEmail(editTextEmail.getText().toString());

    }
}
