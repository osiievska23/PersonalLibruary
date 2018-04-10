package com.example.valentina.valentina_libruary.RealmModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valentina.valentina_libruary.Object.Book;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Valentina on 01.04.2018.
 */

public class RealmModel extends DbHelper {

    protected Realm realm;

    public RealmModel(Context context){
        Realm.init(context);
        config();
        realm = Realm.getDefaultInstance();
    }

    public boolean isInRealm(String name, String author, String category, String description, String image){
        if((realm.where(Book.class)
                    .equalTo("name", name)
                    .equalTo("author", author)
                    .equalTo("category", category)
                    .equalTo("description", description)
                    .equalTo("description", image)
                    .findAll()).isEmpty()){
            return false;
        }
            return true;
    }

    public RealmResults<Book> getRealmModel(){
        if (realm.isClosed())
            return null;
        return realm.where(Book.class).findAll();
    }

    public void deleteRealmObject(Book book){
        realm.beginTransaction();
        (realm.where(Book.class)
                .equalTo("name", book.getName())
                .equalTo("author", book.getAuthor())
                .equalTo("category", book.getCategory())
                .equalTo("description", book.getDescription())
                .findFirst()
        )
                .deleteFromRealm();
        realm.commitTransaction();
    }


    public void setBook(EditText editName, EditText editAuthor,
                        TextView editCategory, EditText editDescription, String editImage){
        realm.beginTransaction();
        try{
            Book book = realm.createObject(Book.class);
            /*if(!editId.getText().toString().equals(""))
                book.setId(editId.getText().toString());*/

            if(!editName.getText().toString().equals(""))
                book.setName(editName.getText().toString());

            if(!editAuthor.getText().toString().equals(""))
                book.setAuthor(editAuthor.getText().toString());

            if(!editCategory.getText().toString().equals(""))
                book.setCategory(editCategory.getText().toString());

            if(!editDescription.getText().toString().equals(""))
                book.setDescription(editDescription.getText().toString());

            /*if(!editStatus.getText().toString().equals(""))
                book.setStatus(editStatus.getText().toString());*/

            if(!editImage.equals(""))
                book.setImage(editImage);

            realm.insertOrUpdate(book);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public void closeRealm(){
        realm.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeRealm();
    }

}

