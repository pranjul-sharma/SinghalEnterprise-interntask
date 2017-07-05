package in.singhalenterprises.singhalenterprise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {

    TextView textDesc,textCategory,textTag;
    ImageView imageView;
    int i1,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent intent=getIntent();
        i1=intent.getIntExtra("ITEM_INDEX",12);
        i2=intent.getIntExtra("ITEM_POS",99);
        getSupportActionBar().setTitle(ProductsContract.namesProducts[i1][i2]);

        imageView=(ImageView)findViewById(R.id.img_product_detail);
        textCategory=(TextView)findViewById(R.id.text_product_category);
        textDesc=(TextView)findViewById(R.id.text_product_desc);
        textTag=(TextView)findViewById(R.id.text_product_tag);

        imageView.setImageResource(ProductsContract.drawablesProducts[i1][i2]);
        textCategory.setText(ProductsContract.category[i1]);
        textTag.setText(ProductsContract.namesProducts[i1][i2]);
        textDesc.setText(ProductsContract.detailsProduct[i1][i2]);
    }
}
