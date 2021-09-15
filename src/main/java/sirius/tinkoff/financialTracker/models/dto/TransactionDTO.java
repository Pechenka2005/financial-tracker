package sirius.tinkoff.financialTracker.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@Schema(description = "Транзакция")
public class TransactionDTO {
    private Long id;
    @Schema(description = "Сумма транзакции")
    private BigDecimal value;
    @Schema(description = "True если эта транзакция является доходом, false если расходом")
    private Boolean isIncome;
    @Schema(description = "Дата совершения транзакции")
    private Date ts;
    @Schema(description = "Валюта транзакции")
    private CurrencyDTO currency;
    @Schema(description = "Категория транзакции")
    private CategoryDTO category;
}
