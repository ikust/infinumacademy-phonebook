package co.infinum.phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivan on 16/07/15.
 */
public class PhoneBookDatabaseHelper extends SQLiteOpenHelper {

    public PhoneBookDatabaseHelper(Context context) {
        super(context, "PhoneBookDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL commands for creating the database first time it is accessed.
        db.execSQL(
                "CREATE TABLE PhoneBook ("
                        + "id INTEGER PRIMARY KEY, "
                        + "firstName TEXT, "
                        + "lastName TEXT, "
                        + "phone TEXT, "
                        + "mail TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Database migration.
    }
}
