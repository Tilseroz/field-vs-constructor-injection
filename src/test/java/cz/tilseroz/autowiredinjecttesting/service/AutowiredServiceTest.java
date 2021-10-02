package cz.tilseroz.autowiredinjecttesting.service;

import cz.tilseroz.autowiredinjecttesting.entity.Book;
import cz.tilseroz.autowiredinjecttesting.service.toinject.BookService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AutowiredServiceTest {

    private static final String NAME = "David";
    private static final String E_MAIL = "david@example.com";

    @Autowired
    AutowiredService autowiredService;

    @MockBean
    UserService userService;

    @MockBean
    BookService bookService;

    @Test
    void retrieveBookByUserIdOrMail() {
        Mockito.when(userService.retrieveUserByUsername(NAME)).thenReturn(1L);
        Mockito.when(bookService.retrieveBookByUserId(1L)).thenReturn(Book.builder()
                .author("Puvak")
                .id(1L)
                .copyright("Tilser")
                .title("Povidky ze Stringdat")
                .build());

        Book book = autowiredService.retrieveBookByUserIdOrMail(NAME, E_MAIL);
        assertEquals(book.getTitle(), "Povidky ze Stringdat");
        assertEquals(book.getAuthor(), "Puvak");
    }

}