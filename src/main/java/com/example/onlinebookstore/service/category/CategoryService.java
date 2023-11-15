package com.example.onlinebookstore.service.category;

import com.example.onlinebookstore.dto.category.CategoryDto;
import com.example.onlinebookstore.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryRequestDto categoryDto);

    CategoryDto update(CreateCategoryRequestDto categoryDto, Long id);

    void deleteById(Long id);
}
