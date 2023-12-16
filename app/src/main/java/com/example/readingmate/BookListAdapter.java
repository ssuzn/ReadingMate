package com.example.readingmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        ImageView imageViewCover = convertView.findViewById(R.id.imageViewCover);
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);


        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());

        Glide.with(getContext())
                .load(book.getImageURL())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(imageViewCover);


        Button buttonViewDetails = convertView.findViewById(R.id.buttonViewDetails);
        buttonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book selectedBook = getItem(position);

                Intent intent = new Intent(getContext(), BookDetailsActivity.class);

                intent.putExtra("SELECTED_BOOK", selectedBook);

                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
