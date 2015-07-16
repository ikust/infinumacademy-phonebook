package co.infinum.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by ivan on 16/07/15.
 */
public class SQLitePhoneBook implements PhoneBook {

    //There should be only one instance.
    private PhoneBookDatabaseHelper databaseHelper;

    public SQLitePhoneBook(Context context) {
        databaseHelper = new PhoneBookDatabaseHelper(context);
    }

    @Override
    public ArrayList<Contact> getContacts() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query("PhoneBook", null, null, null, null, null, null);

        ArrayList<Contact> contacts = new ArrayList<>();

        while(cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex("id")));
            contact.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            contact.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            contact.setNumber(cursor.getString(cursor.getColumnIndex("phone")));
            contact.setMail(cursor.getString(cursor.getColumnIndex("mail")));

            contacts.add(contact);
        }

        cursor.close();
        database.close();

        return contacts;
    }

    @Override
    public void addContact(Contact contact) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", contact.getFirstName());
        contentValues.put("lastName", contact.getLastName());
        contentValues.put("phone", contact.getNumber());
        contentValues.put("mail", contact.getMail());

        contact.setId(database.insert("PhoneBook", null, contentValues));
        database.close();
    }

    @Override
    public void updateContact(Contact contact) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", contact.getFirstName());
        contentValues.put("lastName", contact.getLastName());
        contentValues.put("phone", contact.getNumber());
        contentValues.put("mail", contact.getMail());

        database.update("PhoneBook", contentValues, "id = ?", new String[]{String.valueOf(contact.getId())});
        database.close();
    }

    @Override
    public void removeContact(Contact contact) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        database.delete("PhoneBook", "id = ?", new String[]{String.valueOf(contact.getId())});
        database.close();
    }
}
