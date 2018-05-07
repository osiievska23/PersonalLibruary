package com.example.valentina.valentina_libruary.Fragments.navigationBarFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.valentina.valentina_libruary.Drawer.Drawer;
import com.example.valentina.valentina_libruary.Fragments.MyBookFragment;
import com.example.valentina.valentina_libruary.Manage.Add;
import com.example.valentina.valentina_libruary.Manage.Tabbar;
import com.example.valentina.valentina_libruary.Object.Book;
import com.example.valentina.valentina_libruary.R;
import com.example.valentina.valentina_libruary.RealmModel.RealmModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    @BindView(R.id.bookList)
    SwipeMenuListView bookList;

    private RealmResults<Book> books;
    private SearchFragment.AppAdapter mAdapter;

    public String bookName;
    public String bookAuthor;
    public String bookCategory;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.search, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userBookNameInput = (EditText) promptsView.findViewById(R.id.input_book_name);
        final EditText userBookAuthorInput = (EditText) promptsView.findViewById(R.id.input_author);
        final EditText userBookCategoryInput = (EditText) promptsView.findViewById(R.id.input_category);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Search",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                bookName = userBookNameInput.getText().toString();
                                bookAuthor = userBookAuthorInput.getText().toString();
                                bookCategory = userBookCategoryInput.getText().toString();
                                CreateList(view);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

        return view;
    }
    class AppAdapter extends BaseAdapter {

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
                convertView = View.inflate(getActivity(),
                        R.layout.item_list_app, null);
                new SearchFragment.AppAdapter.ViewHolder(convertView);
            }
            SearchFragment.AppAdapter.ViewHolder holder = (SearchFragment.AppAdapter.ViewHolder) convertView.getTag();
            final Book item = getItem(position);

            if (!Objects.equals(item.getImage(), "")) {
                Picasso.with(getActivity())
                        .load(new File(item.getImage()))
                        .resize(300, 300)
                        .into(holder.iv_icon);
            } else {
                Picasso.with(getActivity())
                        .load("@drawable/book")
                        .resize(300, 300)
                        .into(holder.iv_icon);
            }

            //holder.iv_icon.setImageDrawable(item.getImage());
            holder.tv_name.setText(item.getName() + "\n" + item.getAuthor());
            holder.iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBookFragment myBookFragment = new MyBookFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", item.getName());
                    bundle.putString("author", item.getAuthor());
                    bundle.putString("category", item.getCategory());
                    bundle.putString("link", item.getLink());
                    bundle.putString("description", item.getDescription());
                    bundle.putString("image", item.getImage());
                    myBookFragment.setArguments(bundle);

                    android.support.v4.app.FragmentManager ft = getFragmentManager();
                    ft.beginTransaction().replace(R.id.container, myBookFragment).commit();
                }
            });
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBookFragment myBookFragment = new MyBookFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", item.getName());
                    bundle.putString("author", item.getAuthor());
                    bundle.putString("category", item.getCategory());
                    bundle.putString("link", item.getLink());
                    bundle.putString("description", item.getDescription());
                    bundle.putString("image", item.getImage());
                    myBookFragment.setArguments(bundle);

                    android.support.v4.app.FragmentManager ft = getFragmentManager();
                    ft.beginTransaction().replace(R.id.container, myBookFragment).commit();
                }
            });
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

    public void CreateList(View view){

        ButterKnife.bind(this, view);

        RealmModel realm = new RealmModel(getActivity());
        books = realm.findRealmModel(bookName, bookAuthor, bookCategory);
        if (books.size() == 0){
            Toast.makeText(getActivity(), "Book not found", Toast.LENGTH_SHORT).show();
        }
        mAdapter = new AppAdapter();
        bookList.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity());
                item1.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item1.setWidth(dp2px(60));
                item1.setIcon(R.drawable.ic_update_black_24dp);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity());
                item2.setBackground(new ColorDrawable(Color.rgb(0x70, 0x42, 0x14)));
                item2.setWidth(dp2px(60));
                item2.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item2);
                // 0x7b, 0x3f, 0x00
                // 0x70, 0x42, 0x14
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
                        Intent intent = new Intent(getActivity(), Add.class);
                        if (!Objects.equals(item.getName(), "")){
                            intent.putExtra("name" , item.getName());
                        } else intent.putExtra("name" , "");
                        if (!Objects.equals(item.getAuthor(), "")){
                            intent.putExtra("author" , item.getAuthor());
                        } else intent.putExtra("author" , "");
                        if (!Objects.equals(item.getCategory(), "")){
                            intent.putExtra("category" , item.getCategory());
                        } else intent.putExtra("category" , "");
                        if (!Objects.equals(item.getLink(), "")){
                            intent.putExtra("link" , item.getLink());
                        } else intent.putExtra("description" , "");
                        if (!Objects.equals(item.getDescription(), "")){
                            intent.putExtra("description" , item.getDescription());
                        } else intent.putExtra("description" , "");
                        if (!Objects.equals(item.getImage(), "")){
                            intent.putExtra("image" , item.getImage());
                        } else intent.putExtra("image" , "");
                        intent.putExtra("flag" , true);
                        startActivity(intent);
                        break;
                    case 1:
                        // delete
                        RealmModel realm1 = new RealmModel(getActivity());
                        realm1.deleteRealmObject(item);
                        Intent intent1 = new Intent(getActivity(), Drawer.class);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
