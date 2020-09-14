package com.example.practice.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practice.Bean.Favorite;

@Dao
public interface FavoriteDao {
    @Insert
    void InsertFavorite(Favorite... favorites);
    @Query("SELECT FID FROM FAVORITE WHERE UID IN(:uid)")
    Integer FindFavorite(int uid);
}
