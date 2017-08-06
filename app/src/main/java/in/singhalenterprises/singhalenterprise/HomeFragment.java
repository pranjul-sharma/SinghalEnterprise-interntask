package in.singhalenterprises.singhalenterprise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView1,recyclerView2;
    ImageSwitcher switcher;
    Timer timer;

    int[] images={R.drawable.logo,R.drawable.img_1,R.drawable.img_2,R.drawable.img_3};
    int position=0;
    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        switcher=view.findViewById(R.id.image_switcher);
        recyclerView1=view.findViewById(R.id.recycler_home_1);
        recyclerView2=view.findViewById(R.id.recycler_home_2);
        int[] temp=new int[13];
        String[] temp1=new String[13];
        for (int i=0;i<13;i++){
            temp[i]=ProductsContract.drawablesProducts[i][0];
            temp1[i]=getActivity().getString(ProductsContract.category[i]);
        }

        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(getContext());
            }
        });

        Animation in= AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        Animation out=AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);

        switcher.setInAnimation(in);
        switcher.setOutAnimation(out);

        recyclerView1.setAdapter(new MyCardAdapter(getContext(), temp,temp1,0,99));
        recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerView2.setAdapter(new MyCardAdapter(getContext(),ProductsContract.drawablesProducts[12],ProductsContract.namesProducts[12],5,12));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        startTimer();
        return view;
    }

    public void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                if(getActivity()==null)
                    return;
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        switcher.setImageResource(images[position]);
                        position++;
                        if (position == 4)
                        {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, 5000);

    }

    public void stopTimer(){
        timer.cancel();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        startTimer();
    }
}
