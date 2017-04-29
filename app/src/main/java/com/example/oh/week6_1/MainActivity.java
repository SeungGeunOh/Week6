package com.example.oh.week6_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int _REQUEST_CODE_NUM = 0;
    ArrayList<FoodData> arrayListData = new ArrayList<>();
    ListAdapter adapter;
    ListView listView;
    EditText search;
    Intent intent;
    int num = 0;
    public static boolean select = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");

        listView = (ListView) findViewById(R.id.lv);
        search = (EditText) findViewById(R.id.search);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setChecked(position);
                Toast.makeText(getApplicationContext(), "position " + Integer.toString(position), Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<FoodData> sResult = new ArrayList<>();
                String search = s.toString();

                for (FoodData temp : arrayListData) {
                    if (temp.getName().contains(search))
                        sResult.add(temp);
                }
                adapter.setfoodDatas(sResult);

                adapter.notifyDataSetChanged();
            }
        });

    }


    public void onClick(View v){
        switch (v.getId()) {
            case R.id.add:
                intent = new Intent(this, Main2Activity.class);
                startActivityForResult(intent, _REQUEST_CODE_NUM);
                break;
            case R.id.namesort:
                num = 1;
                adapter.setSortNameOrType(num);
                num = 0;
                break;
            case R.id.typesort:
                num = 2;
                adapter.setSortNameOrType(num);
                num = 0;
                break;
            case R.id.select:
                Button sel = (Button)findViewById(R.id.select);
                if (select == false) {
                    select = true;
                    sel.setText("삭제");
                }
                else{
                    select = false;
                    sel.setText("선택");
                    SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                    int index  = adapter.getCount();

                    for (int i = index - 1; i >= 0; i--){
                        if (checkedItems.get(i))
                            arrayListData.remove(i);
                    }
                    listView.clearChoices();
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _REQUEST_CODE_NUM && resultCode == RESULT_OK) {
            FoodData foodData = data.getParcelableExtra("foodData");
            arrayListData.add(foodData);
            adapter = new ListAdapter(this, arrayListData);
            listView.setAdapter(adapter);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

