package com.example.readingmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    private Book currentBook;
    private ImageView coverImageView;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView publisherTextView;
    private TextView descriptionTextView;
    private Button bookmarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        coverImageView = findViewById(R.id.imageViewCover);
        titleTextView = findViewById(R.id.textViewTitle);
        authorTextView = findViewById(R.id.textViewAuthor);
        publisherTextView = findViewById(R.id.textViewPublisher);
        descriptionTextView = findViewById(R.id.textViewDescription);
        bookmarkButton = findViewById(R.id.btnBookmark);

        // 도서 상세 정보 가져와 화면에 표시
        currentBook = (Book) getIntent().getSerializableExtra("SELECTED_BOOK");
        if (currentBook != null) {
            // 이미지 로딩
            NaverApiUtil.loadImage(this, currentBook.getImageURL(), coverImageView);

            // 제목, 작가, 출판사, 설명 표시
            titleTextView.setText(currentBook.getTitle());
            authorTextView.setText("저자: " + currentBook.getAuthor());
            publisherTextView.setText("출판사: " + currentBook.getPublisher());
            descriptionTextView.setText(currentBook.getDescription());
        }

        // 북마크 버튼 클릭 이벤트 처리
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleBookmark();
            }
        });

        updateBookmarkUI();
    }

    private void toggleBookmark() {
        currentBook.setBookmarked(!currentBook.isBookmarked());

        if (currentBook.isBookmarked()) {
            BookmarkManager.addBookmark(this, currentBook.getId());
        } else {
            BookmarkManager.removeBookmark(this, currentBook.getId());
        }

        updateBookmarkUI();
    }

    private void updateBookmarkUI() {
        Button bookmarkButton = findViewById(R.id.btnBookmark);

        if (currentBook.isBookmarked()) {
            bookmarkButton.setText("북마크 해제");
            bookmarkButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_bookmark_on, 0, 0, 0);
        } else {
            bookmarkButton.setText("북마크 추가");
            bookmarkButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_bookmark_off, 0, 0, 0);
        }
    }
}
