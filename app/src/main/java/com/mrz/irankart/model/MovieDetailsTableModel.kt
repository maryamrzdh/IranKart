package com.mrz.irankart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.rxjava3.annotations.NonNull
import org.jetbrains.annotations.NonNls

@Entity(tableName = "MoviesDetails")
class MovieDetailsTableModel(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    var id: String,

    @ColumnInfo(name = "title")
    var Title: String,

    @ColumnInfo(name = "Year")
    var Year: String,

    @ColumnInfo(name = "type")
    var Type: String,

    @ColumnInfo(name="poster")
    var poster :String,

    @ColumnInfo(name="rating")
    var rating :String

) {


}