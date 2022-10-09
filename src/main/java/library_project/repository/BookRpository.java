package library_project.repository;

import library_project.models.Book;
import library_project.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRpository extends JpaRepository<Book, Integer> {
}
