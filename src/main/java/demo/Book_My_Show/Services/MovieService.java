package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.MovieConverter;
import demo.Book_My_Show.DTOs.EntryDtos.MovieEntryDto;
import demo.Book_My_Show.Models.Movie;
import demo.Book_My_Show.Models.Show;
import demo.Book_My_Show.Models.Ticket;
import demo.Book_My_Show.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String maximumNoShow(){
        List<Movie> movieList = movieRepository.findAll();
        int count = 0;
        String movieName = "";
        for(Movie movie : movieList){
            List<Show> showList = movie.getShowList();
            if(showList.size() > count){
                count = showList.size();
                movieName = movie.getMovieName();
            }
        }
        return movieName;
    }

    public Integer totalCollection(int movieId){
        int amount = 0;
        Movie movie = movieRepository.findById(movieId).get();

        List<Show> showList = movie.getShowList();
        for (Show show : showList){
            List<Ticket> ticketList = show.getTicketList();
            for(Ticket ticket : ticketList){
                amount = amount + ticket.getTotalAmount();
            }
        }
        return amount;
    }
}
