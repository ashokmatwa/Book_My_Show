package demo.Book_My_Show.Controllers;

import demo.Book_My_Show.DTOs.EntryDtos.MovieEntryDto;
import demo.Book_My_Show.Models.Movie;
import demo.Book_My_Show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try {
            String result = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "movie not added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMovie")
    public ResponseEntity<Movie> maximumNoShow(){
        //return movie with maximum no of shows across all the theatre
        Movie movie = movieService.maximumNoShow();
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }
}
