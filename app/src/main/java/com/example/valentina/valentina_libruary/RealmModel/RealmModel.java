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
        //AddFirstBook();
        //CloseRealm();
    }

    public RealmResults<Book> getRealmModel(){
        if (realm.isClosed())
            return null;
        return realm.where(Book.class).findAll();
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

    public void CloseRealm(){
        realm.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        CloseRealm();
    }

    public void AddFirstBook(){
        realm.beginTransaction();
        try{
            // set defolt book #1
            Book book = realm.createObject(Book.class);
            //book.setId("000000");
            book.setName("Harry Potter and the Philosopher's Stone");
            book.setAuthor("J. K. Rowling");
            book.setCategory("Fantasy");
            book.setDescription("Harry Potter and the Philosopher's Stone is a " +
                    "fantasy novel written by British author J. K. Rowling. It is " +
                    "the first novel in the Harry Potter series and Rowling's debut " +
                    "novel, first published in 1997 by Bloomsbury. It was published " +
                    "in the United States as Harry Potter and the Sorcerer's Stone " +
                    "by Scholastic Corporation in 1998.");
            //book.setStatus("Free");
            realm.insertOrUpdate(book);
            realm.commitTransaction();

            // set defolt book #1
            Book book1 = realm.createObject(Book.class);
            //book1.setId("000001");
            book1.setName("Three Comrades");
            book1.setAuthor("Erich Maria Remarque");
            book1.setCategory("Novel");
            book1.setDescription("Three Comrades (German: Drei Kameraden) is a novel first " +
                    "published in 1936 by the German author Erich Maria Remarque. It is written " +
                    "in first person by the main character Robert Lohkamp, whose somewhat " +
                    "disillusioned outlook on life is due to his horrifying experiences in " +
                    "the trenches of the First World War's French-German front.");
            //book1.setStatus("Taken");
            realm.insertOrUpdate(book1);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }
}

