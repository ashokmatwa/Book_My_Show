package demo.Book_My_Show.Controllers;

import demo.Book_My_Show.DTOs.EntryDtos.TheaterEntryDto;
import demo.Book_My_Show.Models.Theater;
import demo.Book_My_Show.Models.TheaterSeat;
import demo.Book_My_Show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try{
            String result = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "theater not added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getTheater")
    public ResponseEntity<List<String>> getTheaters(@RequestParam int movieId){
//        List<Theater> theaterList = theaterService.getTheaters(movieId);
//        return new ResponseEntity<>(theaterList, HttpStatus.FOUND);
        List<String> theaterNameList = theaterService.getTheaters(movieId);
        return new ResponseEntity<>(theaterNameList, HttpStatus.FOUND);
    }
    @GetMapping("/uniqueLocation")
    public ResponseEntity<List<String>> getUniqueLocation(){
        List<String> locationList = theaterService.getUniqueLocation();
        return new ResponseEntity<>(locationList, HttpStatus.FOUND);
    }

    @GetMapping("/time")
    public ResponseEntity<List<String>> getTheaters(@RequestParam LocalTime time){
        List<String> theaterList = theaterService.getTheaterForTime(time);
        return new ResponseEntity<>(theaterList, HttpStatus.FOUND);
    }
}
