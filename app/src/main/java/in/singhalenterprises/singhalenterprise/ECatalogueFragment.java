package in.singhalenterprises.singhalenterprise;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class ECatalogueFragment extends Fragment {

    RecyclerView recyclerView;
    int[] drawables={R.drawable.logo,R.drawable.logo,R.drawable.logo};
    String[] names={"View Chart","View Catalogue","Rifle Rack Catalogue"};


    public ECatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ecatalogue, container, false);
        recyclerView=view.findViewById(R.id.recycler_ecatalogue);
        recyclerView.setAdapter(new MyCardAdapter(getContext(),drawables,names,4,99));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return view;
    }

}
