package sirius.tinkoff.financialTracker.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.dto.CategoryDTO;
import sirius.tinkoff.financialTracker.models.dto.CreateCategoryDTO;
import sirius.tinkoff.financialTracker.services.CategoryService;

import java.util.List;

@RequestMapping("/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> listCategories(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return categoryService.listCategories(user);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO createCategory(@RequestBody CreateCategoryDTO dto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return categoryService.createCategory(dto, user);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO updateCategory(@RequestBody CreateCategoryDTO dto, @PathVariable Long id) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
