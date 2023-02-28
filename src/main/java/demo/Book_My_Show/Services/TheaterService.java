package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.TheaterConverter;
import demo.Book_My_Show.DTOs.EntryDtos.TheaterEntryDto;
import demo.Book_My_Show.Enums.SeatType;
import demo.Book_My_Show.Models.Movie;
import demo.Book_My_Show.Models.Show;
import demo.Book_My_Show.Models.Theater;
import demo.Book_My_Show.Models.TheaterSeat;
import demo.Book_My_Show.Repositories.MovieRepository;
import demo.Book_My_Show.Repositories.TheaterRepository;
import demo.Book_My_Show.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatRepository theaterSeatRepository; // seat
    @Autowired
    MovieRepository movieRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{

        //Do some validation
        //if name is null or location is null or name already exists

        Theater theater = TheaterConverter.convertEntryDtoToEntity(theaterEntryDto);

        //passing theater also to set theaterSeat attribute(theater)
        List<TheaterSeat> theaterSeatList = createTheaterSeats(theaterEntryDto, theater); //calling function

        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater); // cascade --> theaterSeat repo
        return "theater added successfully";
    }
    //passing theater also to set theaterSeat attribute(theater)
    private List<TheaterSeat> createTheaterSeats(TheaterEntryDto theaterEntryDto, Theater theater){

        int classicSeatCount = theaterEntryDto.getClassicSeatCount();
        int premiumSeatCount = theaterEntryDto.getPremiumSeatCount();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        //Created the classic Seats
        for(int count=1;count<=classicSeatCount;count++){
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.CLASSIC).seatNo(count+"C")
                    .theater(theater).build();

            theaterSeatList.add(theaterSeat);
        }
        //Create the premium Seats
        for(int count=1;count<=premiumSeatCount;count++){
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.PREMIUM).seatNo(count+"P")
                    .theater(theater).build();

            theaterSeatList.add(theaterSeat);
        }

//        theaterSeatRepository.saveAll(theaterSeatList);
        return theaterSeatList;
    }

    public List<Theater> getTheaters(int movieId){
        Movie movie = movieRepository.findById(movieId).get();
        List<Show> showList = movie.getShowList();

        List<Theater> theaterList = new ArrayList<>();
        for (Show show : showList){
            Theater theater = show.getTheater();
            theaterList.add(theater);
        }
        return theaterList;
    }
    public List<String> getUniqueLocation(){
        List<String> locationList = theaterRepository.getLocation();
        return locationList;
    }
}
