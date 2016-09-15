package com.alluresoft.gettogether;

/**
 * Created by Vikas on 9/9/2016.
 */
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private String[] titles={"Vikas Dighe",
            "Sagar Nawale",
            "Person Name",
            "Person Name",
            "Person"
    };

    private String[] details={"comment here...",
            "Our soup & ramen made from traditional ingredients.",
            "We have various desserts choice for you to accompany enjoying the love.",
            "Test our fresh drinks delightfully.","Enjoy our best coffee made from selected coffee seeds."
    };


    private int[] images={R.drawable.album1,
            R.drawable.cover,
            R.drawable.album1,
            R.drawable.cover,
            R.drawable.cover
    };

    private String[] comnmented_time={"4","5","2","1","7"};


    class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView comnmented_time;


        public ViewHolder(View itemView){
            super(itemView);

            itemImage =(ImageView) itemView.findViewById(R.id.item_image);
            itemTitle= (TextView) itemView.findViewById(R.id.item_title);
            itemDetail=(TextView) itemView.findViewById(R.id.item_detail);
            comnmented_time=(TextView) itemView.findViewById(R.id.comnmented_time);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, "click detected in item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
        viewHolder.comnmented_time.setText(comnmented_time[i]+" hrs ago");

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
