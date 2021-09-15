package sirius.tinkoff.financialTracker.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@Schema(description = "Кошелек")
public class WalletDTO {
    private Long id;
    @Schema(description = "Лимит на траты для кошелька")
    private Long limit;
    @Schema(description = "Название кошелька")
    private String name;
    @Schema(description = "Валюта кошелька")
    private CurrencyDTO currency;
    @Schema(description = "Баланс кошелька (все доходы минус все расходы)")
    private Integer balance;
    @Schema(description = "Флаг, который говорит, скрыт ли кошелек")
    private boolean hidden;

}
