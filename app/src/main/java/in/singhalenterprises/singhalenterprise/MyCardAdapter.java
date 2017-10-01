package in.singhalenterprises.singhalenterprise;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

class MyCardAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private String[] names;
    private int[] drawables;
    private Context context;
    private int i;
    private String[] ids={"home","products","blog","product list","ecatalogue","home_1"};
    private int id;
    protected View view;
    static int count = 1;

    MyCardAdapter (Context context,int[] drawables,String[] names,int id,int i){
        this.context=context;
        this.drawables=drawables;
        this.names=names;
        this.id=id;
        this.i=i;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(ids[id].equals("products") || ids[id].equals("home_1")){
            view= LayoutInflater.from(context).inflate(R.layout.layout_card,parent,false);
        }
        else
        view= LayoutInflater.from(context).inflate(R.layout.layout_card_2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.iv.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),drawables[position],100,100));
       // Glide.with(context).load(drawables[position]).into(holder.iv);
        //holder.iv.setImageResource(drawables[position]);
        holder.tv.setText(names[position]);
        final int pos=position;

        if(ids[id].equals("home")){
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,ProductListActivity.class);
                    intent.putExtra("ITEM_INDEX",pos);
                    context.startActivity(intent);
                }
            });
        }
        if(ids[id].equals("home_1")){
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,ProductDetailActivity.class);
                    intent.putExtra("ITEM_POS",pos);
                    intent.putExtra("ITEM_INDEX",i);
                    context.startActivity(intent);
                }
            });
        }
        if(ids[id].equals("products") || ids[id].equals("product list")){
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(names[pos].equals("More Items")){
                        Intent intent=new Intent(context,ProductListActivity.class);
                        intent.putExtra("ITEM_INDEX",i);
                        context.startActivity(intent);
                    }
                    else{
                        Intent intent=new Intent(context,ProductDetailActivity.class);
                        intent.putExtra("ITEM_POS",pos);
                        intent.putExtra("ITEM_INDEX",i);
                        context.startActivity(intent);
                    }
                }
            });
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(names[pos].equals("More Items")){
                        Intent intent=new Intent(context,ProductListActivity.class);
                        intent.putExtra("ITEM_INDEX",i);
                        context.startActivity(intent);
                    }
                    else{
                        Intent intent=new Intent(context,ProductDetailActivity.class);
                        intent.putExtra("ITEM_POS",pos);
                        intent.putExtra("ITEM_INDEX",i);
                        context.startActivity(intent);
                    }
                }
            });
        }

        if(ids[id].equals("ecatalogue")){
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(isNetworkEnabled()) {
                        Intent intent = new Intent(context, BlogActivity.class);
                        intent.putExtra("ITEM_CLICKED", pos);
                        intent.putExtra("ACTION_NAME", ids[id]);
                        context.startActivity(intent);
                    }
                    else{
                        Snackbar.make(view, "No Internet Connection!!!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(isNetworkEnabled()) {
                        Intent intent = new Intent(context, BlogActivity.class);
                        intent.putExtra("ITEM_CLICKED", pos);
                        intent.putExtra("ACTION_NAME", ids[id]);
                        context.startActivity(intent);
                    }
                    else{
                        Snackbar.make(view, "No Internet Connection!!!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });


        }
        if(ids[id].equals("blog")){


        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkEnabled()){

                    Intent intent=new Intent(context,BlogActivity.class);
                    intent.putExtra("ITEM_CLICKED",pos);
                    intent.putExtra("ACTION_NAME",ids[id]);
                    context.startActivity(intent);
                }else {
                    Snackbar.make(view, "No Internet Connection!!!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                }
        });
        }
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        Log.v("DOWNSAMPLING "+count,"height :"+height+" width :"+width+" reqh :"+reqHeight+" reqW :"+reqWidth);

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        Log.v("DOWNSAMPLED "+count,"height :"+height+" width :"+width+" reqh :"+reqHeight+" reqW :"+reqWidth+" insamplesize :"+inSampleSize);

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Log.v("DECODE"+count+" "+resId,"decoding ");
        count++;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    private boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder{
     SimpleDraweeView iv;
     TextView tv;
        MyViewHolder(View itemView) {
        super(itemView);
        iv=itemView.findViewById(R.id.image_card);
        tv=itemView.findViewById(R.id.text_card);

    }
}