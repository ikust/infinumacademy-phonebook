package co.infinum.phonebook;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACT_DIALOG = "contact-dialog";

    private ContactAdapter adapter;

    private PhoneBook phoneBook;

    @InjectView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        phoneBook = new SQLitePhoneBook(this);

        adapter = new ContactAdapter(this, new ArrayList<Contact>());
        listView.setAdapter(adapter);

        adapter.addAll(phoneBook.getContacts());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void reload() {
        adapter.clear();
        adapter.addAll(phoneBook.getContacts());
    }

    @OnClick(R.id.tv_new_contact)
    public void onNewContactClick() {
        ContactDialog.newInstance(getString(R.string.add_contact))
                .setListener(new ContactDialog.UpdateContactListener() {
                    @Override
                    public void onSave(Contact item) {
                        phoneBook.addContact(item);
                        reload();
                    }

                    @Override
                    public void onCancel(Contact item) {

                    }
                })
                .show(getSupportFragmentManager(), CONTACT_DIALOG);
    }

    @OnItemClick(R.id.list_view)
    public void onItemClick(int position) {
        ContactDialog.newInstance("Edit contact", adapter.getItem(position))
                .setListener(new ContactDialog.UpdateContactListener() {
                    @Override
                    public void onSave(Contact item) {
                        phoneBook.updateContact(item);
                        reload();
                    }

                    @Override
                    public void onCancel(Contact item) {

                    }
                })
                .show(getSupportFragmentManager(), CONTACT_DIALOG);
    }

    @OnItemLongClick(R.id.list_view)
    public boolean onItemLongClick(final int position) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.delete_contact))
                .setMessage(getString(R.string.delete_contact_message))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phoneBook.removeContact(adapter.getItem(position));
                        reload();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();

        return true;
    }

}
