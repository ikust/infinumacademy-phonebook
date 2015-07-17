package co.infinum.phonebook;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by ivan on 17/07/15.
 */
public class DBFlowPhoneBook implements PhoneBook {

    @Override
    public List<Contact> getContacts() {
        return new Select().from(Contact.class).queryList();
    }

    @Override
    public void addContact(Contact contact) {
        contact.save();

//        Insert.into(Contact.class).columnValues(
//                Condition.column(Contact$Table.FIRSTNAME).eq(contact.getFirstName()),
//                Condition.column(Contact$Table.LASTNAME).eq(contact.getLastName()),
//                Condition.column(Contact$Table.PHONE).eq(contact.getNumber()),
//                Condition.column(Contact$Table.MAIL).eq(contact.getMail())
//        ).queryClose();
    }

    @Override
    public void updateContact(Contact contact) {
        contact.update();

//        Update.table(Contact.class).set(
//                Condition.column(Contact$Table.FIRSTNAME).eq(contact.getFirstName()),
//                Condition.column(Contact$Table.LASTNAME).eq(contact.getLastName()),
//                Condition.column(Contact$Table.PHONE).eq(contact.getNumber()),
//                Condition.column(Contact$Table.MAIL).eq(contact.getMail())
//        ).where(Condition.column(Contact$Table.ID).eq(contact.getId())).queryClose();
    }

    @Override
    public void removeContact(Contact contact) {
        contact.delete();

//        new Delete().from(Contact.class).where(Condition.column(Contact$Table.ID).eq(contact.getId())).queryClose();
    }
}
