package com.hht.myfi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;

import com.hht.myfi.DTO.DTO_Expense;
import com.hht.myfi.DTO.DTO_Income;
import com.hht.myfi.DTO.DTO_Debt;
import com.hht.myfi.DTO.DTO_Loan;
import com.hht.myfi.DTO.DTO_IncomeCategory;
import com.hht.myfi.DTO.DTO_ExpenseCategory;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance = null;
    private Context ctx;

    public static DatabaseHelper getInstance(Context ctx) {
        if (instance == null)
            instance = new DatabaseHelper(ctx.getApplicationContext());
        return instance;
    }

    //region Constant
    public static final String TAG = "SQLite";

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "DatabaseFinancialManager";

    public static final String TABLE_INCOME = "TableIncome";
    public static final String COLUMN_INCOME_INCOMEID = "incomeID";
    public static final String COLUMN_INCOME_INCOMENAME = "incomeName";
    public static final String COLUMN_INCOME_INCOMEAMOUNT = "incomeAmount";
    //public static final String COLUMN_INCOME_INCOMECATEGORYID ="incomeCategoryID";
    public static final String COLUMN_INCOME_INCOMEDATE = "incomeDate";
    public static final String COLUMN_INCOME_INCOMENOTE = "incomeNote";

    public static final String TABLE_INCOMECATEGORY = "TableIncomeCategory";
    public static final String COLUMN_INCOMECATEGORY_INCOMECATEGORYID = "incomeCategoryID";
    public static final String COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME = "incomeCategoryName";

    public static final String TABLE_EXPENSE = "TableExpense";
    public static final String COLUMN_EXPENSE_EXPENSEID = "expenseID";
    public static final String COLUMN_EXPENSE_EXPENSENAME = "expenseName";
    public static final String COLUMN_EXPENSE_EXPENSEAMOUNT = "expenseAmount";
    //public static final String COLUMN_EXPENSE_EXPENSECATEGORYID ="expenseCategoryID";
    public static final String COLUMN_EXPENSE_EXPENSEDATE = "expenseDate";
    public static final String COLUMN_EXPENSE_EXPENSENOTE = "expenseNote";

    public static final String TABLE_EXPENSECATEGORY = "TableExpenseCategory";
    public static final String COLUMN_EXPENSECATEGORY_EXPENSECATEGORYID = "expenseCategoryID";
    public static final String COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME = "expenseCategoryName";

    public static final String TABLE_LOAN = "TableLoan";
    public static final String COLUMN_LOAN_LOANID = "loanID";
    public static final String COLUMN_LOAN_LOANNAME = "loanName";
    public static final String COLUMN_LOAN_LOANAMOUNT = "loanAmount";
    public static final String COLUMN_LOAN_LOANINTERESTRATE = "loanInterestRate";
    public static final String COLUMN_LOAN_LOANDATE = "loanDate";
    public static final String COLUMN_LOAN_LOANEXPIRATIONDATE = "loanExpirationDate";
    public static final String COLUMN_LOAN_LOANNOTE = "loanNote";

    public static final String TABLE_DEBT = "TableDebt";
    public static final String COLUMN_DEBT_DEBTID = "debtID";
    public static final String COLUMN_DEBT_DEBTNAME = "debtName";
    public static final String COLUMN_DEBT_DEBTAMOUNT = "debtAmount";
    public static final String COLUMN_DEBT_DEBTINTERESTRATE = "debtInterestRate";
    public static final String COLUMN_DEBT_DEBTDATE = "debtDate";
    public static final String COLUMN_DEBT_DEBTEXPIRATIONDATE = "debtExpirationDate";
    public static final String COLUMN_DEBT_DEBTNOTE = "debtNote";
    //endregion

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DatabaseHelper.onCreate ... ");

        String script = "CREATE TABLE " + TABLE_INCOMECATEGORY + "("
                + COLUMN_INCOMECATEGORY_INCOMECATEGORYID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME + " NVARCHAR(50)" + ")";
        db.execSQL(script);

        String script1 = "CREATE TABLE " + TABLE_EXPENSECATEGORY + "("
                + COLUMN_EXPENSECATEGORY_EXPENSECATEGORYID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME + " NVARCHAR(50)" + ")";
        db.execSQL(script1);

        String script2 = "CREATE TABLE " + TABLE_INCOME + "("
                + COLUMN_INCOME_INCOMEID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_INCOME_INCOMENAME + " NVARCHAR(50),"
                + COLUMN_INCOME_INCOMEAMOUNT + " REAL,"
                //+ COLUMN_INCOME_INCOMECATEGORYID + " INTEGER,"
                + COLUMN_INCOME_INCOMEDATE + " DATE,"
                + COLUMN_INCOME_INCOMENOTE + " TEXT,"
                + " FOREIGN KEY (" + COLUMN_INCOME_INCOMENAME
                + ") REFERENCES " + TABLE_INCOMECATEGORY
                + "(" + COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME + ")" + ")";
        db.execSQL(script2);


        String script3 = "CREATE TABLE " + TABLE_EXPENSE + "("
                + COLUMN_EXPENSE_EXPENSEID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_EXPENSE_EXPENSENAME + " NVARCHAR(50),"
                + COLUMN_EXPENSE_EXPENSEAMOUNT + " REAL,"
                //+ COLUMN_EXPENSE_EXPENSECATEGORYID + " INTEGER,"
                + COLUMN_EXPENSE_EXPENSEDATE + " DATE,"
                + COLUMN_EXPENSE_EXPENSENOTE + " TEXT,"
                + " FOREIGN KEY (" + COLUMN_EXPENSE_EXPENSENAME
                + ") REFERENCES " + TABLE_EXPENSECATEGORY
                + "(" + COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME + ")" + ")";
        db.execSQL(script3);

        String script4 = "CREATE TABLE " + TABLE_LOAN + "("
                + COLUMN_LOAN_LOANID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_LOAN_LOANNAME + " NVARCHAR(50),"
                + COLUMN_LOAN_LOANAMOUNT + " REAL,"
                + COLUMN_LOAN_LOANINTERESTRATE + " FLOAT,"
                + COLUMN_LOAN_LOANDATE + " DATE,"
                + COLUMN_LOAN_LOANEXPIRATIONDATE + " DATE,"
                + COLUMN_LOAN_LOANNOTE + " TEXT " + ")";
        db.execSQL(script4);

        String script5 = "CREATE TABLE " + TABLE_DEBT + "("
                + COLUMN_DEBT_DEBTID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_DEBT_DEBTNAME + " NVARCHAR(50),"
                + COLUMN_DEBT_DEBTAMOUNT + " REAL,"
                + COLUMN_DEBT_DEBTINTERESTRATE + " FLOAT,"
                + COLUMN_DEBT_DEBTDATE + " DATE,"
                + COLUMN_DEBT_DEBTEXPIRATIONDATE + " DATE,"
                + COLUMN_DEBT_DEBTNOTE + " TEXT " + ")";
        db.execSQL(script5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "DatabaseHelper.onUpgrade ... ");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOMECATEGORY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSECATEGORY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAN);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEBT);

        onCreate(db);
    }

    //region IncomeCategory
    public void createDefaultIncomeCategory() {
        int count = this.getIncomeCategoryCount();
        if (count == 0) {
            DTO_IncomeCategory incomeCategory1 = new DTO_IncomeCategory("Lương");
            DTO_IncomeCategory incomeCategory2 = new DTO_IncomeCategory("Thưởng");
            DTO_IncomeCategory incomeCategory3 = new DTO_IncomeCategory("Tiền lãi");
            DTO_IncomeCategory incomeCategory4 = new DTO_IncomeCategory("Được tặng");
            DTO_IncomeCategory incomeCategory5 = new DTO_IncomeCategory("Bán đồ");
            this.addIncomeCategory(incomeCategory1);
            this.addIncomeCategory(incomeCategory2);
            this.addIncomeCategory(incomeCategory3);
            this.addIncomeCategory(incomeCategory4);
            this.addIncomeCategory(incomeCategory5);
        }
    }


    public void addIncomeCategory(DTO_IncomeCategory incomeCategory) {
        Log.i(TAG, "DatabaseHelper.addIncomeCategory ... " + incomeCategory.getIncomeCategoryName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME, incomeCategory.getIncomeCategoryName());

        db.insert(TABLE_INCOMECATEGORY, null, values);

        db.close();
    }

    public void updateIncomeCategory(DTO_IncomeCategory theLoaiThu, String incomeCategoryID) {
        Log.i(TAG, "DatabaseHelper.updateIncomeCategory ... " + theLoaiThu.getIncomeCategoryName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME, theLoaiThu.getIncomeCategoryName());

        db.update(
                TABLE_INCOMECATEGORY,
                values,
                COLUMN_INCOMECATEGORY_INCOMECATEGORYID + "=" + incomeCategoryID,
                null
        );

        db.close();
    }

    public void deleteIncomeCategory(String incomeCategoryID) {
        Log.i(TAG, "DatabaseHelper.deleteIncomeCategory ...");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_INCOMECATEGORY,
                COLUMN_INCOMECATEGORY_INCOMECATEGORYID + "=" + incomeCategoryID,
                null);
    }

    public int getIncomeCategoryCount() {
        Log.i(TAG, "DatabaseHelper.getIncomeCategoryCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_INCOMECATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getIncomeCategoryIndex(String s) {
        return DatabaseHelper.getInstance(ctx).getIncomeCategory().indexOf(s);
    }

    public List<String> getIncomeCategory() {
        List<String> lstIncomeCategory = new ArrayList<String>();

        Log.i(TAG, "DatabaseHelper.getIncomeCategory ... ");

        String query = "SELECT " + COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME + " FROM " + TABLE_INCOMECATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeCategory.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOMECATEGORY_INCOMECATEGORYNAME)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeCategory;
    }

    public List<String> getIncomeCategoryID() {
        List<String> lstIncomeCategoryID = new ArrayList<String>();

        Log.i(TAG, "DatabaseHelper.getIncomeCategoryID ... ");

        String query = "SELECT " + COLUMN_INCOMECATEGORY_INCOMECATEGORYID + " FROM " + TABLE_INCOMECATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeCategoryID.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INCOMECATEGORY_INCOMECATEGORYID)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeCategoryID;
    }
    //endregion

    //region Income
    public void addIncome(DTO_Income income) {
        Log.i(TAG, "DatabaseHelper.addIncome ... " + income.getIncomeID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INCOME_INCOMENAME, income.getIncomeName());
        values.put(COLUMN_INCOME_INCOMEAMOUNT, income.getIncomeAmount());
        values.put(COLUMN_INCOME_INCOMEDATE, income.getIncomeDate());
        values.put(COLUMN_INCOME_INCOMENOTE, income.getIncomeNote());

        db.insert(TABLE_INCOME, null, values);

        db.close();
    }

    public void updateIncome(DTO_Income income, String incomeID) {
        Log.i(TAG, "DatabaseHelper.updateIncome ... " + income.getIncomeID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INCOME_INCOMENAME, income.getIncomeName());
        values.put(COLUMN_INCOME_INCOMEAMOUNT, income.getIncomeAmount());
        values.put(COLUMN_INCOME_INCOMEDATE, income.getIncomeDate());
        values.put(COLUMN_INCOME_INCOMENOTE, income.getIncomeNote());

        db.update(
                TABLE_INCOME,
                values,
                COLUMN_INCOME_INCOMEID + "=" + incomeID,
                null
        );

        db.close();
    }

    public void deleteIncome(String incomeID) {
        Log.i(TAG, "DatabaseHelper.deleteIncome ...");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_INCOME,
                COLUMN_INCOME_INCOMEID + "=" + incomeID,
                null);
    }

    public List<String> getIncomeID() {
        List<String> lstIncomeID = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getIncomeID ... ");
        String query = "SELECT " + COLUMN_INCOME_INCOMEID + " FROM " + TABLE_INCOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeID.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeID;
    }

    public List<String> getIncomeName() {
        List<String> lstIncomeName = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getIncomeName ... ");
        String query = "SELECT " + COLUMN_INCOME_INCOMENAME + " FROM " + TABLE_INCOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeName.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeName;
    }

    public List<String> getIncomeAmount() {
        List<String> lstIncomeAmount = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getIncomeAmount ... ");
        String query = "SELECT " + COLUMN_INCOME_INCOMEAMOUNT + " FROM " + TABLE_INCOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeAmount.add(String.format("%.1f", cursor.getDouble(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeAmount;
    }

    public List<String> getIncomeDate() {
        List<String> lstIncomeDate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getIncomeDate ... ");
        String query = "SELECT " + COLUMN_INCOME_INCOMEDATE + " FROM " + TABLE_INCOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeDate;
    }

    public List<String> getIncomeNote() {
        List<String> lstIncomeNote = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getIncomeNote ... ");
        String query = "SELECT " + COLUMN_INCOME_INCOMENOTE + " FROM " + TABLE_INCOME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstIncomeNote.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeNote;
    }

    public List<String[]> exportIncome(int month_quarter, int year, int selection) {
        List<String[]> lstIncomeRecord = new ArrayList<String[]>();
        Log.i(TAG, "DatabaseHelper.getIncomeRecord ... ");
        String query = "";
        switch (selection)
        {
            case 0:
                query = "SELECT * FROM " + TABLE_INCOME
                        + " WHERE strftime(\'%m\'," + COLUMN_INCOME_INCOMEDATE + ")=\'" + String.format("%02d", month_quarter) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_INCOME_INCOMEDATE + ")=\'" + year + "\'";
                break;
            case 1:
                query = "SELECT * FROM " + TABLE_INCOME
                        + " WHERE (strftime(\'%m\'," + COLUMN_INCOME_INCOMEDATE + ")=\'" + String.format("%02d", (month_quarter*3 - 2)) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_INCOME_INCOMEDATE + ")=\'" + year + "\')"
                        + " OR (strftime(\'%m\'," + COLUMN_INCOME_INCOMEDATE + ")=\'" + String.format("%02d", (month_quarter*3 - 1)) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_INCOME_INCOMEDATE + ")=\'" + year + "\')"
                        + " OR (strftime(\'%m\'," + COLUMN_INCOME_INCOMEDATE + ")=\'" + String.format("%02d", (month_quarter*3)) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_INCOME_INCOMEDATE + ")=\'" + year + "\')";
                break;
            case 2:
                query = "SELECT * FROM " + TABLE_INCOME
                        + " WHERE strftime(\'%Y\', " + COLUMN_INCOME_INCOMEDATE + ")=\'" + year + "\'";
                break;
             default:
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String[] temp = {
                    cursor.getString(0),
                    cursor.getString(1),
                    String.format("%.1f", cursor.getDouble(2)),
                    cursor.getString(3),
                    cursor.getString(4)};
            lstIncomeRecord.add(temp);
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstIncomeRecord;
    }
    //endregion

    //region ExpenseCategory
    public void createDefaultExpenseCategory() {
        int count = this.getExpenseCategoryCount();
        if (count == 0) {
            DTO_ExpenseCategory expenseCategory1 = new DTO_ExpenseCategory("Ăn uống");
            DTO_ExpenseCategory expenseCategory2 = new DTO_ExpenseCategory("Hoá đơn & Tiện ích");
            DTO_ExpenseCategory expenseCategory3 = new DTO_ExpenseCategory("Di chuyển");
            DTO_ExpenseCategory expenseCategory4 = new DTO_ExpenseCategory("Mua sắm");
            DTO_ExpenseCategory expenseCategory5 = new DTO_ExpenseCategory("Bạn bè & Người yêu");
            DTO_ExpenseCategory expenseCategory6 = new DTO_ExpenseCategory("Giải trí");
            DTO_ExpenseCategory expenseCategory7 = new DTO_ExpenseCategory("Du lịch");
            DTO_ExpenseCategory expenseCategory8 = new DTO_ExpenseCategory("Sức khoẻ");
            DTO_ExpenseCategory expenseCategory9 = new DTO_ExpenseCategory("Quà tặng & Từ thiện");
            DTO_ExpenseCategory expenseCategory10 = new DTO_ExpenseCategory("Gia đình");
            DTO_ExpenseCategory expenseCategory11 = new DTO_ExpenseCategory("Giáo dục");
            DTO_ExpenseCategory expenseCategory12 = new DTO_ExpenseCategory("Đầu tư");
            DTO_ExpenseCategory expenseCategory13 = new DTO_ExpenseCategory("Kinh doanh");
            DTO_ExpenseCategory expenseCategory14 = new DTO_ExpenseCategory("Bảo hiểm");
            DTO_ExpenseCategory expenseCategory15 = new DTO_ExpenseCategory("Chi phí");
            DTO_ExpenseCategory expenseCategory16 = new DTO_ExpenseCategory("Rút tiền");
            this.addExpenseCategory(expenseCategory1);
            this.addExpenseCategory(expenseCategory2);
            this.addExpenseCategory(expenseCategory3);
            this.addExpenseCategory(expenseCategory4);
            this.addExpenseCategory(expenseCategory5);
            this.addExpenseCategory(expenseCategory6);
            this.addExpenseCategory(expenseCategory7);
            this.addExpenseCategory(expenseCategory8);
            this.addExpenseCategory(expenseCategory9);
            this.addExpenseCategory(expenseCategory10);
            this.addExpenseCategory(expenseCategory11);
            this.addExpenseCategory(expenseCategory12);
            this.addExpenseCategory(expenseCategory13);
            this.addExpenseCategory(expenseCategory14);
            this.addExpenseCategory(expenseCategory15);
            this.addExpenseCategory(expenseCategory16);
        }
    }

    public void addExpenseCategory(DTO_ExpenseCategory expenseCategory) {
        Log.i(TAG, "DatabaseHelper.addExpenseCategory ... " + expenseCategory.getExpenseCategoryName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME, expenseCategory.getExpenseCategoryName());

        db.insert(TABLE_EXPENSECATEGORY, null, values);

        db.close();
    }

    public void updateExpenseCategory(DTO_ExpenseCategory expenseCategory, String expenseCategoryID) {
        Log.i(TAG, "DatabaseHelper.updateExpenseCategory ... " + expenseCategory.getExpenseCategoryName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME, expenseCategory.getExpenseCategoryName());

        db.update(
                TABLE_EXPENSECATEGORY,
                values,
                COLUMN_EXPENSECATEGORY_EXPENSECATEGORYID + "=" + expenseCategoryID,
                null
        );

        db.close();
    }

    public void deleteExpenseCategory(String expenseCategoryID) {
        Log.i(TAG, "DatabaseHelper.deleteExpenseCategory ...");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_EXPENSECATEGORY,
                COLUMN_EXPENSECATEGORY_EXPENSECATEGORYID + "=" + expenseCategoryID,
                null);
    }

    public int getExpenseCategoryCount() {
        Log.i(TAG, "DatabaseHelper.getExpenseCategoryCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_EXPENSECATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getExpenseCategoryIndex(String s) {
        return DatabaseHelper.getInstance(ctx).getExpenseCategory().indexOf(s);
    }

    public List<String> getExpenseCategory() {
        List<String> lstExpenseCategory = new ArrayList<String>();

        Log.i(TAG, "DatabaseHelper.getExpenseCategory ... ");

        String query = "SELECT " + COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME + " FROM " + TABLE_EXPENSECATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseCategory.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSECATEGORY_EXPENSECATEGORYNAME)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseCategory;
    }

    public List<String> getExpenseCategoryID() {
        List<String> lstExpenseCategoryID = new ArrayList<String>();

        Log.i(TAG, "DatabaseHelper.getExpenseCategoryID ... ");

        String query = "SELECT " + COLUMN_EXPENSECATEGORY_EXPENSECATEGORYID + " FROM " + TABLE_EXPENSECATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseCategoryID.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSECATEGORY_EXPENSECATEGORYID)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseCategoryID;
    }
    //endregion

    //region Expense
    public void addExpense(DTO_Expense expense) {
        Log.i(TAG, "DatabaseHelper.addExpense ... " + expense.getExpenseID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSE_EXPENSENAME, expense.getExpenseName());
        values.put(COLUMN_EXPENSE_EXPENSEAMOUNT, expense.getExpenseAmount());
        values.put(COLUMN_EXPENSE_EXPENSEDATE, expense.getExpenseDate());
        values.put(COLUMN_EXPENSE_EXPENSENOTE, expense.getExpenseNote());

        db.insert(TABLE_EXPENSE, null, values);

        db.close();
    }

    public void updateExpense(DTO_Expense expense, String expenseID) {
        Log.i(TAG, "DatabaseHelper.updateExpense ... " + expense.getExpenseID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSE_EXPENSENAME, expense.getExpenseName());
        values.put(COLUMN_EXPENSE_EXPENSEAMOUNT, expense.getExpenseAmount());
        values.put(COLUMN_EXPENSE_EXPENSEDATE, expense.getExpenseDate());
        values.put(COLUMN_EXPENSE_EXPENSENOTE, expense.getExpenseNote());

        db.update(
                TABLE_EXPENSE,
                values,
                COLUMN_EXPENSE_EXPENSEID + "=" + expenseID,
                null
        );

        db.close();
    }

    public void deleteExpense(String expenseID) {
        Log.i(TAG, "DatabaseHelper.deleteExpense ...");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_EXPENSE,
                COLUMN_EXPENSE_EXPENSEID + "=" + expenseID,
                null);
    }

    public List<String> getExpenseID() {
        List<String> lstExpenseID = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getExpenseID ... ");
        String query = "SELECT " + COLUMN_EXPENSE_EXPENSEID + " FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseID.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseID;
    }

    public List<String> getExpenseName() {
        List<String> lstExpenseName = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getExpenseName ... ");
        String query = "SELECT " + COLUMN_EXPENSE_EXPENSENAME + " FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseName.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseName;
    }

    public List<String> getExpenseAmount() {
        List<String> lstExpenseAmount = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getExpenseAmount ... ");
        String query = "SELECT " + COLUMN_EXPENSE_EXPENSEAMOUNT + " FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseAmount.add(String.format("%.1f", cursor.getDouble(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseAmount;
    }

    public List<String> getExpenseDate() {
        List<String> lstExpenseDate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getExpenseDate ... ");
        String query = "SELECT " + COLUMN_EXPENSE_EXPENSEDATE + " FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseDate;
    }

    public List<String> getExpenseNote() {
        List<String> lstExpenseNote = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getExpenseNote ... ");
        String query = "SELECT " + COLUMN_EXPENSE_EXPENSENOTE + " FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstExpenseNote.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseNote;
    }

    public List<String[]> exportExpense(int month_quarter, int year, int selection) {
        List<String[]> lstExpenseRecord = new ArrayList<String[]>();
        Log.i(TAG, "DatabaseHelper.getExpenseRecord ... ");
        String query = "";
        switch (selection)
        {
            case 0:
                query = "SELECT * FROM " + TABLE_EXPENSE
                        + " WHERE strftime(\'%m\'," + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + String.format("%02d", month_quarter) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + year + "\'";
                break;
            case 1:
                query = "SELECT * FROM " + TABLE_EXPENSE
                        + " WHERE (strftime(\'%m\'," + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + (month_quarter*3 - 2) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + year + "\')"
                        + "(strftime(\'%m\'," + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + (month_quarter*3 - 1) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + year + "\')"
                        + "(strftime(\'%m\'," + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + (month_quarter*3) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + year + "\')";
                break;
            case 2:
                query = "SELECT * FROM " + TABLE_EXPENSE
                        + " WHERE strftime(\'%Y\', " + COLUMN_EXPENSE_EXPENSEDATE + ")=\'" + year + "\'";
                break;
            default:
                break;
        }
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String[] temp = {
                    cursor.getString(0),
                    cursor.getString(1),
                    String.format("%.1f", cursor.getDouble(2)),
                    cursor.getString(3),
                    cursor.getString(4)};
            lstExpenseRecord.add(temp);
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstExpenseRecord;
    }
    //endregion

    //region Debt
    public void addDebt(DTO_Debt debt) {
        Log.i(TAG, "DatabaseHelper.addDebt ... " + debt.getDebtID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEBT_DEBTNAME, debt.getDebtName());
        values.put(COLUMN_DEBT_DEBTAMOUNT, debt.getDebtAmount());
        values.put(COLUMN_DEBT_DEBTINTERESTRATE, debt.getDebtInterestRate());
        values.put(COLUMN_DEBT_DEBTDATE, debt.getDebtDate());
        values.put(COLUMN_DEBT_DEBTEXPIRATIONDATE, debt.getDebtExpirationDate());
        values.put(COLUMN_DEBT_DEBTNOTE, debt.getDebtNote());

        db.insert(TABLE_DEBT, null, values);

        db.close();
    }

    public void updateDebt(DTO_Debt debt, String debtID) {
        Log.i(TAG, "DatabaseHelper.updateDebt ... " + debt.getDebtID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEBT_DEBTNAME, debt.getDebtName());
        values.put(COLUMN_DEBT_DEBTAMOUNT, debt.getDebtAmount());
        values.put(COLUMN_DEBT_DEBTINTERESTRATE, debt.getDebtInterestRate());
        values.put(COLUMN_DEBT_DEBTDATE, debt.getDebtDate());
        values.put(COLUMN_DEBT_DEBTEXPIRATIONDATE, debt.getDebtExpirationDate());
        values.put(COLUMN_DEBT_DEBTNOTE, debt.getDebtNote());

        db.update(
                TABLE_DEBT,
                values,
                COLUMN_DEBT_DEBTID + "=" + debtID,
                null
        );

        db.close();
    }

    public void deleteDebt(String debtID) {
        Log.i(TAG, "DatabaseHelper.deleteDebt ...");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_DEBT,
                COLUMN_DEBT_DEBTID + "=" + debtID,
                null);
    }

    public List<String> getDebtID() {
        List<String> lstDebtID = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtID ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTID + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtID.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtID;
    }

    public List<String> getDebtName() {
        List<String> lstDebtName = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtName ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTNAME + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtName.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtName;
    }

    public List<String> getDebtAmount() {
        List<String> lstDebtAmount = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtAmount ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTAMOUNT + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtAmount.add(String.format("%.1f", cursor.getDouble(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtAmount;
    }

    public List<String> getDebtInterestRate() {
        List<String> lstDebtInterestRate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtInterestRate ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTINTERESTRATE + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtInterestRate.add(String.format("%.2f", cursor.getDouble(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtInterestRate;
    }

    public List<String> getDebtDate() {
        List<String> lstDebtDate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtDate ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTDATE + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtDate;
    }

    public List<String> getDebtExpirationDate() {
        List<String> lstDebtExpirationDate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtExpirationDate ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTEXPIRATIONDATE + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtExpirationDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtExpirationDate;
    }

    public List<String> getDebtNote() {
        List<String> lstDebtNote = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getDebtNote ... ");
        String query = "SELECT " + COLUMN_DEBT_DEBTNOTE + " FROM " + TABLE_DEBT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstDebtNote.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtNote;
    }

    public List<String[]> exportDebt(int month_quarter, int year, int selection) {
        List<String[]> lstDebtRecord = new ArrayList<String[]>();
        Log.i(TAG, "DatabaseHelper.getDebtRecord ... ");
        String query = "";
        switch (selection)
        {
            case 0:
                query = "SELECT * FROM " + TABLE_DEBT
                        + " WHERE strftime(\'%m\'," + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + String.format("%02d", month_quarter) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + year + "\'";
                break;
            case 1:
                query = "SELECT * FROM " + TABLE_DEBT
                        + " WHERE (strftime(\'%m\'," + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + (month_quarter*3 - 2) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + year + "\')"
                        + "(strftime(\'%m\'," + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + (month_quarter*3 - 1) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + year + "\')"
                        + "(strftime(\'%m\'," + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + (month_quarter*3) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + year + "\')";
                break;
            case 2:
                query = "SELECT * FROM " + TABLE_DEBT
                        + " WHERE strftime(\'%Y\', " + COLUMN_DEBT_DEBTINTERESTRATE + ")=\'" + year + "\'";
                break;
            default:
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String[] temp = {
                    cursor.getString(0),
                    cursor.getString(1),
                    String.format("%.1f", cursor.getDouble(2)),
                    cursor.getString(3),
                    cursor.getString(4)};
            lstDebtRecord.add(temp);
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstDebtRecord;
    }
    //endregion

    //region Loan
    public void addLoan(DTO_Loan loan) {
        Log.i(TAG, "DatabaseHelper.addLoan ... " + loan.getLoanID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOAN_LOANNAME, loan.getLoanName());
        values.put(COLUMN_LOAN_LOANAMOUNT, loan.getLoanAmount());
        values.put(COLUMN_LOAN_LOANINTERESTRATE, loan.getLoanInterestRate());
        values.put(COLUMN_LOAN_LOANDATE, loan.getLoanDate());
        values.put(COLUMN_LOAN_LOANEXPIRATIONDATE, loan.getLoanExpirationDate());
        values.put(COLUMN_LOAN_LOANNOTE, loan.getLoanNote());

        db.insert(TABLE_LOAN, null, values);

        db.close();
    }

    public void updateLoan(DTO_Loan loan, String loanID) {
        Log.i(TAG, "DatabaseHelper.updateLoan ... " + loan.getLoanID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOAN_LOANNAME, loan.getLoanName());
        values.put(COLUMN_LOAN_LOANAMOUNT, loan.getLoanAmount());
        values.put(COLUMN_LOAN_LOANINTERESTRATE, loan.getLoanInterestRate());
        values.put(COLUMN_LOAN_LOANDATE, loan.getLoanDate());
        values.put(COLUMN_LOAN_LOANEXPIRATIONDATE, loan.getLoanExpirationDate());
        values.put(COLUMN_LOAN_LOANNOTE, loan.getLoanNote());

        db.update(
                TABLE_LOAN,
                values,
                COLUMN_LOAN_LOANID + "=" + loanID,
                null
        );

        db.close();
    }

    public void deleteLoan(String loanID) {
        Log.i(TAG, "DatabaseHelper.deleteLoan ...");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_LOAN,
                COLUMN_LOAN_LOANID + "=" + loanID,
                null);
    }

    public List<String> getLoanID() {
        List<String> lstLoanID = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanID ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANID + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanID.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanID;
    }

    public List<String> getLoanName() {
        List<String> lstLoanName = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanName ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANNAME + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanName.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanName;
    }

    public List<String> getLoanAmount() {
        List<String> lstLoanAmount = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanAmount ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANAMOUNT + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanAmount.add(String.format("%.1f", cursor.getDouble(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanAmount;
    }

    public List<String> getLoanInterestRate() {
        List<String> lstLoanInterestRate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanInterestRate ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANINTERESTRATE + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanInterestRate.add(String.format("%.2f", cursor.getDouble(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanInterestRate;
    }

    public List<String> getLoanDate() {
        List<String> lstLoanDate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanDate ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANDATE + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanDate;
    }

    public List<String> getLoanExpirationDate() {
        List<String> lstLoanExpirationDate = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanExpirationDate ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANEXPIRATIONDATE + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanExpirationDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanExpirationDate;
    }

    public List<String> getLoanNote() {
        List<String> lstLoanNote = new ArrayList<String>();
        Log.i(TAG, "DatabaseHelper.getLoanNote ... ");
        String query = "SELECT " + COLUMN_LOAN_LOANNOTE + " FROM " + TABLE_LOAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                lstLoanNote.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanNote;
    }

    public List<String[]> exportLoan(int month_quarter, int year, int selection) {
        List<String[]> lstLoanRecord = new ArrayList<String[]>();
        Log.i(TAG, "DatabaseHelper.getLoanRecord ... ");
        String query = "";
        switch (selection)
        {
            case 0:
                query = "SELECT * FROM " + TABLE_LOAN
                        + " WHERE strftime(\'%m\'," + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + String.format("%02d", month_quarter) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + year + "\'";
                break;
            case 1:
                query = "SELECT * FROM " + TABLE_LOAN
                        + " WHERE (strftime(\'%m\'," + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + (month_quarter*3 - 2) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + year + "\')"
                        + "(strftime(\'%m\'," + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + (month_quarter*3 - 1) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + year + "\')"
                        + "(strftime(\'%m\'," + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + (month_quarter*3) + "\' AND "
                        + "strftime(\'%Y\', " + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + year + "\')";
                break;
            case 2:
                query = "SELECT * FROM " + TABLE_LOAN
                        + " WHERE strftime(\'%Y\', " + COLUMN_LOAN_LOANINTERESTRATE + ")=\'" + year + "\'";
                break;
            default:
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String[] temp = {
                    cursor.getString(0),
                    cursor.getString(1),
                    String.format("%.1f", cursor.getDouble(2)),
                    cursor.getString(3),
                    cursor.getString(4)};
            lstLoanRecord.add(temp);
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return lstLoanRecord;
    }
    //endregion

    //region Others
    public List<Pair<String, Integer>> getOverallIncomeInformation(String tableName, String groupBy, String columns) {
        List<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
        Log.i(TAG, "DatabaseHelper.getOverall"+tableName+"Information ... ");

        String countQuery = "SELECT " + columns + " FROM " + tableName + " GROUP BY " + groupBy;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                result.add(new Pair<String, Integer>(cursor.getString(0), cursor.getInt(1)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return result;
    }
    //endregion

    public List<Pair<String, Integer>> getOverallDataInformation(String tableName, String groupBy, String columns) {
        List<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
        Log.i(TAG, "DatabaseHelper.getOverall" + tableName + "Information ... ");

        String countQuery = "SELECT " + columns + " FROM " + tableName + " GROUP BY " + groupBy;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int sign = (tableName.equals(DatabaseHelper.TABLE_EXPENSE) || tableName.equals(DatabaseHelper.TABLE_DEBT)) ? -1 : 1;

                result.add(new Pair<String, Integer>(cursor.getString(0), sign * cursor.getInt(1)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        return result;
    }
}
