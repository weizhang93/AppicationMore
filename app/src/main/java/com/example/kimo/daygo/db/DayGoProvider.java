package com.example.kimo.daygo.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.kimo.daygo.util.LogUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/8 0008.
 */
public class DayGoProvider extends ContentProvider {

    private static final String TAG = "DayGoProvider";

    private static final String DATABASE_NAME = "daygo.db";

    private static final int DATABASE_VERSION = 1;

    private static HashMap<String, String> mUsersProjectionMap;


    private static final String[] READ_USER_PROJECTION = new String[]{
            DayGo.Users._ID,
            DayGo.Users.COLUMN_NAME_NAME,
            DayGo.Users.COLUMN_NAME_GENDER,
            DayGo.Users.COLUMN_NAME_EMAIL
    };

    private static final int USERS = 1;

    private static final int USER_ID = 2;

    private DatabaseHelper mOpenHelper;

    private static final UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        mUriMatcher.addURI(DayGo.AUTHORITY, "users", USERS);

        mUriMatcher.addURI(DayGo.AUTHORITY, "users/#", USER_ID);

        mUsersProjectionMap = new HashMap<>();
        mUsersProjectionMap.put(DayGo.Users._ID, DayGo.Users._ID);
        mUsersProjectionMap.put(DayGo.Users.COLUMN_NAME_NAME, DayGo.Users.COLUMN_NAME_NAME);
        mUsersProjectionMap.put(DayGo.Users.COLUMN_NAME_GENDER, DayGo.Users.COLUMN_NAME_GENDER);
        mUsersProjectionMap.put(DayGo.Users.COLUMN_NAME_EMAIL, DayGo.Users.COLUMN_NAME_EMAIL);
        mUsersProjectionMap.put(DayGo.Users.COLUMN_NAME_CREATE_DATE, DayGo.Users.COLUMN_NAME_CREATE_DATE);
        mUsersProjectionMap.put(DayGo.Users.COLUMN_NAME_MODIFY_DATE, DayGo.Users.COLUMN_NAME_MODIFY_DATE);

    }

    static class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DayGo.Users.TABLE_NAME + " ("
                    + DayGo.Users._ID + " INTEGER PRIMARY KEY,"
                    + DayGo.Users.COLUMN_NAME_NAME + " TEXT"
                    + DayGo.Users.COLUMN_NAME_GENDER + " TEXT"
                    + DayGo.Users.COLUMN_NAME_EMAIL + " TEXT"
                    + DayGo.Users.COLUMN_NAME_CREATE_DATE + " INTEGER"
                    + DayGo.Users.COLUMN_NAME_MODIFY_DATE + " INTEGER"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LogUtils.logDebug(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            // Kills the table and existing data
            db.execSQL("DROP TABLE IF EXISTS users");
            //Recreates the database with a new version
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DayGo.Users.TABLE_NAME);

        switch (mUriMatcher.match(uri)) {
            case USERS:
                qb.setProjectionMap(mUsersProjectionMap);
                break;
            case USER_ID:
                qb.setProjectionMap(mUsersProjectionMap);
                qb.appendWhere(
                        DayGo.Users._ID +
                                "=" +
                                uri.getPathSegments().get(DayGo.Users.USER_ID_PATH_POSITION)
                );
                break;
            default:
                // If the URI doesn't match any of the known patterns, throw an exception.
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        String orderBy;
        // If no sort order is specified, uses the default
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = DayGo.Users.DEFAULT_SORT_ORDER;
        } else {
            // otherwise, uses the incoming sort order
            orderBy = sortOrder;
        }

        // Opens the database object in "read" mode, since no writes need to be done.
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor c = qb.query(
                db,            // The database to query
                projection,    // The columns to return from the query
                selection,     // The columns for the where clause
                selectionArgs, // The values for the where clause
                null,          // don't group the rows
                null,          // don't filter by row groups
                orderBy        // The sort order
        );

        // Tells the Cursor what URI to watch, so it knows when its source data changes
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        /**
         * Chooses the MIME type based on the incoming URI pattern
         */
        switch (mUriMatcher.match(uri)) {

            // If the pattern is for notes or live folders, returns the general content type.
            case USERS:
                //case LIVE_FOLDER_NOTES:
                return DayGo.Users.CONTENT_TYPE;

            // If the pattern is for note IDs, returns the note ID content type.
            case USER_ID:
                return DayGo.Users.CONTENT_ITEM_TYPE;

            // If the URI pattern doesn't match any permitted patterns, throws an exception.
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Validates the incoming URI. Only the full provider URI is allowed for inserts.
        if (mUriMatcher.match(uri) != USERS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // A map to hold the new record's values.
        ContentValues val;

        if (values != null) {
            val = new ContentValues(values);
        } else {
            val = new ContentValues();
        }

        // Gets the current system time in milliseconds
        Long now = Long.valueOf(System.currentTimeMillis());

        // If the values map doesn't contain the creation date, sets the value to the current time.
        if (val.containsKey(DayGo.Users.COLUMN_NAME_CREATE_DATE) == false) {
            values.put(DayGo.Users.COLUMN_NAME_CREATE_DATE, now);
        }

        // If the values map doesn't contain the modification date, sets the value to the current
        // time.
        if (val.containsKey(DayGo.Users.COLUMN_NAME_MODIFY_DATE) == false) {
            values.put(DayGo.Users.COLUMN_NAME_MODIFY_DATE, now);
        }

        // If the values map doesn't contain a title, sets the value to the default title.
        if (values.containsKey(DayGo.Users.COLUMN_NAME_NAME) == false) {
            Resources r = Resources.getSystem();
            values.put(DayGo.Users.COLUMN_NAME_NAME, r.getString(android.R.string.untitled));
        }

        if (values.containsKey(DayGo.Users.COLUMN_NAME_GENDER) == false) {
            values.put(DayGo.Users.COLUMN_NAME_GENDER, "");
        }

        if (values.containsKey(DayGo.Users.COLUMN_NAME_EMAIL) == false) {
            values.put(DayGo.Users.COLUMN_NAME_EMAIL, "");
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        // Performs the insert and returns the ID of the new note.
        long rowId = db.insert(
                DayGo.Users.TABLE_NAME,          // The table to insert into.
                DayGo.Users.COLUMN_NAME_GENDER,  // A hack, SQLite sets this column value to null
                                                 // if values is empty.
                values                           // A map of column names, and the values to insert
                                                 // into the columns.
        );

        if (rowId > 0) {
            // Creates a URI with the note ID pattern and the new row ID appended to it.
            Uri noteUri = ContentUris.withAppendedId(DayGo.Users.CONTENT_ID_URI_BASE, rowId);

            // Notifies observers registered against this provider that the data changed.
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String finalWhere;

        int count;

        switch (mUriMatcher.match(uri)) {

            case USERS:
                //case LIVE_FOLDER_NOTES:
                count = db.delete(
                        DayGo.Users.TABLE_NAME,  // The database table name
                        selection,                     // The incoming where clause column names
                        selectionArgs                  // The incoming where clause values
                );
                break;

                // If the incoming URI matches a single note ID, does the delete based on the
                // incoming data, but modifies the where clause to restrict it to the
                // particular note ID.
            case USER_ID:
                /*
                 * Starts a final WHERE clause by restricting it to the
                 * desired note ID.
                 */
                finalWhere =
                        DayGo.Users._ID +                              // The ID column name
                                " = " +                                          // test for equality
                                uri.getPathSegments().                           // the incoming note ID
                                        get(DayGo.Users.USER_ID_PATH_POSITION)
                ;

                if (selection != null) {
                    finalWhere = finalWhere + " AND " + selection;
                }

                // Performs the delete.
                count = db.delete(
                        DayGo.Users.TABLE_NAME,  // The database table name.
                        finalWhere,                // The final WHERE clause
                        selectionArgs                  // The incoming where clause values.
                );
                break;

            // If the URI pattern doesn't match any permitted patterns, throws an exception.
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        String finalWhere;

        switch (mUriMatcher.match(uri)) {

            case USERS:
                //case LIVE_FOLDER_NOTES:
                count = db.update(
                        DayGo.Users.TABLE_NAME, // The database table name.
                        values,                   // A map of column names and new values to use.
                        selection,                    // The where clause column names.
                        selectionArgs                 // The where clause column values to select on.
                );
                break;

            // If the incoming URI matches a single note ID, does the delete based on the
            // incoming data, but modifies the where clause to restrict it to the
            // particular note ID.
            case USER_ID:
                /*
                 * Starts a final WHERE clause by restricting it to the
                 * desired note ID.
                 */
                finalWhere =
                        DayGo.Users._ID +                              // The ID column name
                                " = " +                                          // test for equality
                                uri.getPathSegments().                           // the incoming note ID
                                        get(DayGo.Users.USER_ID_PATH_POSITION)
                ;

                if (selection != null) {
                    finalWhere = finalWhere + " AND " + selection;
                }

                // Performs the delete.
                count = db.update(
                        DayGo.Users.TABLE_NAME,
                        values,
                        finalWhere,
                        selectionArgs
                );
                break;

            // If the URI pattern doesn't match any permitted patterns, throws an exception.
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return count;
    }
}
