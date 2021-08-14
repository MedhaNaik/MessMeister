package com.example.android.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

/**
 * Created by medha on 14/11/15.
 */
public class MemberFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    int position;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;

    public MemberFragment() {

    }

    public static MemberFragment newInstance(int position) {
        MemberFragment memberFragment = new MemberFragment();
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
        recycleAdapter = new RecycleAdapter(getContext());
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        recycleAdapter.setPosition(position);
        if (position == 3) {
            view = inflater.inflate(R.layout.group_fragment, container, false);
            setViewSwitcher(view);
        } else {
            view = inflater.inflate(R.layout.member_list, container, false);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleAdapter);
        return view;

    }

    public void setViewSwitcher(View view) {
        final ViewSwitcher viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher1);
        ImageButton imageButton1 = (ImageButton) view.findViewById(R.id.add_button);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
            }
        });
        ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.close);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                viewSwitcher.showPrevious();
            }
        });
        ImageButton imageButton3 = (ImageButton) view.findViewById(R.id.done);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberGroup group;
                GroupDatabase groupDatabase = new GroupDatabase(getContext());
                String group_name = editText.getText().toString();
                if (!group_name.equals("")) {
                    group = new MemberGroup(group_name);
                    groupDatabase.add(group);
                }
                editText.setText("");
                viewSwitcher.showPrevious();
                recycleAdapter = new RecycleAdapter(getContext());
                recycleAdapter.setPosition(position);
                recyclerView.setAdapter(recycleAdapter);
            }
        });
    }


}
