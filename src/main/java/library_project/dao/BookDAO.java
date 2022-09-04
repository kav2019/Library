package library_project.dao;

import library_project.models.Book;
import library_project.models.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //метод возвращающий все книги
    @Transactional
    public List<Book> returnAllBook(){
//        String sql = "select * from book";
//        return jdbcTemplate.query(sql, new BookMapper());

        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery("select b from Book b", Book.class).getResultList();
    }


    //метод возвращающий одну книгу
    @Transactional
    public Book returnOneBook(int id){
//        String sql = "select * from book where id=?";
//        return jdbcTemplate.query(sql, new BookMapper(), id).stream().findAny().orElse(null);

        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    //метод добавляющий книгу
    @Transactional
    public void saveBook(Book book){
//        String sql = "insert into book (name, author, year) values (?, ?, ?)";
//        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear());

        Session session = sessionFactory.getCurrentSession();
        Book bookForDB = new Book(book.getName(), book.getAuthor(), book.getYear());
        session.save(bookForDB);
    }


    //метод сохраняющий изменеия
    @Transactional
    public void saveChangeBook(Book book, int id){
//        String sql = "update book set name=?, author=?, year=? where id=?";
//        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear(), id);

        Session session = sessionFactory.getCurrentSession();

        Book bookFromDB = session.get(Book.class, id);
        bookFromDB.setName(book.getName());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setYear(book.getYear());
    }

    //метод удляющий книгу
    @Transactional
    public void delBook(int id){
//        String sql = "delete from book where id=?";
//        jdbcTemplate.update(sql, id);

        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.remove(book);
    }

    //метод показывающий у книги читателей
    @Transactional
    public List<People> getReadBook(int id){
//        String sql = "select people.id, people.name, people.yearBorn from people " +
//                "join user_book " +
//                "on people.id = user_book.user_id " +
//                "join book " +
//                "on user_book.book_id = book.id " +
//                "where book_id = ?";
//        return jdbcTemplate.query(sql, new PeopleMapper(), id);
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return book.getPeoples();

    }

    //добавляет книгу к пользователю
    @Transactional
    public void setReadPeople(int idUsers, int idBook){
//        String sql = "insert into user_book (user_id, book_id) values (?, ?)";
//        jdbcTemplate.update(sql, idUsers, idBook);

        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, idBook);
        People people = session.get(People.class, idUsers);

        book.getPeoples().add(people);
        people.getBooks().add(book);
    }









}
