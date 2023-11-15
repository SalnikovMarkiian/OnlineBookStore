package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = "categories")
    List<Book> findAllByCategoriesId(Long categoryId, Pageable pageable);
}
