package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by Fajar Fachrizal on 24.07.2017.
 */

public class BierContract {
    public BierContract() {
    }

    public static final class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "supply";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                BierContract.StockEntry.TABLE_NAME + "(" +
                BierContract.StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BierContract.StockEntry.COLUMN_NAME + " TEXT NOT NULL," +
                BierContract.StockEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                BierContract.StockEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                BierContract.StockEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                BierContract.StockEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                BierContract.StockEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                StockEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
