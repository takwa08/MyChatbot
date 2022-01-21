package com.example.mychatbot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatModel> chat;
    private Context context;

    public chatAdapter(ArrayList<ChatModel> chat, Context context) {
        this.chat = chat;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i)
        {
            case 0 :
                view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sender,viewGroup,false);
            return new UserViewHolder(view);
            case 1:
                view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_robot,viewGroup,false);
                return new RobotViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ChatModel chatModel=chat.get(i);
        switch (chatModel.getSender())
        {
            case "user":
               ((UserViewHolder)viewHolder).user_view.setText(chatModel.getMessage());
               break;
            case "robot":
                ((RobotViewHolder)viewHolder).rbt_view.setText(chatModel.getMessage());
                break;
        }

    }
    @Override
    public int getItemViewType(int position) {
        switch (chat.get(position).getSender()) {
            case "user":
                return 0;
            case "robot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return this.chat.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
TextView user_view;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        user_view=itemView.findViewById(R.id.idmsg);
        }
    }
    public static class RobotViewHolder extends RecyclerView.ViewHolder{
        TextView rbt_view;
        public RobotViewHolder(@NonNull View itemView) {
            super(itemView);
            rbt_view=itemView.findViewById(R.id.idmsgRbt);
        }
    }
}
