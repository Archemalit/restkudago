package org.tbank.restkudago.repository;

import org.tbank.restkudago.model.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category> {
    void initCategories(List<Category> categories);
}
