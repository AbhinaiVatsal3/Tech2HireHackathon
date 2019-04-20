package com.example.techolutionatm;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class ATMListAdapter extends RecyclerView.Adapter<ATMListAdapter.ATMViewHolder> {

    private LayoutInflater inflater;

    private List<ATMEntity> atmEntities;
    private Context context;

    ATMListAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ATMViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = inflater.inflate(R.layout.atm_view_model, viewGroup, false);
        return new ATMViewHolder(view);
    }

    void setAtmEntities(List<ATMEntity> atmEntities){
        this.atmEntities = atmEntities;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ATMViewHolder atmViewHolder, final int i) {
        atmViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("check", "id :"+i);
                if(AdminMainPage.admin){
                    Intent intent = new Intent(context, ATMEditPage.class);
                    intent.putExtra("ID", i);
                    context.startActivity(intent);
                }
            }
        });
        atmViewHolder.name.setText(atmEntities.get(i).associated_Bank +", "+atmEntities.get(i).location);
    }

    @Override
    public int getItemCount() {
        return atmEntities != null ? atmEntities.size() : 0;
    }

    public class ATMViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ATMViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.atmName);
        }
    }
}
