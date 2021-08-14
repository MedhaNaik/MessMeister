package com.example.android.login;

/**
 * Created by medha on 14/11/15.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by medha on 26/10/15.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.viewHolder> {

    public static String grpname = null, memberName = null;
    public Context context1;
    public int position;
    List<String> list = new ArrayList<>();
    String group_name;
    LayoutInflater inflater;

    public RecycleAdapter(Context context) {
        context1 = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = inflater.inflate(R.layout.listitem, parent, false);
        viewHolder vHolder = new viewHolder(view);
        return vHolder;
    }

    public void setgrpname(String name) {
        group_name = name;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {


        holder.textView.setText(list.get(position));
        holder.imageButton.isShown();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        setList();
        Log.e("pos", position + "");
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setList() {
        if (position == 1) {
            this.list = new MemberDatabase(context1).getlateMemberslist();

        } else if (position == 2) {

            this.list = new MemberDatabase(context1).getAllMembers();
        } else if (position == 3) {

            this.list = new GroupDatabase(context1).getGroupNames();
        } else if (position == 5) {
            this.list = new MemberDatabase(context1).getNamesbyidlist(new MessMemberGroupDatabase(context1).getmidlist(new GroupDatabase(context1).getgrpId(grpname)));

        }


    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textView;
        ImageButton imageButton;


        public viewHolder(View itemView) {


            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.member_name);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name;
                    if (position == 3) {

                        RecycleAdapter.grpname = textView.getText().toString();
                        Log.e("grpname", RecycleAdapter.grpname);
                        Intent intent = new Intent(context1, Groups.class);
                        intent.putExtra("grpname", RecycleAdapter.grpname);
                        context1.startActivity(intent);


                    } else {
                        name = textView.getText().toString();
                        RecycleAdapter.memberName = name;
                        Log.e("membername", RecycleAdapter.memberName);
                        Intent intent = new Intent(context1, MemberDescription.class);
                        intent.putExtra("name", name);
                        context1.startActivity(intent);

                    }
                }
            });
            imageButton = (ImageButton) itemView.findViewById(R.id.menu_button);
            imageButton.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            createPopup(v);
        }

        public void createPopup(View view) {
            int values[] = {0, R.menu.late_members_menu, R.menu.all_member_menu, R.menu.group_menu, 0, R.menu.group_member_menu};
            PopupMenu popupMenu = new PopupMenu(context1, imageButton);
            popupMenu.inflate(values[position]);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.all_call || id == R.id.late_call) {
                        int mid = new MemberDatabase(context1).getMemberIdbyName(RecycleAdapter.memberName);
                        String mphone = new MemberDatabase(context1).getPhone(mid);
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + mphone));
                        callIntent.setPackage("com.android.server.telecom");
                        context1.startActivity(callIntent);
                    } else if (id == R.id.all_extend) {
                        ExtendPeriod extendPeriod = new ExtendPeriod(context1, 1);
                        extendPeriod.show();
                    } else if (id == R.id.all_add || id == R.id.late_add) {
                        MyDialog addFee = new MyDialog(context1, 0, null);
                        addFee.show();
                    } else if (id == R.id.late_paid) {
                        int mid = new MemberDatabase(context1).getMemberIdbyName(RecycleAdapter.memberName);
                        new MemberDatabase(context1).update_haspaid(mid);
                    } else if (id == R.id.group_extend) {
                        ExtendPeriod extendPeriod = new ExtendPeriod(context1, 0);
                        extendPeriod.show();
                    } else if (id == R.id.activity) {
                        int grpid = new GroupDatabase(context1).getgrpId(RecycleAdapter.grpname);
                        ArrayList<Integer> midlist = new MessMemberGroupDatabase(context1).getmidlist(grpid);
                        new MemberDatabase(context1).setgrpInactive(midlist);

                    } else if (id == R.id.group_remove) {
                        //
                        String meberName = textView.getText().toString();
                        Log.e("mname", meberName);
                        int mid = new MemberDatabase(context1).getMemberIdbyName(meberName);

                        int grpid = new GroupDatabase(context1).getgrpId(group_name);
                        new MessMemberGroupDatabase(context1).delete(mid, grpid);
                        list.remove(list.indexOf(meberName));
                        notifyDataSetChanged();



                       /* RecycleAdapter.position = 5;
                        setList();/*/

                    }
                    return false;
                }
            });

        }
    }


}
