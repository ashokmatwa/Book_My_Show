package demo.Book_My_Show.Converters;

import demo.Book_My_Show.DTOs.EntryDtos.MovieEntryDto;
import demo.Book_My_Show.Models.Movie;

public class MovieConverter {

    //static so that we can call it with class name itself
    //no need waste time and space in creating object
    public static Movie convertEntryDtoToEntity(MovieEntryDto movieEntryDto){

        Movie movie = Movie.builder()
                .movieName(movieEntryDto.getMovieName()).rating(movieEntryDto.getRating())
                .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage())
                .genre(movieEntryDto.getGenre()).build();

        return movie;
    }
}
