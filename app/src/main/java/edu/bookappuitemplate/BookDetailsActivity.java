package edu.bookappuitemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import edu.bookappuitemplate.model.Book;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView imgBook;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        imgBook = findViewById(R.id.item_book_img);
        ratingBar = findViewById(R.id.item_book_ratingbar);

        Book item = (Book) getIntent().getExtras().getSerializable("bookObject");

        loadBookDataItem(item);
    }

    private void loadBookDataItem(Book item) {
        Glide.with(this)
                .load(item.getDrawableResource())
                .transform(new RoundedCorners(16))
                .into(imgBook);
    }
}