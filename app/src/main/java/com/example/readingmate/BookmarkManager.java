package com.example.readingmate;

import android.content.Context;
import android.content.SharedPreferences;
public class BookmarkManager {
    private static final String PREF_NAME = "BookmarkPref";

    // SharedPreferences에서 북마크 정보를 가져오는 메서드
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // 북마크를 추가하는 메서드
    public static void addBookmark(Context context, String bookId) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(bookId, true); // 북마크가 되었음을 표시
        editor.apply();
    }

    // 북마크를 제거하는 메서드
    public static void removeBookmark(Context context, String bookId) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(bookId); // 북마크 정보 제거
        editor.apply();
    }
}