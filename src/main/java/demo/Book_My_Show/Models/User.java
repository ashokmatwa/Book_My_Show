package demo.Book_My_Show.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "user")
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Data//-->consist getter, setter, tostring, reqAlgsCons (above 4)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private int age;
    @NonNull
    @Column(unique = true)
    private String mobileNo;
    private String address;

    //user --> ticket
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> bookedTicketList = new ArrayList<>();
}
