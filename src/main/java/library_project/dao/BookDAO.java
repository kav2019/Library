package library_project.dao;

import library_project.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //метод возвращающий все книги
    public List<Book> returnAllBook(){
        String sql = "select * from book";
        return jdbcTemplate.query(sql, new BookMapper());
    }


    //метод возвращающий одну книгу
    public Book returnOneBook(int id){
        String sql = "select * from book where id=?";
        return jdbcTemplate.query(sql, new BookMapper(), id).stream().findAny().orElse(null);
    }

    //метод добавляющий книгу
    public void saveBook(Book book){
        String sql = "insert into book (name, author, year) values (?, ?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear());
    }


    //метод сохраняющий изменеия
    public void saveChangeBook(Book book, int id){
        String sql = "update book set name=?, author=?, year=? where id=?";
        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear(), id);
    }

    //метод удляющий книгу
    public void delBook(int id){
        String sql = "delete from book where id=?";
        jdbcTemplate.update(sql, id);
    }







}
