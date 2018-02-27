package inararamji.refresh;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemInfo extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_inventory:
                    intent = new Intent(AddItemInfo.this, Inventory.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_add_item:
                    intent = new Intent(AddItemInfo.this, AddItem.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_settings:
                    intent = new Intent(AddItemInfo.this, Settings.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_info);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void takePicture(View view) {
        dispatchTakePictureIntent();
    }

    public void saveItem(View view) {
        EditText item = (EditText)findViewById(R.id.item);
        EditText quantity = (EditText)findViewById(R.id.quantity);
        EditText expiryDate = (EditText)findViewById(R.id.expiry_date);
        EditText addInfo = (EditText)findViewById(R.id.add_info);
        Item newItem = new Item(item.getText().toString(),
                Integer.parseInt(quantity.getText().toString()),
                expiryDate.getText().toString(), addInfo.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference rootRef = database.getReference();

        DatabaseReference itemRef = rootRef.child("Item");

        itemRef.push().setValue(newItem);




    }
}
