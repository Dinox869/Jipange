package com.example.dennis.jipange;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dennis on 2/3/2018.
 */

public class databasehelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "JIPANGE_DB";
    //This contains Transport table
    private static final String TAG = "Databasehelper";
    private static final String TABLE_NAME = "Transport";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name_of_transport";
    private static final String COLUMN_CATEGORY = "Category";
    private static final String COLUMN_TRAVELLING_FROM = "Travelling_from";
    private static final String COLUMN_TRAVELLING_TO ="Travelling_to";
    private static final String COLUMN_PRICE = "Price";
    private static final int DATABASE_VERSION = 1;

    //this contains Food table
    private static final String TABLE_NAMES = "Food";
    private static final String COLUMN_Id = "ID";
    private static final String COLUMN_Name = "Name_of_Food";
    private static final String COLUMN_Category = "Category";
    private static final String COLUMN_Mealtime = "Mealtime";
    private static final String COLUMN_Price = "Price";

    // this contains Drink table
    private static final String TABLE_name = "Drink";
    private static final String COLUMN_id= "ID";
    private static final String COLUMN_name = "Name_of_Drink";
    private static final String COLUMN_category = "Category";
    private static final String COLUMN_price = "Price";

    //this contains Shopping table
    private static final String Table_name = "Shopping";
    private static final String Column_id= "ID";
    private static final String Column_name = "Name_of_Shopping_item";
    private static final String Column_price = "Price";

    //this contains Electronic table
    private static final String table_name = "Electronic";
    private static final String column_id= "ID";
    private static final String column_name = "Name_of_Electronic_item";
    private static final String column_price = "Price";

    //this contains Add table
    private static final String table_Name = "Other";
    private static final String column_Id= "ID";
    private static final String column_Name = "Name_of_Add_item";
    private static final String column_Price = "Price";

    //this contains Cloth table
    private static final String table_NAME = "Cloth";
    private static final String column_ID= "ID";
    private static final String column_NAME = "Name_of_Clothes";
    private static final String column_PRICE = "Price";

    //this contains Bill table
    private static final String Tables_NAME = "malipo";
    private static final String Column_ID = "ID";
    private static final String Column_NAME = "Name_of_bills";
    private static final String Column_CATEGORY = "Category_of_bills";
    private static final String Column_PRICE = "Price";

    //This contains HISTORY table
    private static final String TABLES_NAMES = "History";
    private static final String COLUMNS_IdS = "ID";
    private static final String COLUMNS_NameS = "Statements";
    private static final String COLUMNS_FIELDS = "Fields";
    private static final String COLUMNS_PriceS = "Price";
    private static final String COLUMNS_DATE = "Date";
    private static final String mysql_status = "status";

    //this table is for saving_form table
    private static final String Tables = "Sform";
    private static final String COLUMNS_SavingId = "ID";
    private static final String COLUMNS_SavingName = "Name_of_items";
    private static final String COLUMNS_SavingPrice = "Price";
    private static final String COLUMNS_SavingRate = "Rate_of_deposit";
    private static final String COLUMNS_SavigPeriod = "Period_of_saving ";

    //This table is for storing the photo for profile.
    private static final String PICS = "PICS";
    private static final String Id_pic = "ID";
    private static final String Image = "image";
    private static final String Name = "name";

    //This table is  for Pictures in accounts
    private static final String PIC = "ACCOUNTS";
    private static final String Id_pics = "ID";
    private static final String Images = "image";
    private static final String Names = "name";

    //this tables for mysql database connection
    private static final String mysql = "names";
    private static final String mysql_id = "ID";
    private static final String mysql_name = "name";
    private static final String mysql_email = "email";

    //this table is for permission for saving data online
    private static final String online = "online";
    private static final String id = "ID";
    private static final String number = "number";







    //String for the data for graphs.
    String graph ;

    SQLiteDatabase db;
    private String date;
    private String field;

    public databasehelper (Context context)
    {
        super(context, DATABASE_NAME , null , 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Transport table
        String createtable = "CREATE TABLE "+TABLE_NAME+" ( " +COLUMN_ID+ " INTEGER NOT NULL PRIMARY KEY ,"+
                COLUMN_NAME+" TEXT , "+COLUMN_CATEGORY+" TEXT , "+COLUMN_TRAVELLING_FROM+" TEXT , "+COLUMN_TRAVELLING_TO+" TEXT , "+COLUMN_PRICE+" INTEGER NOT NULL ) ";
        //Food table
        String createtables = "CREATE TABLE "+TABLE_NAMES +" ( " +COLUMN_Id+ " INTEGER NOT NULL PRIMARY KEY ,"+
                COLUMN_Name+" TEXT , "+COLUMN_Category+" TEXT , "+COLUMN_Mealtime+" TEXT , "+COLUMN_Price+" INTEGER NOT NULL ) ";
        //Drink table
        String createtabledrink = "CREATE TABLE "+TABLE_name +" ( " +COLUMN_id+ " INTEGER NOT NULL PRIMARY KEY ,"+
                COLUMN_name+" TEXT , "+COLUMN_category+" TEXT , "+COLUMN_price+" INTEGER NOT NULL ) ";
        //Shopping table
        String createtableshopping = "CREATE TABLE "+Table_name +" ( " +Column_id+ " INTEGER NOT NULL PRIMARY KEY ,"+
                Column_name+" TEXT , "+Column_price+" INTEGER NOT NULL ) ";
        //Electronic table
        String createtableselec = "CREATE TABLE "+table_name +" ( " +column_id+ " INTEGER NOT NULL PRIMARY KEY ,"+
                column_name+" TEXT , "+column_price+" INTEGER NOT NULL ) ";
        //Add table.
        String createtableadd = "CREATE TABLE "+table_Name +" ( " +column_Id+ " INTEGER NOT NULL PRIMARY KEY , "+
                column_Name+" TEXT , "+column_Price+" INTEGER NOT NULL ) ";
        //Clothes table.
        String createtableclothes = "CREATE TABLE "+table_NAME +" ( " +column_ID+ " INTEGER NOT NULL PRIMARY KEY ,"+
                column_NAME+" TEXT , "+column_PRICE+" INTEGER NOT NULL ) ";

        //saving_form table.
        String createtablesavingform = "CREATE TABLE "+table_NAME +" ( " +COLUMNS_SavingId+ " INTEGER NOT NULL PRIMARY KEY , "+
                COLUMNS_SavingName+" TEXT , "+COLUMNS_SavingPrice+" INTEGER NOT NULL , "+COLUMNS_SavingRate+" INTEGER  , "+COLUMNS_SavigPeriod+" INTEGER ) ";
        //saf.
        String createtablesaf = "CREATE TABLE "+Tables +" ( " +COLUMNS_SavingId+ " INTEGER NOT NULL PRIMARY KEY ,"+
                COLUMNS_SavingName+" TEXT , "+COLUMNS_SavingPrice+" INTEGER NOT NULL , "+COLUMNS_SavingRate+" INTEGER  , "+COLUMNS_SavigPeriod+" INTEGER  ) ";

        //Bill table.
        String createtablebill = "CREATE TABLE "+Tables_NAME +" ( " +Column_ID+ " INTEGER NOT NULL PRIMARY KEY , "+
        Column_NAME+" TEXT , "+Column_CATEGORY+" TEXT , "+Column_PRICE+" INTEGER NOT NULL ) ";

        //History table.
        String createtablehistory = "CREATE TABLE "+TABLES_NAMES +" ( " +COLUMNS_IdS+ " INTEGER NOT NULL PRIMARY KEY , "+
                COLUMNS_NameS+" TEXT , "+COLUMNS_FIELDS+" TEXT , "+COLUMNS_DATE+" DATETIME DEFAULT CURRENT_DATE , "+COLUMNS_PriceS+" INTEGER NOT NULL ,  "+mysql_status+" TINYINT ) ";
        //Picture table for profile.
        String create_pics = "CREATE TABLE "+PICS+" ( "+Id_pic+" INTEGER  PRIMARY KEY AUTOINCREMENT ,  "+Image+" BLOB ) ";

        //picture table for accounts.
        String accounts = "CREATE TABLE "+PIC+" ( "+Id_pics+" INTEGER  PRIMARY KEY AUTOINCREMENT ,  "+Images+" BLOB , "+Name+" TEXT ) ";

        //for mysql.
        String mysqli = "CREATE TABLE " +mysql+" ( "+mysql_id+" INTEGER PRIMARY KEY AUTOINCREMENT , "+mysql_name+"  VARCHAR , "+mysql_email+"  VARCHAR  ) ";

        //for online.
        String on = "CREATE TABLE " +online+" ( "+id+" INTEGER PRIMARY KEY AUTOINCREMENT , "+number+"  VARCHAR  ) ";
        db.execSQL(on);
        db.execSQL(mysqli);
        db.execSQL(createtablebill);
        db.execSQL(create_pics);
        db.execSQL(accounts);
        db.execSQL(createtableadd);
        db.execSQL(createtablesaf);
        db.execSQL(createtablehistory);
        db.execSQL(createtableclothes);
        db.execSQL(createtableselec);
        db.execSQL(createtableshopping);
        db.execSQL(createtable);
        db.execSQL(createtables);
        db.execSQL(createtabledrink);
        Log.d(TAG,"DATABASE TRANSPORT,FOOD,DRINK,SHOPPING,ELECTRONIC,BILL & ADD CREATED");
    }

    //In order to delete everything in the database
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_NAME);
        db.execSQL("delete from "+ TABLES_NAMES);
        db.execSQL("delete from "+ TABLE_name);
        db.execSQL("delete from "+ Table_name);
        db.execSQL("delete from "+ table_name);
        db.execSQL("delete from "+ table_Name);
        db.execSQL("delete from "+ table_NAME);
        db.execSQL("delete from "+ Tables);
        db.execSQL("delete from "+ Tables_NAME);
        db.execSQL("delete from "+ TABLES_NAMES);
        db.execSQL("delete from "+ PICS);
        db.execSQL("delete from "+ PIC);
        db.execSQL("delete from "+ online);
        db.close();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //MYSQLI
        String mysqlie = "DROP TABLE IF EXIST "+mysql+" ";
        //Transport
        String query = "DROP TABLE IF EXIST "+TABLE_NAME+" ";
        //Food
        String querys = "DROP TABLE IF EXIST "+TABLE_NAMES+" ";
        //Drink
        String queries = "DROP TABLE IF EXIST "+TABLE_name+" ";
        //Shopping
        String Queries = "DROP TABLE IF EXIST "+Table_name+" ";
        //Electronic
        String QUERIES = "DROP TABLE IF EXIST "+table_name+" ";
        //Add
        String QUERY = "DROP TABLE IF EXIST "+table_name+" ";
        //Clothes
        String QUERYs = "DROP TABLE IF EXIST "+Tables+" ";
        //pics for profile
        String Qpics = "DROP TABLE IF EXIST PICS ";
        //Pics for accounts
        String Accounts = "DROP TABLE IF EXIST PIC ";
        //Bill
        String QUERYies = "DROP TABLE IF EXIST "+Tables_NAME+" ";
        //History
        String QUERYSES = "DROP TABLE IF EXIST "+TABLES_NAMES+" ";
        //Saving_form
        String SAVING = "DROP TABLE IF EXIST "+table_NAME+" ";
        //online
        String ON = "DROP TABLE IF EXIST "+online+" ";

        db.execSQL(QUERIES);
        db.execSQL(QUERY);
        db.execSQL(ON);
        db.execSQL(mysqlie);
        db.execSQL(Accounts);
        db.execSQL(Qpics);
        db.execSQL(SAVING);
        db.execSQL(QUERYSES);
        db.execSQL(QUERYies);
        db.execSQL(QUERYs);
        db.execSQL(Queries);
        db.execSQL(queries);
        db.execSQL(query);
        db.execSQL(querys);
        this.onCreate(db);

        }
        //this is to insert data into online table
        public void insertonline(set_get c)
        {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            String query = "Select * from "+online;
            Cursor cursor =  db.rawQuery(query,null);
            int count = cursor.getCount();
            values.put(id, count);
            values.put(number,c.getname());
            db.insert(online,null,values);
            Log.d(TAG,"DATA added to online database.");
        }
        //this is to check the data length
        public Cursor getOnline() {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM " + online ;
            Cursor c = db.rawQuery(sql, null);
            return c;
        }
        //this one takes two parameters,one is the id of the name and the second is the status that will be changed
        public boolean updateNameStatus(int id, int status) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(mysql_status, status);
            db.update(TABLES_NAMES, contentValues, mysql_id + "=" + id, null);
            db.close();
            return true;
        }
        //gets everything from database
        public Cursor getNames() {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM " + TABLES_NAMES + " ORDER BY " + COLUMNS_IdS + " ASC;";
            Cursor c = db.rawQuery(sql, null);
            return c;
        }
        //this gets all the unsynced in table
        public Cursor getUnsyncedNames() {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM " + TABLES_NAMES + " WHERE " + mysql_status + " = 0;";
            Cursor c = db.rawQuery(sql, null);
            return c;
        }
    //this is for profile
        public void insertprofile(set_get c)
        {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            String query = "Select * from "+mysql;
            Cursor cursor =  db.rawQuery(query,null);
            int count = cursor.getCount();
            values.put(mysql_id, count);
            values.put(mysql_name,c.getname());
            values.put(mysql_email,c.getfield());
            db.insert(mysql,null,values);
            Log.d(TAG,"DATA added to profile database");
        }
    //Pictures for accounts
    public void insertdatas(byte[] image, String names)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Images ,image);
        values.put(Name, names);
        db.insert(PIC,null,values);
        Log.d(TAG,"DATA added to picture database");
    }
    //Pictures method for inserting pisc.
    public void insertdata(byte[] image)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Image ,image);
        db.insert(PICS,null,values);
        Log.d(TAG,"DATA added to picture database");
    }
    //method to send image to listview in accounts
    public Cursor  retreiveImagesFromDB() {
        db = this.getWritableDatabase();
        String query = "SELECT * FROM ACCOUNTS ";
        Cursor cur = db.rawQuery(query,null);
        return cur;
    }
    //method to send image in imageview
    public byte[] retreiveImageFromDB() {
        db = this.getWritableDatabase();
        String query = "SELECT * FROM PICS ";
        Cursor cur = db.rawQuery(query,null);
        if (cur.moveToLast()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(Image));
            return blob;
        }
        return null;
    }

    //Transport method for inserting data.
    public void insertsaving(set_get c)
    {
        Log.d(TAG, "set_get : adding  Transport "+c.getfrom()+" "+c.getname()+ " "+c.getprice()+" "+c.getto()+ " to "+TABLE_NAME);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME,c.getname());
        values.put(COLUMN_CATEGORY,c.getcat());
        values.put(COLUMN_PRICE,c.getprice());
        values.put(COLUMN_TRAVELLING_FROM,c.getfrom());
        values.put(COLUMN_TRAVELLING_TO,c.getto());
        db.insert(TABLE_NAME,null,values);
        Log.d(TAG,"DATA added to Transport database");
    }
    //Food method for inserting data
    public void insertsavings (set_get c)
    {
        Log.d(TAG, "set_get : adding "+c.getmeal()+" "+c.getname()+ " "+c.getprice()+" "+c.getfood()+ " to "+TABLE_NAMES);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+TABLE_NAMES;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_Name,c.getname());
        values.put(COLUMN_Category,c.getcat());
        values.put(COLUMN_Price,c.getprice());
        values.put(COLUMN_Mealtime,c.getmeal());
        db.insert(TABLE_NAMES,null,values);
        Log.d(TAG,"DATA added to Food database");
    }
    //Drink method for inserting data
    public void insertsavingsdrink (set_get c)
    {
        Log.d(TAG, "set_get : adding  "+c.getname()+ " "+c.getprice()+" "+c.getdrink()+ " to "+TABLE_name);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+TABLE_name;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_id, count);
        values.put(COLUMN_name,c.getname());
        values.put(COLUMN_category,c.getdrink());
        values.put(COLUMN_price,c.getprice());
        db.insert(TABLE_name,null,values);
        Log.d(TAG,"DATA added to Drink database");
    }
    // Shopping method for inserting data
    public void insertsavingsshopping(set_get c) {
        Log.d(TAG, "set_get : adding  "+c.getname()+ " "+c.getprice()+"  to "+Table_name);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+TABLE_name;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(Column_id, count);
        values.put(Column_name,c.getname());
        values.put(Column_price,c.getprice());
        db.insert(Table_name,null,values);
        Log.d(TAG,"DATA added to Shopping database");
    }
    //Electronics method for inserting data
    public void insertsavingelec(set_get c)
    {
        Log.d(TAG, "set_get : adding  "+c.getname()+ " "+c.getprice()+"  to "+table_name);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+table_name;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(column_id, count);
        values.put(column_name,c.getname());
        values.put(column_price,c.getprice());
        db.insert(table_name,null,values);
        Log.d(TAG,"DATA added to electronics database");
    }
    //Add method for inserting data
    public void insertsavingadd(set_get c)
    {
        Log.d(TAG, "set_get : adding  "+c.getname()+ " "+c.getprice()+"  to "+table_Name);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+table_name;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(column_Id, count);
        values.put(column_Name,c.getname());
        values.put(column_Price,c.getprice());
        db.insert(table_Name,null,values);
        Log.d(TAG,"DATA added to Add database");
    }
    //Cloth method for inserting data
    public void insertsavingscloth(set_get c)
    {
        Log.d(TAG, "set_get : adding  "+c.getname()+ " "+c.getprice()+"  to "+table_NAME);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "Select * from "+table_NAME;
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(column_ID, count);
        values.put(column_NAME,c.getname());
        values.put(column_PRICE,c.getprice());
        db.insert(table_NAME,null,values);
        Log.d(TAG,"DATA added to Clothes database");
    }
    //bill method to insert data
    public void insertsavingbill(set_get c)
    {
        Log.d(TAG, " set_get : adding  "+c.getname()+ " "+ c.getbill()+" " +c.getprice()+"  to "+Tables_NAME);
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = " Select * from "+Tables_NAME +" ";
        Cursor cursor =  db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(Column_ID, count);
        values.put(Column_NAME,c.getname());
        values.put(Column_CATEGORY,c.getbill());
        values.put(Column_PRICE,c.getprice());
        db.insert(Tables_NAME,null,values);
        Log.d(TAG,"DATA added to Bill database");
    }
    //History method to insert data
    public void insertsaving_HISTORY(set_get c,int status)
    {
        Log.d(TAG ,"set_get : adding "+c.gethistory()+" to "+TABLES_NAMES);
        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+TABLES_NAMES+" ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMNS_IdS, count);
        values.put(COLUMNS_NameS, c.gethistory());
        values.put(COLUMNS_FIELDS, c.getfield());
        values.put(COLUMNS_PriceS, c.getprice());
        values.put(mysql_status,status);
        db.insert(TABLES_NAMES,null,values);

    }
    //for transport
    //History method to insert data
    public void insertsaving_HISTORYs( String Price , String FIELD ,String History ,int status)
    {
        Log.d(TAG ,"set_get : adding "+History+" to "+TABLES_NAMES);
        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+TABLES_NAMES+" ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMNS_IdS, count);
        values.put(COLUMNS_NameS, History);
        values.put(COLUMNS_FIELDS,FIELD);
        values.put(COLUMNS_PriceS,Price);
        values.put(mysql_status,status);
        db.insert(TABLES_NAMES,null,values);
    }
    //METHOD TO ADD FROM ONLINE DB
    public void insertsaving_HISTORYZ( String Statements , String Price ,String FIELD ,int status)
    {
        Log.d(TAG ,"set_get : adding "+Statements+" to "+TABLES_NAMES);
        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+TABLES_NAMES+" ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMNS_IdS, count);
        values.put(COLUMNS_NameS, Statements);
        values.put(COLUMNS_PriceS,Price);
        values.put(COLUMNS_FIELDS,FIELD);
        values.put(mysql_status,status);
        db.insert(TABLES_NAMES,null,values);
    }
    // History Gets data from table
    public Cursor getdata()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM History";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //Gets info for profile
    public Cursor getprofile()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+ mysql;
        Cursor data = db.rawQuery(query,null);
        return  data;

    }
    //to get email
    public Cursor getprofiles()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT "+ mysql_email +" FROM "+ mysql;
        Cursor data = db.rawQuery(query,null);
        return  data;

    }
    // to get the total price for the graph.
   //Transport
    public Cursor getfield()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Transport' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //Food
    public Cursor getfieldes()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'FOOD' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //drink
    public Cursor getfieldss()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Drinks' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //Shopping
    public Cursor getfieldz()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Shopping' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //Bills
    public Cursor getBILLS()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Bill' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //Electronics
    public Cursor getelecs()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Electronics' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //clothes
    public Cursor getCLOTHES()
    {

        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Clothes' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //others
    public Cursor getOTHERS()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE "+COLUMNS_FIELDS+" = 'Others' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //to get the price digits for transports graph.
    public Cursor getprice()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT "+COLUMN_CATEGORY+" , SUM( "+COLUMN_PRICE +" ) FROM "+TABLE_NAME+" GROUP  BY "+COLUMN_CATEGORY ;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //method to get the appropriate for food & snacks graph.
    public Cursor getFoodprice()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT "+COLUMN_Mealtime+" , SUM( "+COLUMN_Price +" ) FROM "+TABLE_NAMES+" GROUP  BY "+COLUMN_Mealtime ;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //method to get the appropriate for Drinks graph.
    public Cursor getDrinksprice()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT "+COLUMN_category+" , SUM( "+COLUMN_price +" ) FROM "+TABLE_name+" GROUP  BY "+COLUMN_category ;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //method to get the appropriate for bills graph.
    public Cursor getBillprice()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT "+Column_CATEGORY+" , SUM( "+Column_PRICE +" ) FROM "+Tables_NAME+" GROUP  BY "+Column_CATEGORY ;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //method to get the appropriate for all fields graph.
    public Cursor getTotalPrice()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT "+COLUMNS_FIELDS+" , SUM( "+COLUMNS_PriceS +" ) FROM "+TABLES_NAMES+" GROUP  BY "+COLUMNS_FIELDS ;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //gets total in profile
    public Cursor getTotal() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM( " + COLUMNS_PriceS + " ) FROM  History";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    //method to get date in history
    public void setdate(String date)
    {
       this.date = date;
    }
    //History database method to get data from specific dates
    public Cursor getdate()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE Date = '"+date+"' ";
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
    //To get the Total in date
    public Cursor getAmount()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT SUM ( Price ) FROM "+TABLES_NAMES+" WHERE Date = '"+date+"' ";
        Cursor data = db.rawQuery(query,null);
        return  data;

    }
    //method for history's date & field
    public void setsfield( String field)
    {
        this.field = field;

    }
    //method for date && field
    public Cursor getdatefield()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLES_NAMES+" WHERE Date = '"+date+"' AND Fields = '"+field+"' ";
        Cursor data = db.rawQuery(query,null);
        return  data;

    }
    //method to getdate from saving_form
    public void insertsaving_saving_form(set_get c)
    {
        Log.d(TAG ,"set_get : adding "+c.getname()+" to "+Tables);
        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+Tables+" ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMNS_SavingId, count);
        values.put(COLUMNS_SavingName, c.getname());
        values.put(COLUMNS_SavingPrice, c.getprice());
        values.put(COLUMNS_SavigPeriod,0);
        values.put(COLUMNS_SavingRate,0 );
        db.insert(Tables,null,values);
    }
   //To get the count from the savings_table for the if_statement
   public Cursor getsavings()
   {
       SQLiteDatabase db =this.getWritableDatabase();
       String query = "SELECT * FROM "+Tables ;
       Cursor data = db.rawQuery(query,null);
       return  data;
   }


}
     //2018-02-07
