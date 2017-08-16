package com.example.swordpc07.mywebview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by swordpc07 on 1/18/2017.
 */

public class SingleSourceAdapter extends RecyclerView.Adapter<SingleSourceAdapter.SingleSourceHolder> {

    Context context;
    SingleSourceAdapter(Context context){
        this.context= context;
    }

    @Override
    public int getItemCount() {
        return (null != SingeltonArrayListClass.sourceNewsArrayList ? SingeltonArrayListClass.sourceNewsArrayList.size() : 0);
    }

    @Override
    public SingleSourceHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_source, null);
        SingleSourceAdapter.SingleSourceHolder viewHolder = new SingleSourceAdapter.SingleSourceHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SingleSourceHolder holder, int position) {

        final LoadSourceNewsGetterSetter feedItem = SingeltonArrayListClass.sourceNewsArrayList.get(position);


        holder.title.setText("title: "+ feedItem.getTitle());
        holder.author.setText("Author:" +feedItem.getAuthor());
        holder.decription.setText(feedItem.getDescription());
        holder.published.setText("Published at: "+feedItem.getPublished_At());
        Picasso.with(context).load(feedItem.getUrl_Image()).into(holder.url_logo_imageView);


    }



    public class SingleSourceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView title, author, decription, published;
        protected ImageView url_logo_imageView;

        public SingleSourceHolder(View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);
            this.title= (TextView) itemView.findViewById(R.id.title);
            this.author= (TextView) itemView.findViewById(R.id.author);
            this.decription= (TextView) itemView.findViewById(R.id.descrip);
            this.published= (TextView) itemView.findViewById(R.id.published);
            this.url_logo_imageView= (ImageView) itemView.findViewById(R.id.url_logo);

        }

        @Override
        public void onClick(View v) {
            String url= SingeltonArrayListClass.sourceNewsArrayList.get(getPosition()).getUrl();
            Intent i= new Intent(context, ForthWebViewActivity.class);
            i.putExtra("urlweb", url);
            context.startActivity(i);
        }
    }

}
