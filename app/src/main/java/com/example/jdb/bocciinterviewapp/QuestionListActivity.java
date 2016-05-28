package com.example.jdb.bocciinterviewapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<String> questionArray;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        setQuestionArray();
        ListView questionList=(ListView)findViewById(R.id.questionList);
        adapter=new ListViewAdapter(this,R.layout.question_item,questionArray);
        questionList.setAdapter(adapter);
        questionList.setOnItemClickListener(this);
    }

    private class ViewHolder{
        Button delButton;
        Button editButton;
        TextView questionTextView;
        View view;
    }

    private class ListViewAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private int itemLayoutId;

        ListViewAdapter(Context context, int itemLayoutId, List<String> questionArray){
            super();
            this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayoutId=itemLayoutId;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            ViewHolder holder;
            //最初だけViewをinflateし、以降は再利用
            if(convertView==null){
                convertView=inflater.inflate(itemLayoutId,parent,false); //activity_mainの<ListView>にlist_itemsをinflateしてconvertVとする
                //ViewHolderを生成
                holder=new ViewHolder();
                holder.delButton=(Button) convertView.findViewById(R.id.delButton);
                holder.editButton=(Button) convertView.findViewById(R.id.editButton);
                holder.questionTextView=(TextView) convertView.findViewById(R.id.questionText);
                holder.view=(View)convertView.findViewById(R.id.questionItem);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag(); //holderを使って再利用
            }
            holder.questionTextView.setText(questionArray.get(pos));

            return convertView;
        }

        @Override
        public int getCount(){
            return questionArray.size();
        }

        @Override
        public Objects getItem(int pos){
            return null;
        }

        @Override
        public long getItemId(int pos){
            return 0;
        }
    }

    private void setQuestionArray(){
        //TODO データベースから質問引っ張ってきて
        questionArray.add("まず、自己紹介をしてください。");
        questionArray.add("では、あなたが当社を志望した理由を教えて下さい。s");
        questionArray.add("では、あなたの長所を教えて下さい。");
    }

    @Override
    public void onItemClick(AdapterView<?> parent,View v,int pos,long id){
        //TODO 再生
    }
}
