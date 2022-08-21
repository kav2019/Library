package library_project.dao;

import library_project.models.People;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper<People> {
    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People people = new People();
        people.setName(rs.getString("name"));
        people.setYearBorn(rs.getInt("yearBorn"));
        people.setId(rs.getInt("id"));
        return people;
    }
}
