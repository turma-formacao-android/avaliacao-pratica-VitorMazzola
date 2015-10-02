package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.services.PhoneBusinessService;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

public class PhoneFormActivity extends AppCompatActivity{

    private Phone phone;
    private Button buttonSavePhone;
    private EditText editTextNumber;
    private static final String PARAM_PHONE = "PARAM_PHONE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_phone);

        initPhone();
        bindEditTextNumber();
        bindButtonSavePhone();
    }

    private void bindButtonSavePhone() {
        buttonSavePhone = (Button) findViewById(R.id.button_savePhone);
        buttonSavePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = getResources().getString(R.string.msg_required);

                if(!FormHelper.validateRequired(msg, editTextNumber)){
                    bindPhone();
                    PhoneBusinessService.save(phone);
                    Toast.makeText(PhoneFormActivity.this, getString(R.string.msg_save_sucessfull), Toast.LENGTH_SHORT).show();
                    PhoneFormActivity.this.finish();
                }
            }
        });
    }

    private void bindEditTextNumber() {
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextNumber.setText(phone.getNumber() == null ? "" : phone.getNumber());
    }

    private void initPhone(){

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.phone = (Phone) extras.getParcelable(PARAM_PHONE);
        }

        this.phone = this.phone == null ? new Phone(): this.phone;

    }

    private void bindPhone(){
        phone.setNumber(editTextNumber.getText().toString());

    }
}
