package com.example.examenmichelle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private Context Ctx;
    private List<Journal> journals;
    private LayoutInflater inflater;

    public Adapter(Context Ctx, List<Journal> journals){
        this.journals = journals;
        this.inflater=LayoutInflater.from(Ctx);
        this.Ctx=Ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_journal, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.lblname.setText(journals.get(position).getName());
        holder.lbljournal_id.setText(journals.get(position).getJournal_id());
        holder.lbldescription.setText(journals.get(position).getDescription());

        Glide.with(Ctx)
                .load(journals.get(position).getPortada())
                .into(holder.portada);

    }
    @Override
    public int getItemCount() {
        return journals.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView lblname,lbljournal_id,lbldescription;
        private ImageView portada;

        ViewHolder(View itemView) {
            super(itemView);
            lblname = itemView.findViewById(R.id.lblname);
            lbljournal_id = itemView.findViewById(R.id.lbljournal_id);
            lbldescription = itemView.findViewById(R.id.lbldescription);
            portada = itemView.findViewById(R.id.portada);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Ctx, Issues.class);
                    i.putExtra("journal_id",lbljournal_id.getText().toString());
                    Ctx.startActivity(i);
                }
            });
        }
    }
}
