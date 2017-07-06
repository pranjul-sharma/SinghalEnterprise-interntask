package in.singhalenterprises.singhalenterprise;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class ContactFragment extends Fragment {

    private EditText editName,editSubject,editMessage;
    Button buttonSend;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact, container, false);
        editName =(EditText)view.findViewById(R.id.edit_name_form);
        editSubject=(EditText)view.findViewById(R.id.edit_subject_form);
        editMessage=(EditText)view.findViewById(R.id.edit_message_form);
        buttonSend=(Button)view.findViewById(R.id.button_send_form);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isNetworkEnabled()){
                    Snackbar.make(view, "No Internet Connection!!!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(editName.getText().toString().isEmpty()){
                    editName.setError("Field can\'t be empty.");
                }else{
                    String name=editName.getText().toString();
                    String subject=editSubject.getText().toString();
                    String message=editMessage.getText().toString();

                    Intent intent=new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:info@singhalenterprises.in"));
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject+" mail from "+name);
                    intent.putExtra(Intent.EXTRA_TEXT,message);

                    startActivity(intent);
                }
            }
        });
        return view;

    }





    private boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }



}
