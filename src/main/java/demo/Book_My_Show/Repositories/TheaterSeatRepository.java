package demo.Book_My_Show.Repositories;

import demo.Book_My_Show.Models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer> {
}
