package com.example.valentina.valentina_libruary.RealmModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valentina.valentina_libruary.Object.Book;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmModel extends DbHelper {

    protected Realm realm;

    public RealmModel(Context context){
        Realm.init(context);
        config();
        realm = Realm.getDefaultInstance();
    }

    public boolean isInRealm(String name, String author, String category, String link,
                             String description, String image){
        if((realm.where(Book.class)
                    .equalTo("name", name)
                    .equalTo("author", author)
                    .equalTo("category", category)
                    .equalTo("link", link)
                    .equalTo("description", description)
                    .equalTo("image", image)
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

    public RealmResults<Book> findRealmModel(String name, String author, String category){
        if (realm.isClosed())
            return null;
        if (name.equals("") && !author.equals("") && !category.equals("")){
            return realm.where(Book.class)
                    .equalTo("author", author)
                    .equalTo("category", category)
                    .findAll();
        } else if (!name.equals("") && author.equals("") && !category.equals("")){
            return realm.where(Book.class)
                    .equalTo("name", name)
                    .equalTo("category", category)
                    .findAll();
        } else if (!name.equals("") && !author.equals("") && category.equals("")) {
            return realm.where(Book.class)
                    .equalTo("name", name)
                    .equalTo("author", author)
                    .findAll();
        } else if (name.equals("") && author.equals("") && !category.equals("")) {
            return realm.where(Book.class)
                    .equalTo("category", category)
                    .findAll();
        } else if (name.equals("") && !author.equals("") && category.equals("")) {
            return realm.where(Book.class)
                    .equalTo("author", author)
                    .findAll();
        } else if (!name.equals("") && author.equals("") && category.equals("")) {
            return realm.where(Book.class)
                    .equalTo("name", name)
                    .findAll();
        } else if (!name.equals("") && !author.equals("") && !category.equals("")) {
            return realm.where(Book.class)
                    .equalTo("name", name)
                    .equalTo("author", author)
                    .equalTo("category", category)
                    .findAll();
        }
        return realm.where(Book.class)
                .equalTo("name", name)
                .equalTo("author", author)
                .equalTo("category", category)
                .findAll();
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

    public boolean bookExist(EditText editName, EditText editAuthor,
                          TextView editCategory, EditText editDescription, String editImage){
        Book book = realm.where(Book.class)
                .equalTo("name", editName.toString())
                .equalTo("author", editAuthor.toString())
                .equalTo("category", editCategory.toString())
                .equalTo("description", editDescription.toString())
                .equalTo("image", editImage)
                .findFirst();
        if (book == null) {
            return false;
        }
        return true;
    }

    public void setBook(EditText editName, EditText editAuthor, TextView editCategory,
                        EditText editLink, EditText editDescription, String editImage){
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

            if(!editLink.getText().toString().equals(""))
                book.setLink(editLink.getText().toString());

            if(!editDescription.getText().toString().equals(""))
                book.setDescription(editDescription.getText().toString());

            /*if(!editStatus.getText().toString().equals(""))
            book.setStatus(editStatus.getText().toString());*/

            if(!editImage.equals(""))
                book.setImage(editImage);

            realm.insertOrUpdate(book);
            realm.commitTransaction();
        }catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public void updateBook(String beforeName, String beforeAuthor, String beforeCtegory,
                           String beforeLink, String beforeDescription, String beforeImage,
                           EditText editName, EditText editAuthor, TextView editCategory,
                           EditText editLink, EditText editDescription, String editImage){
        Book book = realm.where(Book.class)
                .equalTo("name", beforeName)
                .equalTo("author", beforeAuthor)
                .equalTo("category", beforeCtegory)
                .equalTo("link", beforeLink)
                .equalTo("description", beforeDescription)
                .findFirst();
        realm.beginTransaction();
        try{
            if(!editName.getText().toString().equals(""))
                book.setName(editName.getText().toString());

            if(!editAuthor.getText().toString().equals(""))
                book.setAuthor(editAuthor.getText().toString());

            if(!editCategory.getText().toString().equals(""))
                book.setCategory(editCategory.getText().toString());

            if(!editLink.getText().toString().equals(""))
                book.setLink(editLink.getText().toString());

            if(!editDescription.getText().toString().equals(""))
                book.setDescription(editDescription.getText().toString());

            if(!editImage.equals(""))
                book.setImage(editImage);

            realm.commitTransaction();

        }catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public void closeRealm(){
        realm.close();
    }

    /*@Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeRealm();
    }*/

}

