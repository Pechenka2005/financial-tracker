package sirius.tinkoff.financialTracker.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "is_income", nullable = false)
    private Boolean isIncome;

    @Column(name = "icon_id", nullable = false)
    private Long iconId;

    @Column(name = "icon_color", nullable = false)
    private String iconColor;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
