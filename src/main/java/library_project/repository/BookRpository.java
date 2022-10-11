package library_project.repository;

import library_project.models.Book;
import library_project.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRpository extends JpaRepository<Book, Integer> {
    List<Book> findByNameContaining(String name);
}
