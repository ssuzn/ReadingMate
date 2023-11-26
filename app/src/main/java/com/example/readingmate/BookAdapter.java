package com.example.readingmate;

// BookAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class BookAdapter extends ListAdapter<BookItem, BookAdapter.ViewHolder> {

    private final OnItemClickListener itemClickListener;
    private final Context context;

    public interface OnItemClickListener {
        void onItemClick(BookItem book);
    }

    public BookAdapter(OnItemClickListener itemClickListener, Context context) {
        super(diffUtil);
        this.itemClickListener = itemClickListener;
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
        BookItem bookItem = getItem(position);

        // Glide 라이브러리를 사용하여 이미지 로드 및 표시
        Glide.with(context)
                .load(bookItem.getImageURL())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.imageViewCover);

        holder.textViewTitle.setText(bookItem.getTitle());
        holder.textViewAuthor.setText(bookItem.getAuthor());
        holder.textViewPublisher.setText(bookItem.getPublisher());

        // 자세히 보기 버튼 클릭 시 상세 화면으로 이동
        holder.buttonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(bookItem);
            }
        });
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

    private static final DiffUtil.ItemCallback<BookItem> diffUtil = new DiffUtil.ItemCallback<BookItem>() {
        @Override
        public boolean areItemsTheSame(BookItem oldItem, BookItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(BookItem oldItem, BookItem newItem) {
            return oldItem.equals(newItem);
        }
    };
}
