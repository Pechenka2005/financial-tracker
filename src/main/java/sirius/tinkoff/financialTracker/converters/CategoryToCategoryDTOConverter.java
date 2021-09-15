package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Category;
import sirius.tinkoff.financialTracker.models.dto.CategoryDTO;

@Component
public class CategoryToCategoryDTOConverter {
    public CategoryDTO convert(Category category) {
        return new CategoryDTO()
                .setId(category.getId())
                .setIsIncome(category.getIsIncome())
                .setIconId(category.getIconId())
                .setIconColor(category.getIconColor())
                .setName(category.getName());
    }
}
