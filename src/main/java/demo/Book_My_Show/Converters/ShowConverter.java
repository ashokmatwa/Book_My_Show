package demo.Book_My_Show.Converters;

import demo.Book_My_Show.DTOs.EntryDtos.ShowEntryDto;
import demo.Book_My_Show.Models.Show;

public class ShowConverter {

    public static Show convertEntryDtoToEntity(ShowEntryDto showEntryDto){
        Show show = Show.builder().showDate(showEntryDto.getShowDate()).showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType()).build();
        return show;
    }
}
