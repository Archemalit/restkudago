package org.tbank.restkudago.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tbank.restkudago.model.Category;
import org.tbank.restkudago.repository.CategoryRepository;
import org.tbank.restkudago.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.getById(categoryId).orElseThrow(() -> new NoSuchElementException("Category with id=" + categoryId + "not found!"));
    }

    @Override
    public Category createCategory(String slug, String name) {
        return categoryRepository.save(new Category(slug, name));
    }

    @Override
    public void updateCategory(Long id, String slug, String name) {
        Category category = getCategoryById(id);
        category.setSlug(slug);
        category.setName(name);
        categoryRepository.updateById(id, category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
