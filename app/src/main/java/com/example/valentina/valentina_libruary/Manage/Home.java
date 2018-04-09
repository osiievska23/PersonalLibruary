package com.example.valentina.valentina_libruary.Manage;


import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.valentina.valentina_libruary.Object.Book;
import com.example.valentina.valentina_libruary.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmModel;
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
                item1.setIcon(R.drawable.ic_info_outline_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item2);
                SwipeMenuItem item3 = new SwipeMenuItem(
                        getApplicationContext());
                item3.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item3.setWidth(dp2px(60));
                item3.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item3);
            }

            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item1.setWidth(dp2px(60));
                item1.setIcon(R.drawable.ic_info_outline_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item2);
                SwipeMenuItem item3 = new SwipeMenuItem(
                        getApplicationContext());
                item3.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));

                item3.setWidth(dp2px(60));
                item3.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item3);
                // 0x7b, 0x3f, 0x00
                // 0x7b, 0x3f, 0x00
                // 0x70, 0x42, 0x14
            }

            private void createMenu3(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item1.setWidth(dp2px(60));
                item1.setIcon(R.drawable.ic_info_outline_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item2);
                SwipeMenuItem item3 = new SwipeMenuItem(
                        getApplicationContext());
                item3.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item3.setWidth(dp2px(60));
                item3.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item3);
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
                        // open
                        break;
                    case 1:
                        // delete
                    case 2:
//					delete(item);
                        books.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

    }

    class AppAdapter extends BaseAdapter {

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
            Book item = getItem(position);

            if (item.getImage() != ""){
                Picasso.with(getApplicationContext())
                        .load(new File(item.getImage()))
                        .fit()
                        .into(holder.iv_icon);
            } else{
                Picasso.with(getApplicationContext())
                        .load("@drawable/book")
                        .fit()
                        .into(holder.iv_icon);
            }

            //holder.iv_icon.setImageDrawable(item.getImage());
            holder.tv_name.setText(item.getName());
            return convertView;
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

    protected String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver()
                .query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
