package demo.Book_My_Show.Controllers;

import demo.Book_My_Show.DTOs.EntryDtos.TicketEntryDto;
import demo.Book_My_Show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto){
        try{
            String result = ticketService.addTicket(ticketEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "ticket not booked";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //require response DTO
    @GetMapping("/getTicket")
    public ResponseEntity<List<Integer>> getTicketByUser(@RequestParam int userId){
        List<Integer> ticketList = ticketService.getTicketByUser(userId);
        return new ResponseEntity<>(ticketList, HttpStatus.FOUND);
    }

    @DeleteMapping("/cancelTicket")
    public ResponseEntity<String> cancelTicket(@RequestParam int ticketId){
        String result = ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
