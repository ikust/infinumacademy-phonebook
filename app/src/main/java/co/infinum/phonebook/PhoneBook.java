package co.infinum.phonebook;

import java.util.ArrayList;

/**
 * Created by ivan on 16/07/15.
 */
public interface PhoneBook {

    ArrayList<Contact> getContacts();

    void addContact(Contact contact);

    void updateContact(Contact contact);

    void removeContact(Contact contact);
}
