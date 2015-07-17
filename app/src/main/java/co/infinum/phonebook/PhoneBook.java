package co.infinum.phonebook;

import java.util.List;

/**
 * Created by ivan on 16/07/15.
 */
public interface PhoneBook {

    List<Contact> getContacts();

    void addContact(Contact contact);

    void updateContact(Contact contact);

    void removeContact(Contact contact);
}
