package com.example.android.login;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by avhirup on 11/17/2015.
 */
public class GenerateCSV {

    private static IncomeDatabase incomeDatabase;
    private static ExpenseDatabase expenseDatabase;

    GenerateCSV(String sFilename, Context context) {
        generateCsvFile(sFilename, context);
    }

    private void generateCsvFile(String sFileName, Context context) {
        incomeDatabase = new IncomeDatabase(context);
        expenseDatabase = new ExpenseDatabase(context);

        String TAG = "helper";
        String state = Environment.getExternalStorageState();
        Log.e(TAG, state);
        if ("mounted".equals(state)) {
            Boolean saveState = true;
            File pPath = Environment.getExternalStorageDirectory();

            if (!pPath.exists()) {
                boolean bReturn = pPath.mkdirs();
                Log.e(TAG, "mkdirs returned: " + bReturn);
            }
            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), sFileName);
                file.createNewFile();
                String outputLocation = file.getAbsolutePath().toString();
                Log.e(TAG, outputLocation);

                FileWriter writer = new FileWriter(file.getAbsoluteFile());

                writer.append("Date");
                writer.append(',');
                writer.append("Income Tag");
                writer.append(',');
                writer.append("Amount");
                writer.append("\n");

                Cursor cursor = incomeDatabase.getIncome();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String date = cursor.getString(0);
                    writer.append(date);
                    writer.append(",");
                    String incometag = cursor.getString(1);
                    writer.append(incometag);
                    writer.append(",");
                    String amount = cursor.getString(2);
                    writer.append(amount);
                    writer.append('\n');
                    cursor.moveToNext();

                }

                writer.append('\n');

                writer.append("Date");
                writer.append(',');
                writer.append("Expense Tag");
                writer.append(',');
                writer.append("Amount");
                writer.append("\n");

                cursor = expenseDatabase.getExpense();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String date = cursor.getString(0);
                    writer.append(date);
                    writer.append(",");
                    String expensetag = cursor.getString(1);
                    writer.append(expensetag);
                    writer.append(",");
                    String amount = cursor.getString(2);
                    writer.append(amount);
                    writer.append('\n');
                    cursor.moveToNext();

                }

                writer.append('\n');
                //generate whatever data you want

                writer.flush();
                writer.close();
            } catch (IOException e) {
                Log.d(TAG, "Could not create file: " + e.toString());
                saveState = false;
            }
        }

    }
}
