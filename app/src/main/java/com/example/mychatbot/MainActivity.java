package com.example.mychatbot;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView chatsRV;
    private EditText usertsgEdt;
    private FloatingActionButton sendisgFAB;


    private final String BOT_KEY = "robot";
    private final String USER_KEY = "user";
    private ArrayList<ChatModel> chatModelArrayList;
    private chatAdapter chatRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatsRV = findViewById(R.id.chat);
        usertsgEdt = findViewById(R.id.msg);
        sendisgFAB = findViewById(R.id.floatingActionButton);
        chatModelArrayList = new ArrayList<>();
        chatRVAdapter = new chatAdapter(chatModelArrayList,this);
         LinearLayoutManager manager = new LinearLayoutManager(this);
         chatsRV.setLayoutManager(manager);
         chatsRV.setAdapter(chatRVAdapter);
        sendisgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usertsgEdt.getText ().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;}
                    getResponse(usertsgEdt.getText().toString());
                    usertsgEdt.setText("");
                };
            });


    }
    private void getResponse(String message){
        chatModelArrayList.add(new ChatModel (message,USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=163005&key=okb3TgbQtJQjcLX7&uid=[uid]&msg="+message;
        String BASE_URL= "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<MessageModel>modelCall=retrofitAPI.getMsg(url);
        modelCall.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful()) {
                    MessageModel model = response.body();
                    chatModelArrayList.add(new ChatModel(model.getCnt(),"robot"));
                    chatRVAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                chatModelArrayList.add(new ChatModel("Plese revert your question", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
            }
        });

    }



}