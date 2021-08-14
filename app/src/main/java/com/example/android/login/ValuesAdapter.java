package com.example.android.login;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by medha on 18/11/15.
 */
public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.viewHolder> {

    public Context context1;
    TextView tag;
    TextView amount;
    List<pair> list;
    String position;
    Boolean aBoolean = true;

    LayoutInflater inflater;

    public ValuesAdapter(Context context) {
        context1 = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = inflater.inflate(R.layout.balence_line, parent, false);
        viewHolder vHolder = new viewHolder(view);
        Log.e("create", position + "");
        return vHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        amount.setText(Integer.toString(list.get(position).amount1));
        tag.setText(list.get(position).tag1);
        Log.e("bind", position + "");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        setList();
        Log.e("pos4", position + "");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }


    public void setPosition(String position) {
        this.position = position;
    }

    public void setBool(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public void setList() {
        if (aBoolean)
            this.list = new IncomeDatabase(context1).getTuples(position);
        else
            this.list = new IncomeDatabase(context1).getToday(position);
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public viewHolder(View itemView) {


            super(itemView);
            tag = (TextView) itemView.findViewById(R.id.tag);
            amount = (TextView) itemView.findViewById(R.id.amount);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
