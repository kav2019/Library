package library_project.dao;

import library_project.models.Book;
import library_project.models.People;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class BookDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //метод возвращающий все книги
    @Transactional
    public List<Book> returnAllBook(){
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("select b from Book as b", Book.class).getResultList();
    }


    //метод возвращающий одну книгу
    @Transactional
    public Book returnOneBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return book;
    }

    //метод добавляющий книгу
    @Transactional
    public void saveBook(Book book){
        Session session = sessionFactory.getCurrentSession();
        Book bookForDB = new Book(book.getName(), book.getAuthor(), book.getYear());
        session.save(bookForDB);
    }

    //метод сохраняющий изменеия
    @Transactional
    public void saveChangeBook(Book book, int id){
        Session session = sessionFactory.getCurrentSession();
        Book bookFromDB = session.get(Book.class, id);
        bookFromDB.setName(book.getName());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setYear(book.getYear());
    }

    //метод удляющий книгу
    @Transactional
    public void delBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.remove(book);
    }

    //метод показывающий у книги читателей
    @Transactional
    public List<People> getReadBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        List<People> peopleList = book.getPeoples();
        Hibernate.initialize(peopleList);
        return peopleList;

    }

    //добавляет книгу к пользователю
    @Transactional
    public void setReadPeople(int idUsers, int idBook){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, idBook);
        People people = session.get(People.class, idUsers);
        people.getBooks().add(book);
    }









}
