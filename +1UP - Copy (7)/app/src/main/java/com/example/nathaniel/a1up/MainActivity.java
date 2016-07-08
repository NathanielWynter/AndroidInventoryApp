package com.example.nathaniel.a1up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nameEntered, numberEntered, quantityEntered, priceEntered, dateEntered;
    ImageView itemImage;
    List<Inventory> Inventories = new ArrayList<>(); //********    fix issue with Inventory displaying
    ListView inventoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameEntered = (EditText) findViewById(R.id.enteredName);
        numberEntered = (EditText) findViewById(R.id.enteredNumber);
        quantityEntered = (EditText) findViewById(R.id.enteredQuantity);
        priceEntered = (EditText) findViewById(R.id.enteredPrice);
        dateEntered = (EditText) findViewById(R.id.enteredDate);
        inventoryListView = (ListView) findViewById(R.id.listView);
//        itemImage = (ImageView) findViewById(R.id.itemImage);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

        final Button addButton = (Button) findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInventory(nameEntered.getText().toString(), numberEntered.getText().toString(), quantityEntered.getText().toString(),
                        priceEntered.getText().toString(), dateEntered.getText().toString());
                populateList();
                Toast.makeText(getApplicationContext(), quantityEntered.getText().toString() + " " + nameEntered.getText().toString() + " Added", Toast.LENGTH_SHORT).show();
            }
        });

        nameEntered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addButton.setEnabled(!nameEntered.getText().toString().trim().isEmpty());//********      not working consistently why???????
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//       itemImage.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public  void onClick(View views){
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
//            }
//        });
//    }
//
//    private void addInventoryItem(String nameEntered, String numberEntered, String quantityEntered,
//                                  String priceEntered, String dateEntered) {
//        Inventories.add(new Inventory(nameEntered, numberEntered, quantityEntered, priceEntered, dateEntered));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    public void onActivityResult(int reqCode, int resCode, Intent data){
//        if (resCode == RESULT_OK){
//            if (reqCode == 1)
//                itemImage.setImageURI(data.getData());
//        }
//    }

    private void populateList(){
        ArrayAdapter<Inventory> adapter = new InventoryListAdapter();
        inventoryListView.setAdapter(adapter);
    }
    private void addInventory (String nameEntered, String numberEntered, String quantityEntered,
                               String priceEntered, String dateEntered){
        Inventories.add(new Inventory(nameEntered, numberEntered, quantityEntered,
                priceEntered, dateEntered));
    }

    private class InventoryListAdapter extends ArrayAdapter<Inventory> {
        public InventoryListAdapter() {
            super(MainActivity.this, R.layout.listview_item, Inventories);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);


            Inventory currentItem = Inventories.get(position);

            TextView item_name = (TextView) view.findViewById(R.id.itemName);
            item_name.setText(currentItem.getNameEntered());
            TextView item_number = (TextView) view.findViewById(R.id.itemNumber);
            item_number.setText(currentItem.getNumberEntered());
            TextView item_quantity = (TextView) view.findViewById(R.id.itemQuantity);
            item_quantity.setText(currentItem.getQuantityEntered());
            TextView item_price = (TextView) view.findViewById(R.id.itemPrice);
            item_price.setText(currentItem.getPriceEntered());
            TextView item_date = (TextView) view.findViewById(R.id.itemDate);
            item_date.setText(currentItem.getDateEntered());


            return view;

        }
    }

}



