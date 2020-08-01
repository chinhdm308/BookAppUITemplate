package edu.bookappuitemplate.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import edu.bookappuitemplate.R;
import edu.bookappuitemplate.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    List<Book> mData;
    BookCallback listener;

    public BookAdapter(List<Book> mData, BookCallback listener) {
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // bind book item data here
        // load img using Glide
        Glide.with(holder.itemView.getContext())
                .load(mData.get(position).getDrawableResource()) // set the img book Url
                .transform(new RoundedCorners(16))
                .into(holder.imgBook);

        holder.ratingBar.setRating((float) 4.5);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBook, imgFav, imgContainer;
        TextView tvTitle, tvPages, tvAuthor, tvRate;
        RatingBar ratingBar;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.item_book_img);
            imgContainer = itemView.findViewById(R.id.container);
            tvTitle = itemView.findViewById(R.id.item_book_title);
            tvAuthor = itemView.findViewById(R.id.item_book_author);
            tvRate = itemView.findViewById(R.id.item_book_score);
            tvPages = itemView.findViewById(R.id.item_book_pagesrev);
            ratingBar = itemView.findViewById(R.id.item_book_ratingbar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBookItemClick(getAdapterPosition(),
                            imgContainer,
                            imgBook,
                            tvTitle,
                            tvAuthor,
                            tvPages,
                            tvRate,
                            ratingBar);
                }
            });
        }
    }
}
