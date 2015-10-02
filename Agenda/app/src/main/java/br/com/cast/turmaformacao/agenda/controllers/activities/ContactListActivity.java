package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;

public class ContactListActivity extends AppCompatActivity{

    private ListView listViewContactList;
    private Contact selectedContact;
    private List<Contact> getContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        bindContactList();
    }

    private void bindContactList() {
       listViewContactList = (ListView) findViewById(R.id.listViewContactList);
        registerForContextMenu(listViewContactList);
        listViewContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListAdapter adapter = (ContactListAdapter) listViewContactList.getAdapter();
                selectedContact = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_contact, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuUpdateClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuUpdateClick() {
        Intent goToContactForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        goToContactForm.putExtra(ContactFormActivity.PARAM_TASK, selectedContact);
        startActivity(goToContactForm);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ContactListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactBusinessService.delete(selectedContact);
                        selectedContact = null;
                        String message = getString(R.string.msg_delete_sucessfull);
                        onUpdateList();
                        Toast.makeText(ContactListActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onUpdateList();
    }

    private void onUpdateList() {
        List<Contact> values = ContactBusinessService.findAll();
        listViewContactList.setAdapter(new ContactListAdapter(this, values));
        ContactListAdapter adapter = (ContactListAdapter) listViewContactList.getAdapter();
        adapter.notifyDataSetChanged();
    }


    private void onMenuAddClick() {
        Intent goToContactForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        startActivity(goToContactForm);
    }

}
