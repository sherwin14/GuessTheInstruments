package com.webteq.guesstheinstruments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.webteq.guesstheinstruments.Models.InstrumentsModel;
import com.webteq.guesstheinstruments.R;
import com.webteq.guesstheinstruments.interfaces.OnClickListener;

import java.util.List;

/**
 * Created by Sherwin on 1/19/2017.
 */

public class InstrumentsAdapter extends RecyclerView.Adapter<InstrumentsAdapter.InstrumentsViewHolder> {

    private List<InstrumentsModel> instruments;
    private Context context;
    private OnClickListener onClickListener;
    public InstrumentsAdapter(List<InstrumentsModel> instruments, Context context, OnClickListener onClickListener) {
        this.instruments = instruments;
        this.context = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public int getItemCount() {
        return instruments.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(InstrumentsViewHolder holder, int position) {
        final InstrumentsModel im = instruments.get(position);

        holder.mName.setText(im.getIntrumentName());
        Glide.with(this.context)
                .load(im.getInstrumentDrawable())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mThumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnItemClick(v,im);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public InstrumentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instrument_layout,parent,false);
        return new InstrumentsViewHolder(itemView);
    }

    public class InstrumentsViewHolder extends RecyclerView.ViewHolder {

        protected ImageView mThumb;
        protected TextView mName;

        public InstrumentsViewHolder(View itemView) {
            super(itemView);

            mThumb = (ImageView) itemView.findViewById(R.id.instrumentThumb);
            mName =  (TextView)  itemView.findViewById(R.id.instrumentName);
        }
    }
}
