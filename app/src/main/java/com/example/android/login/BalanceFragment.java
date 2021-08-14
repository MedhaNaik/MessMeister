package com.example.android.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by medha on 18/11/15.
 */
public class BalanceFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    int position;
    RecyclerView recyclerView;
    BalanceAdapter balanceAdapter;

    public BalanceFragment() {

    }

    public static BalanceFragment newInstance(int position) {
        BalanceFragment memberFragment = new BalanceFragment();
        memberFragment.position = position;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        memberFragment.setArguments(args);
        return memberFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        balanceAdapter = new BalanceAdapter(getContext());
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        balanceAdapter.setPosition(position);
        view = inflater.inflate(R.layout.member_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(balanceAdapter);
        return view;

    }
}
