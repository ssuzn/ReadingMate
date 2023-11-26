package com.example.readingmate;

// BookAdapter.java
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<BookItem> bookList;
    private Context context;

    public BookAdapter(List<BookItem> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookItem bookItem = bookList.get(position);
        Picasso.get().load(bookItem.getImageURL()).placeholder(R.drawable.placeholder_image).into(holder.imageViewCover);

        holder.textViewTitle.setText(bookItem.getTitle());
        holder.textViewAuthor.setText(bookItem.getAuthor());
        holder.textViewPublisher.setText(bookItem.getPublisher());

        // 자세히 보기 버튼 클릭 시 상세 화면으로 이동
        holder.buttonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 책의 설명을 상세 화면으로 전달하여 표시
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("title", bookItem.getTitle());
                intent.putExtra("author", bookItem.getAuthor());
                intent.putExtra("publisher", bookItem.getPublisher());
                intent.putExtra("imageURL", bookItem.getImageURL());
                intent.putExtra("description", bookItem.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewCover;
        public TextView textViewTitle;
        public TextView textViewAuthor;
        public TextView textViewPublisher;
        public Button buttonViewDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCover = itemView.findViewById(R.id.imageViewCover);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewPublisher = itemView.findViewById(R.id.textViewPublisher);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);
        }
    }
}
