package demo.Book_My_Show.Models;

import demo.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "show_seat")
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isBooked;
    private int price; //price of CLASSIC Seat for that particular show
    private String seatNo;
    @CreatedDate
    private Date bookedAt;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    //show --> showSeat
    @ManyToOne
    @JoinColumn
    private Show show;
}
