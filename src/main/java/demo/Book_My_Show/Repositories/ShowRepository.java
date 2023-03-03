package demo.Book_My_Show.Repositories;

import demo.Book_My_Show.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    List<Show> findByShowTime(LocalTime localTime);
}
