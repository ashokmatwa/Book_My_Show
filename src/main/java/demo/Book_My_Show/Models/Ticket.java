package demo.Book_My_Show.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket")
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalAmount; // total price
    private String movieName;
    private LocalTime showTime;
//    @CreatedDate
    private LocalDate showDate;
    private String ticketId = UUID.randomUUID().toString();
    private String theaterName;
    private String bookedSeats;
//    private List<Integer> bookedSeats;

    //user --> tickets
    @ManyToOne
    @JoinColumn
    private User user;

    //show --> tickets
    @ManyToOne
    @JoinColumn
    private Show show;
}
