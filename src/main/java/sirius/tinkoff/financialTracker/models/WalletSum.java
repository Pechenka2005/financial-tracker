package sirius.tinkoff.financialTracker.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class WalletSum {
    private BigDecimal income;
    private BigDecimal expenses;
}
