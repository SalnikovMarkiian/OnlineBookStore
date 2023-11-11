package com.example.onlinebookstore.service.book;

import com.example.onlinebookstore.dto.book.BookDto;
import com.example.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.book.CreateBookRequestDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.BookMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toBook(requestDto);
        addBookCategories(requestDto.categoryIds(), book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find book by id " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookDto update(CreateBookRequestDto bookRequestDto, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find book by id " + id)
        );
        bookMapper.updateBook(bookRequestDto, book);
        addBookCategories(bookRequestDto.categoryIds(), book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBookByCategoryId(Long id, Pageable pageable) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " doesn't exist");
        }
        return bookRepository.findAllByCategoriesId(id, pageable)
                .stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private void addBookCategories(List<Long> categoryIds, Book book) {
        book.setCategories(categoryIds.stream()
                .map(categoryRepository::getReferenceById)
                .collect(Collectors.toSet()));
    }
}
