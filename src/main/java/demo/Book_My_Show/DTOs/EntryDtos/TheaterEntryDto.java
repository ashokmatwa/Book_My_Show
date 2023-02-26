package demo.Book_My_Show.DTOs.EntryDtos;

import lombok.Data;

@Data
public class TheaterEntryDto {

    private String name;
    private String location;

    //new
    private int classicSeatCount;
    private int premiumSeatCount;
}
