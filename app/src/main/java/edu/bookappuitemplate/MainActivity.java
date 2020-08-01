package edu.bookappuitemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.bookappuitemplate.model.Book;
import edu.bookappuitemplate.recyclerview.BookAdapter;
import edu.bookappuitemplate.recyclerview.BookCallback;
import edu.bookappuitemplate.recyclerview.CustomItemAnimator;

public class MainActivity extends AppCompatActivity implements BookCallback {

    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private List<Book> mData;

    private Button btnAddBook, btnRemoveBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDataBook();
        setBookAdapter();
    }

    private void setBookAdapter() {
        bookAdapter = new BookAdapter(mData, this);
        rvBook.setAdapter(bookAdapter);
    }

    void initDataBook() {
        mData = new ArrayList<>();
        mData.add(new Book(R.drawable.book_1));
        mData.add(new Book(R.drawable.book_2));
        mData.add(new Book(R.drawable.book_3));
        mData.add(new Book(R.drawable.book_4));
        mData.add(new Book(R.drawable.book_5));
        mData.add(new Book(R.drawable.book_6));
        mData.add(new Book(R.drawable.book_7));
        mData.add(new Book(R.drawable.book_8));
    }

    void initViews() {
        btnAddBook = findViewById(R.id.btn_add);
        btnRemoveBook = findViewById(R.id.btn_remove);

        rvBook = findViewById(R.id.rv_book);
        rvBook.setLayoutManager(new LinearLayoutManager(this));
        rvBook.setHasFixedSize(true);

        // we need first to setup the custom item animator that we just create
        rvBook.setItemAnimator(new CustomItemAnimator());

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add(1, new Book(R.drawable.book_8));
                bookAdapter.notifyItemInserted(1);
            }
        });

        btnRemoveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(1);
                bookAdapter.notifyItemRemoved(1);
            }
        });
    }

    @Override
    public void onBookItemClick(int pos, ImageView imgContainer, ImageView imgBook, TextView title, TextView authorName, TextView nbPages, TextView score, RatingBar ratingBar) {
        // create intent and send book object to Details activity
        Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
        intent.putExtra("bookObject", mData.get(pos));

        // share animation setup
        Pair<View, String> p1 = Pair.create((View) imgContainer, "containerTN");
        Pair<View, String> p2 = Pair.create((View) imgBook, "bookTN");
        Pair<View, String> p3 = Pair.create((View) title, "booktitleTN");
        Pair<View, String> p4 = Pair.create((View) authorName, "authorTN");
        Pair<View, String> p5 = Pair.create((View) nbPages, "bookpagesTN");
        Pair<View, String> p6 = Pair.create((View) score, "scoreTN");
        Pair<View, String> p7 = Pair.create((View) ratingBar, "rateTN");

        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3, p4, p5, p6, p7);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            startActivity(intent, optionsCompat.toBundle());
        else
            startActivity(intent);
    }
}