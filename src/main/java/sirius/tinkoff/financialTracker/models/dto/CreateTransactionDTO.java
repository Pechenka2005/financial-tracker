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
public class CreateTransactionDTO {
    @Schema(description = "Сумма транзакции")
    private BigDecimal value;

    @Schema(description = "True если эта транзакция является доходом, false если расходом")
    private Boolean isIncome;

    @Schema(description = "Дата совершения транзакции")
    private Date ts;

    @Schema(description = "Краткое название валюты (например RUB)")
    private String currencyShortStr;

    @Schema(description = "Id кошелька, к которому относится транзакция")
    private Long walletId;

    @Schema(description = "Id категории, к которой относится транзакция")
    private Long categoryId;
}
