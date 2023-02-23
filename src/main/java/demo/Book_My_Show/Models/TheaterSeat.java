package demo.Book_My_Show.Models;

import demo.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theater_seat")
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    //theater --> theaterSeat
    @ManyToOne
    @JoinColumn
    private Theater theater;
}
