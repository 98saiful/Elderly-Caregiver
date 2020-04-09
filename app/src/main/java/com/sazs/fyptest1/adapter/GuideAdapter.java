package com.sazs.fyptest1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sazs.fyptest1.Guide;
import com.sazs.fyptest1.GuideDetailActivity;
import com.sazs.fyptest1.R;

import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {

    private Context mCtx;
    private List<Guide> guideList;

    public GuideAdapter(Context mCtx, List<Guide> guideList){
        this.mCtx = mCtx;
        this.guideList = guideList;
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_guide, null);
        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GuideViewHolder holder, final int position) {
        Guide guide = guideList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(guide.getPicture())
                .fitCenter()
                .into(holder.imageView);

        holder.textViewTitle.setText(guide.getTitle());
        holder.textViewSubtitle.setText(guide.getSubtitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), GuideDetailActivity.class);
                Guide guide =  guideList.get(position);

                intent.putExtra("Title", guide.getTitle());
                intent.putExtra("Description", guide.getDescription());
                intent.putExtra("Picture", guide.getPicture());

                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }

    class GuideViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewSubtitle;
        ImageView imageView;

        public GuideViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.tv_card_1_title);
            textViewSubtitle = itemView.findViewById(R.id.tv_card_1_subtitle);
            imageView = itemView.findViewById(R.id.img_card_1);
        }
    }


}