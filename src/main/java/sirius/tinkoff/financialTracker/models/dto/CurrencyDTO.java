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
@Schema(description = "Валюта")
public class CurrencyDTO {
    @Schema(description = "Полное название валюты")
    private String shortStr;
    @Schema(description = "Краткое название валюты")
    private String longStr;
    private String symbol;
}
