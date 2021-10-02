package cz.tilseroz.autowiredinjecttesting.service;

import cz.tilseroz.autowiredinjecttesting.entity.Book;
import cz.tilseroz.autowiredinjecttesting.service.toinject.BookService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.EmailService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.StoreService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RequiredArgsConstructor
public class ConstructorService {

    private final BookService bookService;
    private final EmailService emailService;
    private final StoreService storesService;
    private final UserService userService;

    public ConstructorService(BookService bookService, EmailService emailService, StoreService storesService, UserService userService) {
        this.bookService = bookService;
        this.emailService = emailService;
        this.storesService = storesService;
        this.userService = userService;
    }

    public Book retrieveBookByUserIdOrMail(String username, String email) {

        Long userId = userService.retrieveUserByUsername(username);

        Book book = bookService.retrieveBookByUserId(userId);

        if (book == null) {
            book = emailService.retrieveBookByEmail(email);
        }

        if (book == null) {
            log.info("Book not found");
            storesService.createRequestForBook(userId);
        }

        return book;

    }
}