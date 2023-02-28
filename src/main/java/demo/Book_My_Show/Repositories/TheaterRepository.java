package demo.Book_My_Show.Repositories;

import demo.Book_My_Show.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

    @Query(value = "select distinct location from theater", nativeQuery = true)
    List<String> getLocation();
}
