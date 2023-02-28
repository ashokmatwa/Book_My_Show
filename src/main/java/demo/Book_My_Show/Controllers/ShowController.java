package demo.Book_My_Show.Controllers;

import demo.Book_My_Show.DTOs.EntryDtos.ShowEntryDto;
import demo.Book_My_Show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String > addShow(@RequestBody ShowEntryDto showEntryDto){
        try {
            String result = showService.addShow(showEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "show not added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShowTime")
    public ResponseEntity<List<LocalTime>> getShowTime(@RequestParam int theaterId, @RequestParam int movieId){
        List<LocalTime> localTime = showService.getShowTime(theaterId, movieId);
        return new ResponseEntity<>(localTime, HttpStatus.FOUND);
    }
}
