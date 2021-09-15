package sirius.tinkoff.financialTracker.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@Schema(description = "Категория трат/доходов")
public class CategoryDTO {
    private Long id;
    @Schema(description = "True если это категория доходов, false если категория расходов")
    private Boolean isIncome;
    @Schema(description = "Id иконки категории")
    private Long iconId;
    @Schema(description = "Id цвета иконки")
    private String iconColor;
    @Schema(description = "Название категории")
    private String name;
}
