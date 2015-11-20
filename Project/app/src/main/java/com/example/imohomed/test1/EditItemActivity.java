package com.example.imohomed.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditText;
    int positionInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Item");
        setContentView(R.layout.activity_edit_item);
        String originalText = getIntent().getStringExtra("todoItemText");
        positionInList = getIntent().getIntExtra("itemPosition",0);
        Log.v("EditItemActivity",originalText);
        etEditText = (EditText) findViewById(R.id.etTodoItem);
        etEditText.setText(originalText);
        etEditText.setSelection(originalText.length());
    }

    public void onSaveItem(View view) {
        String updatedText = etEditText.getText().toString();
        Intent data = new Intent();
        data.putExtra("updatedItem", updatedText);
        data.putExtra("itemPosition", positionInList);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
