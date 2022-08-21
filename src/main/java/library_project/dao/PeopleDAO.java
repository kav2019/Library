package library_project.dao;

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

    public List<People> returnAllPeople(){ //----метод возвращающий ВСЕХ пользователей
        String sql = "select * from people";
        return jdbcTemplate.query(sql,new PeopleMapper());
    }

    public People returnOnePeople(int id){ //---метод возвращающий ОДНОГО пользователя по id
        String sql = "select * from people where id=?";
        return jdbcTemplate.query(sql, new PeopleMapper(), id).stream().findAny().orElse(null);
    }

    public void saveChangePeople(People people,int id){ //метод сохраяющий изменения ОДНОГО пользователей
        String sql = "update people set name=?, yearBorn=? where id=?";
        jdbcTemplate.update(sql, people.getName(), people.getYearBorn(),id);
    }

    public void savePeople(People people){     //метод сохраяющий ОДНОГО пользователей
        String sql = "insert into people (name, yearBorn) values (?, ?)";
        jdbcTemplate.update(sql, people.getName(), people.getYearBorn());
    }

    public void delPeople(int id){ //метод удаляющий ОДНОГО пользователей
        String sql = "delete from people where id=?";
        jdbcTemplate.update(sql, id);
    }








}
