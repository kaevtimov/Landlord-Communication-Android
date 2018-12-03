package source.kevtimov.landlordcommunicationapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TemplateMessageDatabaseHelper extends SQLiteOpenHelper implements TemplateMessageSQLite{

    private static final String DATABASE_NAME  = "TemplateMessages";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "template_messages";
    private static final String SECOND_COL = "template_message_text";
    //private List<String> mMessages;
    private SQLiteDatabase mDatabase;
    private ContentValues mContentValues;

    public TemplateMessageDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //mMessages = new ArrayList<>();
        mDatabase = this.getWritableDatabase();
        mContentValues = new ContentValues();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " template_message_text TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Cursor getAllTemplateMessages() {
        //mMessages.clear();
        Cursor result = mDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

//        if(result.getCount() > 0){
//            while(result.moveToNext()){
//                mMessages.add(result.getString(1));
//            }
//        }
        return result;
    }

    @Override
    public boolean addTemplateMessage(String message) {
        mContentValues.put(SECOND_COL, message);

        long insertionResult = mDatabase.insert(TABLE_NAME, null, mContentValues);

        return insertionResult != -1;
    }
}
