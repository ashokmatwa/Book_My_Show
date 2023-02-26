package demo.Book_My_Show.DTOs.EntryDtos;

import demo.Book_My_Show.Enums.MovieGenre;
import demo.Book_My_Show.Enums.MovieLanguage;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;
    private double rating;
    private int duration;
    private MovieLanguage language;
    private MovieGenre genre; //enum
}
