package demo.Book_My_Show.Models;

import demo.Book_My_Show.Enums.MovieGenre;
import demo.Book_My_Show.Enums.MovieLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @Column(unique = true,nullable = false)
    private String movieName;
    private double rating;
    private int duration;
    @Enumerated(value = EnumType.STRING)
    private MovieLanguage language;
    @Enumerated(value = EnumType.STRING)
    private MovieGenre genre; //enum

//    private LocalDate releaseDate;

    //movie --> show
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
}
