package library_project.dao;

import library_project.models.Book;
import library_project.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //метод возвращающий ВСЕХ пользователей
    public List<People> returnAllPeople(){
        String sql = "select * from people";
        return jdbcTemplate.query(sql,new PeopleMapper());
    }

    //метод возвращающий ОДНОГО пользователя по id
    public People returnOnePeople(int id){
        String sql = "select * from people where id=?";
        return jdbcTemplate.query(sql, new PeopleMapper(), id).stream().findAny().orElse(null);
    }

    //метод сохраяющий изменения ОДНОГО пользователей
    public void saveChangePeople(People people,int id){
        String sql = "update people set name=?, yearBorn=? where id=?";
        jdbcTemplate.update(sql, people.getName(), people.getYearBorn(),id);
    }

    //метод добавляющий ОДНОГО пользователей
    public void savePeople(People people){
        String sql = "insert into people (name, yearBorn) values (?, ?)";
        jdbcTemplate.update(sql, people.getName(), people.getYearBorn());
    }

    //метод удаляющий ОДНОГО пользователей
    public void delPeople(int id){
        String sql = "delete from people where id=?";
        jdbcTemplate.update(sql, id);
    }

    // //Метод получающий список книг у пользователя
    public List<Book> bookListUsing(int id){
        String sql = "select book.id, book.name, book.author, book.year from people " +
                "join user_book on people.id = user_book.user_id " +
                "join book on user_book.book_id = book.id " +
                "where people.id =?";
        return jdbcTemplate.query(sql, new BookMapper(), id);

    }








}
