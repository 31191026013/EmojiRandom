package com.example.emojirandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView mainGrid;
    int wrongClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startEmoji();
    }

    protected void startEmoji() {
        mainGrid = findViewById(R.id.mainGrid);
        List<Integer> emoji = Arrays.asList(
                0x1F601, 0x1F602, 0x1F603, 0x1F604, 0x1F605);
        List data = new ArrayList();
        for (int i = 0; i < emoji.size(); i++) {
            data.add(new String(Character.toChars(emoji.get(i))));
        }
        Collections.shuffle(data);
        EmojiAdapter adapter = new EmojiAdapter(getApplicationContext(), R.layout.grid_item, data);
        mainGrid.setAdapter(adapter);

        List temp = new ArrayList(data);
        final String[] target = {getAndShowRandomEmoji(temp)};
        mainGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView v = (TextView) view;
                String selected = v.getText().toString();
                if(selected.equals(target[0])){
                    temp.remove(target[0]);
                    v.setText("");
                    if(temp.size() == 0){
                        startActivity(new Intent(getApplicationContext(), WinnerActivity.class));
                    }
                }
                else{
                    wrongClick++;
                    Toast.makeText(getApplicationContext(),
                            "Bạn còn " + (3-wrongClick) + " lượt ", Toast.LENGTH_SHORT).show();
                    if(wrongClick == 3){

                        startActivity(new Intent(getApplicationContext(), LoserActivity.class));
                    }
                }
                target[0] = getAndShowRandomEmoji(temp);
            }
        });
    }

    protected String getAndShowRandomEmoji(List<String> list) {
        Random rand = new Random();
        String result = list.get(rand.nextInt(list.size()));
        TextView target = findViewById(R.id.target);
        target.setText(result);
        return result;
    }
}

//,
//        0x1F606, 0x1F609, 0x1F60A, 0x1F60B, 0x1F60C,
//        0x1F60D, 0x1F60F, 0x1F612, 0x1F613, 0x1F614,
//        0x1F616, 0x1F618, 0x1F61A, 0x1F61C, 0x1F61D