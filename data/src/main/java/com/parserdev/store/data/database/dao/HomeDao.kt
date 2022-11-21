package com.parserdev.store.data.database.dao

import androidx.room.*
import com.parserdev.store.data.dto.home.FavouriteItemDto
import com.parserdev.store.domain.models.home.HomeCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteItemDto(favouriteItemDto: FavouriteItemDto)

    @Delete
    suspend fun deleteFavouriteItemDto(favouriteItemDto: FavouriteItemDto)

    @Query("SELECT EXISTS(SELECT * FROM favouriteItem WHERE id = :id AND homeCategory = :homeCategory)")
    fun isFavouriteItemDtoExists(id: Int, homeCategory: HomeCategory): Boolean
}
