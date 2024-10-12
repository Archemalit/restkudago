package org.tbank.restkudago.repository.impl;

import org.springframework.stereotype.Repository;
import org.tbank.restkudago.model.Category;
import org.tbank.restkudago.repository.CategoryRepository;

import java.util.List;

@Repository
public class ConcurentHashMapCategoryRepositoryImpl extends ConcurentHashMap<Category> implements CategoryRepository {

    @Override
    public void initCategories(List<Category> categories) {
        for (Category category : categories) {
            this.map.put(category.getId(), category);
            this.lastIndex = Math.max(lastIndex, category.getId() + 1);
        }
    }

    @Override
    public Category save(Category element) {
        element.setId(lastIndex);
        this.map.put(lastIndex++, element);
        return element;
    }
}
