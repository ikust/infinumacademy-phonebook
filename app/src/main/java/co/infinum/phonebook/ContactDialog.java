package co.infinum.phonebook;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ivan on 28/06/15.
 */
public class ContactDialog extends DialogFragment {

    private static final String TITLE_KEY = "title";

    private static final String CONTACT_KEY = "contact";

    public interface UpdateContactListener {

        void onSave(Contact item);

        void onCancel(Contact item);
    }

    // Use this instance of the interface to deliver action events
    UpdateContactListener listener;

    @InjectView(R.id.et_first_name)
    EditText firstNameEditText;

    @InjectView(R.id.et_last_name)
    EditText lastNameEditText;

    @InjectView(R.id.et_phone)
    EditText phoneEditText;

    @InjectView(R.id.et_email)
    EditText emailEditText;

    public static ContactDialog newInstance(String title) {
        return newInstance(title, null);
    }

    public static ContactDialog newInstance(String title, Contact contact) {
        ContactDialog fragment = new ContactDialog();
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog_Alert);
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putSerializable(CONTACT_KEY, contact);

        fragment.setArguments(args);
        return fragment;
    }

    public ContactDialog setListener(UpdateContactListener listener) {
        this.listener = listener;
        return this;
    }

    private Contact getContact() {
        Contact contact = (Contact) getArguments().getSerializable(CONTACT_KEY);
        if(contact == null) {
            contact = new Contact();
        }

        contact.setFirstName(firstNameEditText.getText().toString());
        contact.setLastName(lastNameEditText.getText().toString());
        contact.setNumber(phoneEditText.getText().toString());
        contact.setMail(emailEditText.getText().toString());

        return contact;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View contentView = getActivity().getLayoutInflater().inflate(R.layout.dialog_contact, null);
        ButterKnife.inject(this, contentView);

        Contact contact = (Contact) getArguments().getSerializable(CONTACT_KEY);
        if(contact != null) {
            firstNameEditText.setText(contact.getFirstName());
            lastNameEditText.setText(contact.getLastName());
            phoneEditText.setText(contact.getNumber());
            emailEditText.setText(contact.getMail());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getArguments().getString(TITLE_KEY))
                .setView(contentView)
                .setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(listener != null) {
                            listener.onSave(getContact());
                        }
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(listener != null) {
                            listener.onCancel(getContact());
                        }
                    }
                });

        return builder.create();
    }
}
