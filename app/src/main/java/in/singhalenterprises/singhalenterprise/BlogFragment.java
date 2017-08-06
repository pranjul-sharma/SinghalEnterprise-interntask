package in.singhalenterprises.singhalenterprise;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    RecyclerView recyclerView;
    int[] drawables={R.drawable.cyber,R.drawable.happy_new_year,R.drawable.merr_christmas};
    String[] names={"Cyber Security Charts","Happy New Year","Merry Christmas"};
    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_blog, container, false);
        recyclerView=view.findViewById(R.id.recycler_blog);
        recyclerView.setAdapter(new MyCardAdapter(getContext(),drawables,names,2,99));//99 is extra value and is not needed here , but as in its constructor, it is required only in case of product category
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return view;

    }

}
