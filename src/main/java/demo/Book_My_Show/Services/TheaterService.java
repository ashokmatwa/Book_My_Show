package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.TheaterConverter;
import demo.Book_My_Show.DTOs.EntryDtos.TheaterEntryDto;
import demo.Book_My_Show.Enums.SeatType;
import demo.Book_My_Show.Models.Theater;
import demo.Book_My_Show.Models.TheaterSeat;
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

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{
        Theater theater = TheaterConverter.convertEntryDtoToEntity(theaterEntryDto);

        List<TheaterSeat> theaterSeatList = createTheaterSeats(theaterEntryDto, theater);

        theaterRepository.save(theater);
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

        theaterSeatRepository.saveAll(theaterSeatList);
        return theaterSeatList;
    }
}
