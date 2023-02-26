package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.MovieConverter;
import demo.Book_My_Show.DTOs.EntryDtos.MovieEntryDto;
import demo.Book_My_Show.Models.Movie;
import demo.Book_My_Show.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{

        //call converter function to convert EntryDto --> Entity
        Movie movie = MovieConverter.convertEntryDtoToEntity(movieEntryDto);

        movieRepository.save(movie);
        return "movie added successfully";
    }
}
