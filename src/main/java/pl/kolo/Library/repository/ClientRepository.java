package pl.kolo.Library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.kolo.Library.tdo.Client;

import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Client> getAll(){
        return jdbcTemplate.query("SELECT id, name, lastName, dateOfBirth, city FROM client",
                BeanPropertyRowMapper.newInstance(Client.class));
    }
    public Client getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, name, lastName, dateOfBirth, city FROM client where id=?",
                BeanPropertyRowMapper.newInstance(Client.class),id);
    }
    public int save(Client client){
        jdbcTemplate.update("INSERT INTO client(name,lastName,dateOfBirth,city VALUES (?,?,?,?)",client.getName(),
                client.getLastName(),client.getDateOfBirth(),client.getCity());
        return 0;
    }
    public int update(Client client){
        return jdbcTemplate.update("UPDATE client set name=?, lastName=?, dateOfBirth=?, city=? WHERE id=?",
                client.getName(),client.getLastName(),client.getDateOfBirth(),client.getCity(),client.getId());
    }
    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM client WHERE id=?",id);
    }
    public int test2(){
        return 4;
    }


}
