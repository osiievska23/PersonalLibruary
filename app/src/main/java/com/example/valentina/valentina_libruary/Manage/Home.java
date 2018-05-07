package com.example.valentina.valentina_libruary.Manage;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.valentina.valentina_libruary.Fragments.AddFragment;
import com.example.valentina.valentina_libruary.Fragments.TabBarFragments.BookDescription;
import com.example.valentina.valentina_libruary.Fragments.TabBarFragments.BookInfo;
import com.example.valentina.valentina_libruary.Object.Book;
import com.example.valentina.valentina_libruary.R;
import com.example.valentina.valentina_libruary.RealmModel.RealmModel;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class Home extends AppCompatActivity {

    @BindView(R.id.bookList)
    SwipeMenuListView bookList;

    private RealmResults<Book> books;
    //private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);

        RealmModel realm = new RealmModel(this);
        books = realm.getRealmModel();
        //ArrayList<Book> books = new ArrayList<>(RealmResults);
        // mAppList = getPackageManager().getInstalledApplications(0);
        mAdapter = new AppAdapter();
        bookList.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;
                    case 1:
                        createMenu2(menu);
                        break;
                    case 2:
                        createMenu3(menu);
                        break;
                }
            }

            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item1.setWidth(dp2px(60));
                item1.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0x7b, 0x3f, 0x00)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item2);
            }

            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x7b, 0x3f, 0x00)));
                item1.setWidth(dp2px(60));
                item1.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item2);
                // 0x7b, 0x3f, 0x00
                // 0x70, 0x42, 0x14
            }

            private void createMenu3(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item1.setWidth(dp2px(60));
                item1.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0x7b, 0x3f, 0x00)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item2);
            }
        };
        // set creator
        bookList.setMenuCreator(creator);

        // step 2. listener item click event
        bookList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Book item = books.get(position);
                switch (index) {
                    case 0:
                        // update
                        Intent intent = new Intent(Home.this, Add.class);
                        if (item.getName() != ""){
                            intent.putExtra("name" , item.getName());
                        } else intent.putExtra("name" , "");
                        if (item.getAuthor() != ""){
                            intent.putExtra("author" , item.getAuthor());
                        } else intent.putExtra("author" , "");
                        if (item.getCategory() != ""){
                            intent.putExtra("category" , item.getCategory());
                        } else intent.putExtra("category" , "");
                        if (item.getDescription() != ""){
                            intent.putExtra("description" , item.getDescription());
                        } else intent.putExtra("description" , "");
                        if (item.getImage() != ""){
                            intent.putExtra("image" , item.getImage());
                        } else intent.putExtra("image" , "");
                        intent.putExtra("flag" , true);
                        startActivity(intent);
                        break;
                    case 1:
                        // delete
                        RealmModel realm1 = new RealmModel(getApplicationContext());
                        realm1.deleteRealmObject(item);
                        Intent intent1 = new Intent(Home.this, Home.class);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });

    }

    class AppAdapter extends BaseAdapter implements View.OnClickListener {

        Book item;

        @Override
        public int getCount() {
            return books.size();
        }

        @Override
        public Book getItem(int position) {
            return books.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            // menu type count
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position % 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            item = getItem(position);

            if (!Objects.equals(item.getImage(), "")){
                Picasso.with(getApplicationContext())
                        .load(new File(item.getImage()))
                        .resize(300, 300)
                        .into(holder.iv_icon);
            } else{
                Picasso.with(getApplicationContext())
                        .load("@drawable/book")
                        .resize(300, 300)
                        .into(holder.iv_icon);
            }

            //holder.iv_icon.setImageDrawable(item.getImage());
            holder.tv_name.setText(item.getName()+"\n"+item.getAuthor());
            holder.iv_icon.setOnClickListener(this);
            holder.tv_name.setOnClickListener(this);
            return convertView;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Home.this, Tabbar.class);
            if (item.getName() != ""){
                intent.putExtra("name" , item.getName());
            } else intent.putExtra("name" , "");
            if (item.getAuthor() != ""){
                intent.putExtra("author" , item.getAuthor());
            } else intent.putExtra("author" , "");
            if (item.getCategory() != ""){
                intent.putExtra("category" , item.getCategory());
            } else intent.putExtra("category" , "");
            if (item.getDescription() != ""){
                intent.putExtra("description" , item.getDescription());
            } else intent.putExtra("description" , "");
            if (item.getImage() != ""){
                intent.putExtra("image" , item.getImage());
            } else intent.putExtra("image" , "");
            startActivity(intent);
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
