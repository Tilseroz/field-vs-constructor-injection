package cz.tilseroz.autowiredinjecttesting.service;

import cz.tilseroz.autowiredinjecttesting.entity.Book;
import cz.tilseroz.autowiredinjecttesting.service.toinject.BookService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConstructorServiceTest {

    private static final String NAME = "David";
    private static final String E_MAIL = "david@example.com";

    @InjectMocks
    private ConstructorService constructorService;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Test
    void retrieveBookByUserIdOrMail() {
        when(userService.retrieveUserByUsername(NAME)).thenReturn(1L);
        when(bookService.retrieveBookByUserId(1L)).thenReturn(Book.builder()
                .author("Puvak")
                .id(1L)
                .copyright("Tilser")
                .title("Povidky ze Stringdat")
                .build());

        Book book = constructorService.retrieveBookByUserIdOrMail(NAME, E_MAIL);
        assertEquals(book.getTitle(), "Povidky ze Stringdat");
        assertEquals(book.getAuthor(), "Puvak");
    }
}