package cz.tilseroz.autowiredinjecttesting.service.toinject;

import cz.tilseroz.autowiredinjecttesting.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    public Book retrieveBookByUserId(Long userId) {
        return Book.builder()
                .author("Puvak")
                .id(1L)
                .copyright("Tilser")
                .title("Povidky ze Stringdat")
                .build();
    }
}
