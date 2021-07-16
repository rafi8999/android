package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sample extends AppCompatActivity {


    Button btsbumit;
    EditText etusername;
    EditText etnumber;
    RecyclerView recyclerView;
    ArrayList al=new ArrayList();
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);
        etusername=findViewById(R.id.etusername);
        etnumber=findViewById(R.id.etedittext);
        btsbumit=findViewById(R.id.btsubmit);
        recyclerView=findViewById(R.id.recyclerview);



        btsbumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usename=etusername.getText().toString();
                String password=etnumber.getText().toString();

                if(usename.isEmpty() || usename==null){
                    etusername.setError("Username cannot be empty");
                }else if(password.isEmpty() || password==null ){
                    etnumber.setError("Filed cannot be empty");
                }else  if(password.length()<10){
                    etnumber.setError("Number should be 10 digits");
                }
                else {
                    Log.e("usename", usename);
                    UserModel um = new UserModel();
                    um.setUsername(usename);
                    um.setNumber(password);
                    al.add(um);
                    Log.e("values", al.toString());
               /* Intent i=new Intent(getApplicationContext(),RecyclerclassExample.class);
                startActivity(i);*/
         /*  RecyclerAdapter recyclerAdapter=new RecyclerAdapter(getApplicationContext(),al);
           recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();*/
                    String s1[] = {"1", "2", "3",};
                    CustomAdapter customAdapter = new CustomAdapter(al);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(customAdapter);
                }
            }
        });
    }

    private class RecyclerAdapter extends RecyclerView.Adapter {

        public RecyclerAdapter(Context context, ArrayList al) {
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }





    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private ArrayList  localDataSet=new ArrayList();



        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public  class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;


            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.textView);
            }

            public TextView getTextView() {
                return textView;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public CustomAdapter(ArrayList dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            UserModel userModel= (UserModel) localDataSet.get(position);
            viewHolder.getTextView().setText(userModel.getUsername() +"\t"+ userModel.getNumber());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

}
