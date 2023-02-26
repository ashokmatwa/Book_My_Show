package demo.Book_My_Show.Converters;

import demo.Book_My_Show.DTOs.EntryDtos.TheaterEntryDto;
import demo.Book_My_Show.Models.Theater;

public class TheaterConverter {

    public static Theater convertEntryDtoToEntity(TheaterEntryDto theaterEntryDto){
        Theater theater = Theater.builder().name(theaterEntryDto.getName())
                .location(theaterEntryDto.getLocation()).build();
        return theater;
    }
}
