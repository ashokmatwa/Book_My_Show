package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.TicketConverter;
import demo.Book_My_Show.DTOs.EntryDtos.TicketEntryDto;
import demo.Book_My_Show.Models.Show;
import demo.Book_My_Show.Models.ShowSeat;
import demo.Book_My_Show.Models.Ticket;
import demo.Book_My_Show.Models.User;
import demo.Book_My_Show.Repositories.ShowRepository;
import demo.Book_My_Show.Repositories.TicketRepository;
import demo.Book_My_Show.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{

        Ticket ticket = TicketConverter.convertEntryDtoToEntity(ticketEntryDto);

        //validation : check if the requested seats are available or NOT ?
        boolean isValidRequest = checkValidityofRequestedSeats(ticketEntryDto);
        if (!isValidRequest)
            throw new Exception("seats already occupied");
        //valid seats
        //calculate total amount
        Show show = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();
        int totalAmount = 0;
        for (ShowSeat showSeat : showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalAmount = totalAmount + showSeat.getPrice();
                showSeat.setBooked(true);//seat booked
                showSeat.setBookedAt(new Date());//booked on that date --> initially NULL
            }
        }
        //setting attributes
        ticket.setTotalAmount(totalAmount);
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getName());

        //set string that talked about requested seats
        String allottedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        ticket.setBookedSeats(allottedSeats);

        //foreign attribute
        User user = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticket.setShow(show);
        ticket.setUser(user);

        ticket = ticketRepository.save(ticket); // to stop from added two times

        //bidirectional
        show.getTicketList().add(ticket);
        user.getBookedTicketList().add(ticket);

        showRepository.save(show);
        userRepository.save(user); // cascade --> ticked booked

        return "ticket booked successfully";
    }
    private boolean checkValidityofRequestedSeats(TicketEntryDto ticketEntryDto){

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int showId = ticketEntryDto.getShowId();
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        //check if requested seat is actually exists or not
//        for(String seat : requestedSeats){
//            if(!showSeatList.contains(seat))
//                return false;
//            System.out.println(seat);
//        }

        //iterating over list of SEATS for that particular SHOW
        for (ShowSeat showSeat : showSeatList){
            String seatNo = showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)){
                if(showSeat.isBooked())
                    return false; //seat already occupied
            }
        }

        return true; //all the seats are available
    }
    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats){

        //to save in SQL we set them as a STRING instead of List<String>
        String result = "";
        for (String seat : requestedSeats){
            result = result + seat + ", ";
        }
        return result;
    }

    public List<Integer> getTicketByUser(int userId){
        User user = userRepository.findById(userId).get();
        List<Ticket> ticketList = user.getBookedTicketList();

        List<Integer> ticketIdList = new ArrayList<>();
        for (Ticket ticket :ticketList)
            ticketIdList.add(ticket.getId());

        return ticketIdList;
    }

    public String cancelTicket(int ticketId){
        //ticketEntryDto -->  showId, requestedSeats, userId

        //set
        // seat as Not booked
        // amount = 0

        Ticket ticket = ticketRepository.findById(ticketId).get();
        String seats[] = ticket.getBookedSeats().split(",");

        Show show = ticket.getShow();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat : showSeatList)
            showSeat.setBooked(false);

        ticket.setTotalAmount(0);
        ticketRepository.save(ticket);
        return "ticket cancelled";
    }
}
