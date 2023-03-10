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
    public ResponseEntity<String> maximumNoShow(){
        //return movieName with maximum no of shows across all the theatre
        String movieName = movieService.maximumNoShow();
        return new ResponseEntity<>(movieName, HttpStatus.FOUND);
    }

    @GetMapping("/collection")
    public ResponseEntity<Integer> totalCollection(@RequestParam int movieId){
        Integer collection = movieService.totalCollection(movieId);
        return new ResponseEntity<>(collection, HttpStatus.FOUND);
    }
}
