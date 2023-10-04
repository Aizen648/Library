package pl.kolo.Library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kolo.Library.model.Book;
import pl.kolo.Library.service.BookService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookService bookService;

    @Test
    @Transactional
    void addSingleBookAndGetSingleBook() throws Exception {
        //given
        Long emptyId = null;
        String title = "title test";
        String author = "author test";
        LocalDateTime created = LocalDateTime.now();

        Book newBook = new Book(emptyId, emptyId, title, author, created);
        bookService.addBook(newBook);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books/" + newBook.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //then
        Book book = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);
        assertNotNull(book);
        assertEquals(book.getId(), newBook.getId());
        assertEquals(book.getTitle(), title);
        assertEquals(book.getAuthor(), author);
        assertEquals(created, book.getCreated());
    }

    @Test
    void getAllAvailableBooks() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books/available"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Book[] books = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        for (Book book : books) {
            if (book.getClientId() != null) {
                fail();
            }
        }
    }

    @Test
    void getAllBooks() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Book[] books = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        assertTrue(books.length > 95 && books.length < 1000);
    }

    @Test
    void getPageBooks_default() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/available/page"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isNotEmpty());

    }

    @Test
    void getPageBooks_notDefault() throws Exception {
        int page = 2;
        int pageLimit = 15;

        mockMvc.perform(MockMvcRequestBuilders.get("/books/available/page")
                        .param("page", String.valueOf(page))
                        .param("pageLimit", String.valueOf(pageLimit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isNotEmpty());
    }

    @Test
    @Transactional
    void addSingleBookAndDelete() throws Exception {
        Long emptyId = null;
        String title = "title test";
        String author = "author test";
        LocalDateTime created = LocalDateTime.now();

        Book newBook = new Book(emptyId, emptyId, title, author, created);
        Book book = bookService.addBook(newBook);

        Book exist = bookService.getSingleBook(book.getId());
        assertNotNull(exist);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/" + book.getId()))
                .andExpect(MockMvcResultMatchers.status().is(410));

        assertThrows(NoSuchElementException.class, () -> bookService.getSingleBook(book.getId()));
    }

}