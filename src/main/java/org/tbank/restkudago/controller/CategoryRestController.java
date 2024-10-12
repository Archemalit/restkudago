package org.tbank.restkudago.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.tbank.restkudago.controller.payload.category.NewCategoryPayload;
import org.tbank.restkudago.controller.payload.category.UpdateCategoryPayload;
import org.tbank.restkudago.model.Category;
import org.tbank.restkudago.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places/categories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{categoryId}")
    public Category findAllCategories(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody NewCategoryPayload info, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Category category = this.categoryService.createCategory(info.slug(), info.name());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/v1/places/categories/{categoryId}")
                            .build(category.getId()))
                    .body(category);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody UpdateCategoryPayload info, BindingResult bindingResult, @PathVariable Long categoryId) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.categoryService.updateCategory(categoryId, info.slug(), info.name());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
