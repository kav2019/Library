package library_project.dao;

import library_project.models.Book;
import library_project.models.People;
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


    //����� ������������ ��� �����
    public List<Book> returnAllBook(){
        String sql = "select * from book";
        return jdbcTemplate.query(sql, new BookMapper());
    }


    //����� ������������ ���� �����
    public Book returnOneBook(int id){
        String sql = "select * from book where id=?";
        return jdbcTemplate.query(sql, new BookMapper(), id).stream().findAny().orElse(null);
    }

    //����� ����������� �����
    public void saveBook(Book book){
        String sql = "insert into book (name, author, year) values (?, ?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear());
    }


    //����� ����������� ��������
    public void saveChangeBook(Book book, int id){
        String sql = "update book set name=?, author=?, year=? where id=?";
        jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getYear(), id);
    }

    //����� �������� �����
    public void delBook(int id){
        String sql = "delete from book where id=?";
        jdbcTemplate.update(sql, id);
    }

    //����� ������������ � ����� ���������
    public List<People> getReadBook(int id){
        String sql = "select people.id, people.name, people.yearBorn from people " +
                "join user_book " +
                "on people.id = user_book.user_id " +
                "join book " +
                "on user_book.book_id = book.id " +
                "where book_id = ?";
        return jdbcTemplate.query(sql, new PeopleMapper(), id);
    }

    //��������� ����� � ������������
    public void setReadPeople(int idUsers, int idBook){
        String sql = "insert into user_book (user_id, book_id) values (?, ?)";
        jdbcTemplate.update(sql, idUsers, idBook);
    }









}
