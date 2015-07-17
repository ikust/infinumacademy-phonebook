package co.infinum.phonebook;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ivan on 17/07/15.
 */
@Database(name = PhoneBookDatabase.NAME, version = PhoneBookDatabase.VERSION)
public class PhoneBookDatabase {

    public static final String NAME = "PhoneBookDatabase";

    public static final int VERSION = 1;
}
