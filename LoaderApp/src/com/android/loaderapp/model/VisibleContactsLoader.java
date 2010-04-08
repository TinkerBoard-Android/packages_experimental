/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.loaderapp.model;

import android.app.patterns.CursorLoader;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.Contacts;

/**
 * Loader for all visible contacts.
 */
public class VisibleContactsLoader extends CursorLoader {
    public static final class ListQuery {
        private ListQuery() {}

        public static final String[] COLUMNS = new String[] {
            Contacts._ID,                       // 0
            Contacts.DISPLAY_NAME_PRIMARY,      // 1
            Contacts.DISPLAY_NAME_ALTERNATIVE,  // 2
            Contacts.SORT_KEY_PRIMARY,          // 3
            Contacts.STARRED,                   // 4
            Contacts.TIMES_CONTACTED,           // 5
            Contacts.CONTACT_PRESENCE,          // 6
            Contacts.PHOTO_ID,                  // 7
            Contacts.LOOKUP_KEY,                // 8
            Contacts.PHONETIC_NAME,             // 9
            Contacts.HAS_PHONE_NUMBER,          // 10
        };

        public static final int COLUMN_ID = 0;
        public static final int COLUMN_NAME = 1;
        public static final int COLUMN_LOOKUP_KEY = 8;
    }

    public VisibleContactsLoader(Context context) {
        super(context);
    }

    @Override
    protected Cursor doQueryInBackground() {
        Cursor cursor = getContext().getContentResolver().query(Contacts.CONTENT_URI,
                ListQuery.COLUMNS, Contacts.IN_VISIBLE_GROUP + "=1", null,
                Contacts.SORT_KEY_PRIMARY);
        return cursor;
    }
}
