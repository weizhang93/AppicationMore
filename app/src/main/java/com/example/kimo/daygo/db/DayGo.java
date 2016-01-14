package com.example.kimo.daygo.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/1/8 0008.
 * Contract
 */
public final class DayGo  {
    public static final String AUTHORITY = "com.example.kimo.daygo";

    //this class can not be instantiated.
    private DayGo(){

    }

    public static final class Users implements BaseColumns{

        //这样只能同过ContentProvider调用，蛋疼
        private Users(){

        }
        public static final String TABLE_NAME = "users";

        /**
         * URI definitions
         */
        private static final String SCHEME = "content://";

        private static final String PATH_USERS = "/users";

        private static final String PATH_USER_ID = "/users/";

        public static final int USER_ID_PATH_POSITION = 1 ;

        private static final String PATH_LIVE_FOLDER = "/live_folders/notes";//好像已经淘汰了

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse(SCHEME+AUTHORITY+PATH_USERS);

        /**
         * The content URI base for a single note. Callers must
         * append a numeric note id to this Uri to retrieve a note
         */
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME+AUTHORITY+PATH_USER_ID);

        /**
         * The content Uri pattern for a notes listing for live folders
         */
        public static final Uri LIVE_FOLDER_URI
                = Uri.parse(SCHEME + AUTHORITY + PATH_LIVE_FOLDER);

        /**
         * MIME type definitions
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.dontknow.daygo";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.dontknow.daygo";

        public static final String DEFAULT_SORT_ORDER = "modified DESC";


        /**
         * Column definitions
         */
        public static final String COLUMN_NAME_NAME = "name";

        public static final String COLUMN_NAME_GENDER = "gender";

        public static final String COLUMN_NAME_EMAIL = "email";

        public static final String COLUMN_NAME_CREATE_DATE = "created";

        public static final String COLUMN_NAME_MODIFY_DATE = "modified";

    }
}
