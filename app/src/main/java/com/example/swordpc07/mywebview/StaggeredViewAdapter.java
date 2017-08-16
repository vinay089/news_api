package com.example.swordpc07.mywebview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by swordpc07 on 1/17/2017.
 */

public class StaggeredViewAdapter extends RecyclerView.Adapter<StaggeredViewAdapter.StaggeredViewHolders> {

    Context context;
    StaggeredViewAdapter(Context context) {
        this.context = context;
        }

    @Override
    public int getItemCount() {
        return (null != SingeltonArrayListClass.allNewsArrayList ? SingeltonArrayListClass.allNewsArrayList.size() : 0);
    }


    @Override
    public StaggeredViewAdapter.StaggeredViewHolders onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staggered_single_item, null);
        StaggeredViewAdapter.StaggeredViewHolders viewHolder = new StaggeredViewAdapter.StaggeredViewHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StaggeredViewHolders holder, int position) {
        final LoadAllNewsGetterSetter feedItem = SingeltonArrayListClass.allNewsArrayList.get(position);


        holder.Name.setText(feedItem.getName());
        holder.Description.setText(feedItem.getDesription());
        holder.Category.setText(feedItem.getCategory());
        //Picasso.with(context).load(feedItem.getUrlLogo()).into(holder.image_Logo);

    }

    public class StaggeredViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView Name, Description, Category;
        protected ImageView image_Logo;

    public StaggeredViewHolders(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        this.Name= (TextView) itemView.findViewById(R.id.name_news_text);
        this.Category= (TextView) itemView.findViewById(R.id.categ_news_text);
        this.Description= (TextView) itemView.findViewById(R.id.descip_news_text);
        this.image_Logo= (ImageView) itemView.findViewById(R.id.url_logo_imageview);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(itemView.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        Log.d("Name="+ SingeltonArrayListClass.allNewsArrayList.get(getPosition()).getName(),"& Description ="+ SingeltonArrayListClass.allNewsArrayList.get(getPosition()).getDesription() );

        Intent in =new Intent(context, DownloadSourceNewsService.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", SingeltonArrayListClass.allNewsArrayList.get(getPosition()).getId());
        in.putExtras(bundle);
        context.startService(in);

    }

    }
}
