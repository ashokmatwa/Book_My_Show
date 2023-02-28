package demo.Book_My_Show.DTOs.EntryDtos;

import demo.Book_My_Show.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {

    private LocalDate showDate;
    private LocalTime showTime;
    private ShowType showType;

    //showSeat attributes
    private int classicSeatPrice;
    private int premiumSeatPrice;

    //movie attributes
    private int movieId;

    //theater attributes
    private int theaterId;
}
