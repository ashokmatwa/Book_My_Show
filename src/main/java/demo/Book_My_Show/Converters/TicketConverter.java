package demo.Book_My_Show.Converters;

import demo.Book_My_Show.DTOs.EntryDtos.TicketEntryDto;
import demo.Book_My_Show.Models.Ticket;

public class TicketConverter {

    public static Ticket convertEntryDtoToEntity(TicketEntryDto ticketEntryDto){
        Ticket ticket = new Ticket();//as we have nothing to set here
        return ticket;
    }
}
