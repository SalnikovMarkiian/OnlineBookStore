package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.category.CategoryDto;
import com.example.onlinebookstore.dto.category.CreateCategoryRequestDto;
import com.example.onlinebookstore.service.book.BookService;
import com.example.onlinebookstore.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @Operation(summary = "Get all categories",
            description = "Get a list of available categories")
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<CategoryDto> getAll(@PageableDefault(size = 20, sort = "name",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(summary = "Create category", description = "Create a new category")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @Operation(summary = "Get category by id",
            description = "Get a category by ID")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable @Positive Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Update category by id",
            description = "Update a category by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto update(
            @RequestBody @Valid CreateCategoryRequestDto requestDto,
            @PathVariable @Positive Long id) {
        return categoryService.update(requestDto, id);
    }

    @Operation(summary = "Delete category by id",
            description = "Delete a category by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        categoryService.deleteById(id);
    }

    @Operation(summary = "Get book by category id",
            description = "Receive a list of books by category id")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @PathVariable @Positive Long id,
            @PageableDefault(size = 5) Pageable pageable) {
        return bookService.getBookByCategoryId(id, pageable);
    }
}
