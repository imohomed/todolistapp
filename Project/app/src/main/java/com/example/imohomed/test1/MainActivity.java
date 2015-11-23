package com.example.imohomed.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ArrayAdapter<Item> itemsAdapter;
    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE = 20;
    private TodoDatabaseHelper db;

    /*
    private void readItems(){
        File filesDir = getFilesDir();
        File file = new File(filesDir,"todo.txt");
        try{
            items = new ArrayList<String>(FileUtils.readLines(file));
        }catch(IOException e) {
            Log.v("Exception",e.toString());
            items = new ArrayList<String>();
        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File file = new File(filesDir,"todo.txt");
        try{
            FileUtils.writeLines(file,items);
        }catch(IOException e){};
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Simple Todo");
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        //items = new ArrayList<String>();
        db = TodoDatabaseHelper.getInstance(this);
        items = db.getAllItems();
        //readItems();
        itemsAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemsAdapter);
        /*
        items.add("Item #1");
        items.add("Item #2");
        items.add("Item #3");
        */
        etEditText = (EditText) findViewById(R.id.etEditText);

        //lvItems.setOnItemLongClickListener();

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
        public boolean onItemLongClick(AdapterView<?> parent,View view, int position, long id)
            {
                Item removedItem = items.remove(position);
                db.deleteItem(removedItem);
                itemsAdapter.notifyDataSetChanged();
                //writeItems();
                return true;
            }
        });


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Item tapped", String.format("%d",position));
                //startActivity(new Intent(this, EditItemActivity.class));
                Intent i;
                i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("todoItemText",items.get(position).text);
                i.putExtra("itemPosition",position);
                startActivityForResult(i,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String updatedText = data.getExtras().getString("updatedItem");
            int position = data.getExtras().getInt("itemPosition", 0);
            Item updatedItem = items.get(position);
            updatedItem.text = updatedText;
            items.set(position,updatedItem);
            db.updateItem(updatedItem);

            //items.set(position,updatedText);
            itemsAdapter.notifyDataSetChanged();
            //writeItems();
            // Toast the name to display temporarily on screen
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();

        }
    }

    public void onaddItem(View view) {
        Item newItem = new Item();
        newItem.text = etEditText.getText().toString();
        newItem = db.addItem(newItem);
        items.add(newItem);
        itemsAdapter.notifyDataSetChanged();
        //itemsAdapter.add();
        etEditText.setText("");
        //writeItems();
    }
}
