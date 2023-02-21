package pl.kolo.Library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.kolo.Library.tdo.Book;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int save(List<Book> books) {
        for (Book book : books) {
            jdbcTemplate.update("INSERT INTO book(name,raiting, author, available) VALUES(?,?,?,?)"
                    ,book.getName(),book.getRaiting(),book.getAuthor(),book.isAvailable());
        }
        return 0;
    }

    public List<Book> getAll(){
        return jdbcTemplate.query("SELECT id, name, raiting, author, available FROM book ORDER BY id DESC", BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getById(int id){
        Book book = jdbcTemplate.queryForObject("SELECT id, name, raiting, author, available FROM book WHERE id=?",
                BeanPropertyRowMapper.newInstance(Book.class), id);
        return book;
    }

    public int update(Book book){
        return jdbcTemplate.update("UPDATE book SET name=? , raiting=? , author=?, available=? WHERE id=? ",
                book.getName(),book.getRaiting(),book.getAuthor(),book.isAvailable(),book.getId());

    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
