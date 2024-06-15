package com.example.scientific_calculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

// TODO: -> Add buttons of go back and clear History
// TODO: -> Changing the theme of the app
// TODO: -> Add Menu list to go to the History page

public class HistoriqueActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    DatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("History");
        }

        linearLayout = findViewById(R.id.linearLayout);
        dbHelper = new DatabaseHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DatabaseHelper.COLUMN_EXPRESSION, DatabaseHelper.COLUMN_RESULT};
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_HISTORY,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String expression = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPRESSION));
            double result = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RESULT));

            createTextView(expression, result);
        }

        MaterialButton clearButton = findViewById(R.id.bHistorique);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
                linearLayout.removeAllViews();
            }
        });

        cursor.close();
    }

//    private void createTextView(String expression, double result) {
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                dpToPx(100)
//        );
//        params.weight = 1;
//
//        TextView textView = new TextView(this);
//        textView.setLayoutParams(params);
//        textView.setBackgroundColor(Color.WHITE);
//        textView.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
//        textView.setGravity(Gravity.END);
//        textView.setTextColor(Color.parseColor("#A8A8A8"));
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
//
//        String text = expression + " = " + result;
//        textView.setText(text);
//
//        linearLayout.addView(textView);
//    }

    private void createTextView(final String expression, double result) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dpToPx(100)
        );
        params.weight = 1;

        TextView textView = new TextView(this);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.WHITE);
        textView.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
        textView.setGravity(Gravity.END);
        textView.setTextColor(Color.parseColor("#A8A8A8"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        String text = expression + " = " + result;
        textView.setText(text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("expression", expression);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        linearLayout.addView(textView);
    }

    private void clearHistory() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_HISTORY, null, null);
        db.close();
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}


//public class HistoriqueActivity extends AppCompatActivity {
//
//    TextView tvsec;
//
//    StringBuilder history = new StringBuilder();
//
//    DatabaseHelper dbHelper;
//
//    private SQLiteDatabase db;
//
//    LinearLayout linearLayout;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_historique);
//
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        tvsec = findViewById(R.id.tvsec);
//
//        dbHelper = new DatabaseHelper(this);
////        db = dbHelper.getWritableDatabase();
//
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String[] projection = {DatabaseHelper.COLUMN_EXPRESSION, DatabaseHelper.COLUMN_RESULT};
//        Cursor cursor = db.query(
//                DatabaseHelper.TABLE_HISTORY,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        StringBuilder historyData = new StringBuilder();
//
//        while (cursor.moveToNext()) {
//            String expression = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPRESSION));
//            double result = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RESULT));
//
//            historyData.append(expression).append(" = ").append(result).append("\n");
//        }
//
//        cursor.close();
////        db.close();
//
//        tvsec.setText(historyData.toString());
//
//
//    }
//
//}
//        tvsec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("copy", tvsec.getText().toString());
//                clipboard.setPrimaryClip(clip);
//                Toast.makeText(HistoriqueActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
//            }
//        });
//        tvsec.setOnClickListener();

//        final TextView helloTextView = (TextView) findViewById(R.id.text_view_id);
//        helloTextView.setText(R.string.user_greeting);




//import android.content.ClipData;
//        import android.content.ClipboardManager;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.database.Cursor;
//        import android.database.sqlite.SQLiteDatabase;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.LinearLayout;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//public class HistoriqueActivity extends AppCompatActivity {
//
//    TextView tvsec;
//
//    StringBuilder history = new StringBuilder();
//
//    DatabaseHelper dbHelper;
//
//    private SQLiteDatabase db;
//
//    LinearLayout linearLayout;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_historique);
//
//        tvsec = findViewById(R.id.tvsec);
//
//        dbHelper = new DatabaseHelper(this);
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String[] projection = {DatabaseHelper.COLUMN_EXPRESSION, DatabaseHelper.COLUMN_RESULT};
//        Cursor cursor = db.query(
//                DatabaseHelper.TABLE_HISTORY,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        StringBuilder historyData = new StringBuilder();
//
//        while (cursor.moveToNext()) {
//            String expression = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPRESSION));
//            double result = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RESULT));
//
//            historyData.append(expression).append(" = ").append(result).append("\n");
//        }
//
//        cursor.close();
//
//        tvsec.setText(historyData.toString());
//
//        tvsec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView clickedTextView = (TextView) v;
//                String lineText = clickedTextView.getText().toString();
//
//                // Start MainActivity with the line text as an extra data
//                Intent intent = new Intent(HistoriqueActivity.this, MainActivity.class);
//                intent.putExtra("lineText", lineText);
//                startActivity(intent);
//            }
//        });
//    }
//}

