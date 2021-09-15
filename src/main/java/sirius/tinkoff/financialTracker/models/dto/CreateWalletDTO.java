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
@Schema(description = "Кошелек")
public class CreateWalletDTO {
    @Schema(description = "Лимит на траты для кошелька")
    private Long limit;
    @Schema(description = "Название кошелька")
    private String name;
    @Schema(description = "Краткое название валюты (например RUB)")
    private String currencyShortStr;
    @Schema(description = "Флаг, который скырвает кошелек")
    private boolean hidden;
}
