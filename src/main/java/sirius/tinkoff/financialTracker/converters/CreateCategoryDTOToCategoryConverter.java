package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.Category;
import sirius.tinkoff.financialTracker.models.dto.CreateCategoryDTO;

@Component
public class CreateCategoryDTOToCategoryConverter {
    public Category convert(CreateCategoryDTO dto) {
        return new Category()
                .setIsIncome(dto.getIsIncome())
                .setIconColor(dto.getIconColor())
                .setIconId(dto.getIconId())
                .setName(dto.getName());
    }
}
