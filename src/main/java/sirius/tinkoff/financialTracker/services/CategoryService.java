package sirius.tinkoff.financialTracker.services;

import org.springframework.stereotype.Service;
import sirius.tinkoff.financialTracker.converters.CategoryToCategoryDTOConverter;
import sirius.tinkoff.financialTracker.converters.CreateCategoryDTOToCategoryConverter;
import sirius.tinkoff.financialTracker.exceptions.EntityNotFoundException;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.dto.CategoryDTO;
import sirius.tinkoff.financialTracker.models.dto.CreateCategoryDTO;
import sirius.tinkoff.financialTracker.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CreateCategoryDTOToCategoryConverter createCategoryConverter =
            new CreateCategoryDTOToCategoryConverter();
    private final CategoryToCategoryDTOConverter categoryConverter =
            new CategoryToCategoryDTOConverter();

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getCategory(Long id) {
        var found = categoryRepository.findById(id);
        return categoryConverter.convert(
                found
                        .orElseThrow(() -> new EntityNotFoundException("Category с таким id не был найден"))
        );
    }

    public List<CategoryDTO> listCategories(User user) {
        return categoryRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    public CategoryDTO createCategory(CreateCategoryDTO dto, User user) {
        var category = createCategoryConverter.convert(dto).setUserId(user.getId());
        return categoryConverter.convert(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(Long categoryId, CreateCategoryDTO dto) {
        var category = createCategoryConverter.convert(dto);
        category.setId(categoryId);
        return categoryConverter.convert(categoryRepository.save(category));
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
