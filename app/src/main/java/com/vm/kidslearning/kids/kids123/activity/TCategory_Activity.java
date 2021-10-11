package com.vm.kidslearning.kids.kids123.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vm.kidslearning.kids.kids123.R;
import com.vm.kidslearning.kids.kids123.adapter.CategoryAdapter;
import com.vm.kidslearning.kids.kids123.dbhandler.DatabaseHandler;
import com.vm.kidslearning.kids.kids123.model.CategoryModel;

import java.util.ArrayList;

public class TCategory_Activity extends AppCompatActivity {

    RecyclerView rvcustomername;
    DatabaseHandler databaseHandler ;
    ArrayList<CategoryModel> categorylist = new ArrayList<>();
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcategory);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Categories");


        rvcustomername = findViewById(R.id.rc_cat);
        databaseHandler = new DatabaseHandler(this);

        categorylist = databaseHandler.getCategory();

        rvcustomername.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new CategoryAdapter(this, categorylist);

        rvcustomername.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch( item.getItemId())
        {
            case android.R.id.home:

                Intent i  = new Intent(getApplicationContext(),Home_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
