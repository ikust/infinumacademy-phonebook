package co.infinum.phonebook;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ivan on 17/07/15.
 */
@Migration(version = 2, databaseName = PhoneBookDatabase.NAME)
public class SomeMigration extends BaseMigration {

    @Override
    public void migrate(SQLiteDatabase sqLiteDatabase) {





    }
}
