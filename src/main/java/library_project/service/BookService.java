package library_project.service;

import library_project.models.Book;
import library_project.models.People;
import library_project.repository.BookRpository;
import library_project.repository.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRpository bookRpository;
    private final PeopleService peopleService;

    @Autowired
    public BookService(BookRpository bookRpository, PeopleRepository peopleRepository, PeopleService peopleService) {
        this.bookRpository = bookRpository;
        this.peopleService = peopleService;
    }

    //метод возвращающий все книги
    public List<Book> returnAllBook(){
        return bookRpository.findAll();
    }

    //метод возвращающий одну книгу
    public Book returnOneBook(int id){
        return bookRpository.findById(id).orElse(null);
    }

    //метод добавляющий книгу
    @Transactional
    public void saveBook(Book book){
        bookRpository.save(book);
    }

    //метод сохраняющий изменеия
    @Transactional
    public void saveChangeBook(Book book, int id){
        book.setId(id);
        bookRpository.save(book);
    }

    //метод удляющий книгу
    @Transactional
    public void delBook(int id){
        bookRpository.deleteById(id);
    }

    //метод показывающий у книги читателей
    public List<People> getReadBook(int id){
        Book book = bookRpository.findById(id).get();
        List<People> peopleList = book.getPeoples();
        Hibernate.initialize(peopleList);
        return peopleList;
    }

    //добавляет книгу к пользователю
    @Transactional
    public void setReadPeople(int idUsers, int idBook){
        Book book = bookRpository.findById(idBook).get();
        People people = peopleService.returnOnePeople(idUsers);
        people.getBooks().add(book);
    }
}
