package com.example.oh.week6_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int _REQUEST_CODE_NUM = 0;
    ArrayList<FoodData> arrayListData = new ArrayList<>();
    ArrayList<String> arrayListString = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    ListView listView;
    Button add;
    TextView subject;
    Intent intent;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");
        add = (Button)findViewById(R.id.bt);
        subject = (TextView)findViewById(R.id.tv);
        listView = (ListView)findViewById(R.id.lv);
    }

    public void ListViewSetOnItemClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                final int num = position;
                dlg.setTitle("정보확인")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("이동하시겠습니까 ?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intent = new Intent(MainActivity.this, Main3Activity.class);
                                intent.putExtra("name", arrayListData.get(num).getName());
                                intent.putExtra("type", arrayListData.get(num).getType());
                                intent.putExtra("tel", arrayListData.get(num).getTel());
                                intent.putExtra("menu1", arrayListData.get(num).getMenu1());
                                intent.putExtra("menu2", arrayListData.get(num).getMenu2());
                                intent.putExtra("menu3", arrayListData.get(num).getMenu3());
                                intent.putExtra("homepage", arrayListData.get(num).getHomepage());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("아니요", null)
                        .show();
            }
        });
    }

    public void onClick(View v){
        intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, _REQUEST_CODE_NUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _REQUEST_CODE_NUM && resultCode == RESULT_OK) {
            FoodData foodData = data.getParcelableExtra("foodData");
            arrayListData.add(foodData);
            setListView();
            count++;
            subject.setText("맛집 리스트 (" + count + "개)");
            ListViewSetOnItemClickListener();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setListView() {
        arrayListString.add(arrayListData.get(count).getName());

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListString);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                final int num = position;
                dlg.setTitle("정말로")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("삭제 하시겠습니까 ?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayListString.remove(num);
                                arrayListData.remove(num);
                                arrayAdapter.notifyDataSetChanged();
                                count--;
                                subject.setText("맛집 리스트 (" + count + "개)");
                                Snackbar.make(view, "삭제 되었습니다.", Snackbar.LENGTH_SHORT)
                                        .setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        })
                                        .show();
                            }
                        })
                        .setNegativeButton("아니요", null)
                        .show();
                return false;
            }
        });



    }
}
