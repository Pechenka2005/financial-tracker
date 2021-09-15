package sirius.tinkoff.financialTracker.models;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
@Getter
@Setter
@Accessors(chain = true)
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "short_str", unique = true, nullable = false)
    private String shortStr;

    @Column(name = "long_str", unique = true, nullable = false)
    private String longStr;

    @Column(name = "coeff", nullable = false)
    private BigDecimal coeff;

    @Column(name = "symbol")
    private String symbol;
}
