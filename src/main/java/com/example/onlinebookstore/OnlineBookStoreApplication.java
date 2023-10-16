package com.example.onlinebookstore;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.service.BookService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class OnlineBookStoreApplication {
    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book testBook = new Book();
                testBook.setTitle("Some title");
                testBook.setAuthor("Test Author");
                testBook.setIsbn("0001123");
                testBook.setPrice(BigDecimal.valueOf(100));
                testBook.setDescription("Some description");
                testBook.setCoverImage("Cover img");

                bookService.save(testBook);
                System.out.println(bookService.getAll());
            }
        };
    }

}
