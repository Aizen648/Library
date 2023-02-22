package pl.kolo.Library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int count(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM rental WHERE idClient=? group by idClient", Integer.class, id);
        } catch (Exception e) {
            return -1;
        }
    }

    public List<Integer> borrowed(int id) {
        return jdbcTemplate.queryForList(" SELECT idBook FROM rental WHERE idClient=?", Integer.class, id);
    }

    public int add(int idClient, List<Integer> idBooks) {
        for (Integer i : idBooks) {
            jdbcTemplate.update("INSERT INTO rental(idClient,idBook) VALUES (?,?)", idClient, i);
        }
        return 1;
    }

    public int delete(int idBook) {
        return jdbcTemplate.update("DELETE FROM book WHERE idBook=?", idBook);
    }
}
