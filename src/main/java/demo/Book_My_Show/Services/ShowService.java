package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.ShowConverter;
import demo.Book_My_Show.DTOs.EntryDtos.ShowEntryDto;
import demo.Book_My_Show.Enums.SeatType;
import demo.Book_My_Show.Models.*;
import demo.Book_My_Show.Repositories.MovieRepository;
import demo.Book_My_Show.Repositories.ShowRepository;
import demo.Book_My_Show.Repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception{
        Show show = ShowConverter.convertEntryDtoToEntity(showEntryDto);

        List<ShowSeat> showSeatList = createShowSeats(showEntryDto, show);
        show.setShowSeatList(showSeatList);

        show = showRepository.save(show); // to stop from adding two times

        //for bidirectional
        int theaterId = showEntryDto.getTheaterId();
        Theater theater = theaterRepository.findById(theaterId).get();
        theater.getShowList().add(show);
        //for bidirectional
        int movieId = showEntryDto.getMovieId();
        Movie movie = movieRepository.findById(movieId).get();
        movie.getShowList().add(show);

        //setting the foreign attribute
        show.setTheater(theater);
        show.setMovie(movie);

        //        showRepository.save(show);
        theaterRepository.save(theater);
        movieRepository.save(movie);

        return "show added successfully";
    }
    private List<ShowSeat> createShowSeats(ShowEntryDto showEntryDto, Show show){

        Theater theater = theaterRepository.findById(showEntryDto.getTheaterId()).get();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();//for skeleton

        List<ShowSeat> showSeatList = new ArrayList<>(); //virtual

        //set attributes of showSeat
        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = new ShowSeat();

            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showEntryDto.getClassicSeatPrice());
            else
                showSeat.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeat.setBooked(false);
            showSeat.setShow(show);//foreign attribute
//            showSeat.setBookedAt(new Date());
            showSeatList.add(showSeat); //adding to the list
        }

        return showSeatList;
    }

    public List<LocalTime> getShowTime(int theaterId, int movieId){
        List<Show> showList = theaterRepository.findById(theaterId).get().getShowList();
        List<LocalTime> showTimeList = new ArrayList<>();

        for (Show show : showList){
            int id = show.getMovie().getMovieId();
            if(id == movieId) showTimeList.add(show.getShowTime());
        }
        return showTimeList;
    }
}
