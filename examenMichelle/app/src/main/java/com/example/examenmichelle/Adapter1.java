package com.example.examenmichelle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder>  {
    private View.OnClickListener listener;
   private Context Ctx;
   private List<Journal1> journals1;
   private LayoutInflater inflater;
    public Adapter1(Context Ctx, List<Journal1> journals1){
        this.journals1 = journals1;
        this.inflater=LayoutInflater.from(Ctx);
        this.Ctx=Ctx;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_issues, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_journalTitle.setText(journals1.get(position).getTitle());
        holder.tv_issue_id.setText(journals1.get(position).getIssue_id());
        holder.tv_doi.setText(journals1.get(position).getDoi());
        holder.tv_date_published.setText(journals1.get(position).getDate_published());

        Glide.with(Ctx)
                .load(journals1.get(position).getCover())
                .into(holder.cover);

    }
    @Override
    public int getItemCount() {
        return journals1.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView tv_journalTitle,tv_issue_id,tv_doi,tv_date_published;
        private ImageView cover;

        ViewHolder(View itemView) {
            super(itemView);

            tv_journalTitle = itemView.findViewById(R.id.tv_journalTitle);
            tv_issue_id = itemView.findViewById(R.id.tv_issue_id);
            tv_doi = itemView.findViewById(R.id.tv_doi);
            tv_date_published = itemView.findViewById(R.id.tv_date_published);
            cover = itemView.findViewById(R.id.coverImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Ctx, Pubs.class);
                    i.putExtra("issue_id",tv_issue_id.getText().toString());
                    Ctx.startActivity(i);
                }
            });
        }
    }
}