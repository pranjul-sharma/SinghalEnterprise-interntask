package in.singhalenterprises.singhalenterprise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class ProductListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        int index=getIntent().getIntExtra("ITEM_INDEX",13);
        getSupportActionBar().setTitle(getString(ProductsContract.category[index]));
        textView=(TextView)findViewById(R.id.tt_product_list);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_product_list);
        textView.setText(getString(ProductsContract.catDetails[index]));
        if(index!=13){
            recyclerView.setAdapter(new MyCardAdapter(this,ProductsContract.drawablesProducts[index],ProductsContract.namesProducts[index],3,index));
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }
    }
}
