package pl.kolo.Library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int count(int id){
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM rental WHERE idClient=? group by idClient", Integer.class, id);
        } catch (Exception e){
            return -1;
        }
    }
    public List<Integer> borrowed(int id){
        return jdbcTemplate.queryForList(" SELECT idBook FROM rental WHERE idClient=?",Integer.class, id);

    }
}
