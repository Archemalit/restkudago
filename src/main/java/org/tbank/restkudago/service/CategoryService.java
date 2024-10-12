package org.tbank.restkudago.service;

import org.tbank.restkudago.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(String slug, String name);
    void updateCategory(Long id, String slug, String name);
    void deleteCategory(Long id);
}
