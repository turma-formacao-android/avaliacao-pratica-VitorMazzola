package br.com.cast.turmaformacao.agenda.controllers.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agenda.model.services.NetworkBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.PhoneBusinessService;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

public class NetworkFormActivity extends AppCompatActivity{

    private SocialNetwork network;
    private Button buttonSaveNetWork;
    private EditText editTextNetwork;
    private static final String PARAM_NETWORK = "PARAM_NETWORK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_network);

        initNetwork();
        bindEditTextNetWork();
        bindButtonSaveNetwork();
    }

    private void bindButtonSaveNetwork() {
        buttonSaveNetWork = (Button) findViewById(R.id.button_saveSocialNetwork);
        buttonSaveNetWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = getResources().getString(R.string.msg_required);

                if(!FormHelper.validateRequired(msg, editTextNetwork)){
                    bindNetwork();
                    NetworkBusinessService.save(network);
                    Toast.makeText(NetworkFormActivity.this, getString(R.string.msg_save_sucessfull), Toast.LENGTH_SHORT).show();
                    NetworkFormActivity.this.finish();
                }
            }
        });
    }

    private void bindEditTextNetWork() {
        editTextNetwork = (EditText) findViewById(R.id.editTextSocial);
        editTextNetwork.setText(network.getName() == null ? "" : network.getName());
    }

    private void initNetwork(){

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.network = (SocialNetwork) extras.getParcelable(PARAM_NETWORK);
        }

        this.network = this.network == null ? new SocialNetwork(): this.network;

    }

    private void bindNetwork(){
        network.setName(editTextNetwork.getText().toString());

    }
}
