package com.example.imohomed.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditText;
    int positionInList;
    RadioGroup rgPriority;

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
        rgPriority = (RadioGroup) findViewById(R.id.radioPriority);
        String priority = getIntent().getStringExtra("itemPriority");
        switch(priority)
        {
            case "Medium":
                RadioButton rbMedium = (RadioButton) findViewById(R.id.rbMedium);
                rbMedium.setChecked(true);
                break;
            case "High":
                RadioButton rbHigh = (RadioButton) findViewById(R.id.rbHigh);
                rbHigh.setChecked(true);
                break;

        }
    }

    public void onSaveItem(View view) {
        String updatedText = etEditText.getText().toString();
        Intent data = new Intent();
        data.putExtra("updatedItem", updatedText);
        data.putExtra("itemPosition", positionInList);
        RadioButton selectedButton = (RadioButton) findViewById(rgPriority.getCheckedRadioButtonId());
        data.putExtra("itemPriority", selectedButton.getText());
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
