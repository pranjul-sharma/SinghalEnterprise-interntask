package in.singhalenterprises.singhalenterprise;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProductFragment extends Fragment {

    RecyclerView recyclerView[]=new RecyclerView[12];
    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView[0]=(RecyclerView)view.findViewById(R.id.recycler_product);
        recyclerView[1]=(RecyclerView)view.findViewById(R.id.recycler_display_board);
        recyclerView[2]=(RecyclerView)view.findViewById(R.id.recycler_training);
        recyclerView[3]=(RecyclerView)view.findViewById(R.id.recycler_printing_services);
        recyclerView[4]=(RecyclerView)view.findViewById(R.id.recycler_printing_cartridge);
        recyclerView[5]=(RecyclerView)view.findViewById(R.id.recycler_paper_shredder);
        recyclerView[6]=(RecyclerView)view.findViewById(R.id.recycler_folder_visitor_book);
        recyclerView[7]=(RecyclerView)view.findViewById(R.id.recycler_search_light);
        recyclerView[8]=(RecyclerView)view.findViewById(R.id.recycler_sand_model_set);
        recyclerView[9]=(RecyclerView)view.findViewById(R.id.recycler_rifle_rack);
        recyclerView[10]=(RecyclerView)view.findViewById(R.id.recycler_brass);
        recyclerView[11]=(RecyclerView)view.findViewById(R.id.recycler_trophy);



        for(int i=0;i<12;i++){
            int[] temp;
            String[] temp1;
            if(ProductsContract.drawablesProducts[i].length<=5){
                temp=ProductsContract.drawablesProducts[i];
                temp1=ProductsContract.namesProducts[i];

            }else{
                temp= new int[]{ProductsContract.drawablesProducts[i][0],ProductsContract.drawablesProducts[i][1],ProductsContract.drawablesProducts[i][2],ProductsContract.drawablesProducts[i][3],ProductsContract.drawablesProducts[i][4],R.drawable.more_fallback};
                temp1=new String[]{ProductsContract.namesProducts[i][0],ProductsContract.namesProducts[i][1],ProductsContract.namesProducts[i][2],ProductsContract.namesProducts[i][3],ProductsContract.namesProducts[i][4],"More Items"};
            }

            recyclerView[i].setAdapter(new MyCardAdapter(getActivity(),temp,temp1,1,i));
            recyclerView[i].setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        }
        return view;
    }

}
