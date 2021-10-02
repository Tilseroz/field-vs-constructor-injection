package cz.tilseroz.autowiredinjecttesting.service;

import cz.tilseroz.autowiredinjecttesting.entity.Book;
import cz.tilseroz.autowiredinjecttesting.service.toinject.BookService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.EmailService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.StoreService;
import cz.tilseroz.autowiredinjecttesting.service.toinject.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutowiredService {

    @Autowired
    private BookService bookService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StoreService storesService;

    @Autowired
    private UserService userService;

    /**
     * Just method for test purposes using all injected services
     */
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
