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

    public List<People> returnAllPeople(){
        String sql = "select * from people";
        return jdbcTemplate.query(sql,new PeopleMapper());
    }
//----метод возвращающий ВСЕХ пользователей
    //метод возвращающий ОДНОГО пользователей
    //метод сохраяющий изменения ОДНОГО пользователей
    //метод добавляющий ОДНОГО пользователей
    //метод удаляющий ОДНОГО пользователей





}
