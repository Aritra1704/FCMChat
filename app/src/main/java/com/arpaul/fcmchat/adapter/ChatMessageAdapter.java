package com.arpaul.fcmchat.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arpaul.fcmchat.R;
import com.arpaul.fcmchat.dataObjects.MessageDO;

import java.util.ArrayList;

/**
 * Created by Aritra on 21-10-2016.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MessageDO> arrMessage = new ArrayList<>();

    public ChatMessageAdapter(Context context, ArrayList<MessageDO> arrFarms) {
        this.context = context;
        this.arrMessage = arrFarms;
    }

    public void refresh(ArrayList<MessageDO> arrFarms) {
        this.arrMessage = arrFarms;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_message, parent, false);//farmlist_adapter_cell
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MessageDO objFarmDO = arrMessage.get(position);

        holder.tvSender.setText(objFarmDO.messageSender);
        holder.tvMessage.setText(objFarmDO.messageBody);
    }

    @Override
    public int getItemCount() {
        if(arrMessage != null)
            return arrMessage.size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CardView cardView;

        public final TextView tvSender;
        public final TextView tvMessage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cardView                = (CardView) view.findViewById(R.id.cardView);

            tvSender                = (TextView) view.findViewById(R.id.tvSender);
            tvMessage               = (TextView) view.findViewById(R.id.tvMessage);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
