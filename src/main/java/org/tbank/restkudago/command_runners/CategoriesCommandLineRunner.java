package org.tbank.restkudago.command_runners;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tbank.restkudago.clients.impl.RestClientCategories;
import org.tbank.restkudago.model.Category;
import org.tbank.restkudago.repository.impl.ConcurentHashMapCategoryRepositoryImpl;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesCommandLineRunner implements CommandLineRunner {
    private final RestClientCategories restClientCategories;
    private final ConcurentHashMapCategoryRepositoryImpl categoryRepository;

    @Override
    public void run(String... args) {
        List<Category> categories = restClientCategories.findAllCategories("ru", "slug", List.of("id", "slug", "name"));
        categoryRepository.initCategories(categories);
    }
}
