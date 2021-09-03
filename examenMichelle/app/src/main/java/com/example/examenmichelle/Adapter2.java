package com.example.examenmichelle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder>  {
   private Context Ctx;
   private List<Journal2> journals2;
   private LayoutInflater inflater;
    public Adapter2(Context Ctx, List<Journal2> journals2){
        this.journals2 = journals2;
        this.inflater=LayoutInflater.from(Ctx);
        this.Ctx=Ctx;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_pubs, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.lblsection.setText(journals2.get(position).getSection());
        holder.lblpublication_id.setText(journals2.get(position).getPublication_id());
        holder.lbltitle.setText(journals2.get(position).getTitle());
        holder.lbldoi.setText(journals2.get(position).getDoi());
        holder.lbldate_published.setText(journals2.get(position).getDate_published());

    }
    @Override
    public int getItemCount() {
        return journals2.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView lblsection,lblpublication_id,lbltitle,lbldoi,lbldate_published;

        ViewHolder(View itemView) {
            super(itemView);

            lblsection = itemView.findViewById(R.id.lblsection);
            lblpublication_id = itemView.findViewById(R.id.lblpublication_id);
            lbltitle = itemView.findViewById(R.id.lbltitle);
            lbldoi = itemView.findViewById(R.id.lbldoi);
            lbldate_published = itemView.findViewById(R.id.lbldate_published);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url=lbldoi.getText().toString();
                    Uri uri =Uri.parse(url);
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    Ctx.startActivity(intent);
                }
            });
        }
    }
}