package com.example.valentina.valentina_libruary;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class Info_category extends ListActivity {

    // creating the list of categories
    final String[] categoryList = new String[] { "Crime", "Western", "Science fiction",
            "Popular science", "Reference book", "Textbook", "Cookbook", "Encyclopedia",
            "Mystery", "Drama", "Post-apocalyptic", "Folklore", "Distopia", "Fantasy",
            "Noire", "Political film", "Adventure movie", "Fairy tale", "Fan fiction",
            "Romantic novel", "Horror", "Classic", "Tragedy", "Tragicomedy", "Folklore",
            "Historical fiction", "Humor", "Thriller", "Biograpy", "Novel"};

    //creating adapter
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categoryList);
        setListAdapter(myAdapter);
    }

}
