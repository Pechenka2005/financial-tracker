package sirius.tinkoff.financialTracker.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sirius.tinkoff.financialTracker.validators.ValidEmail;

@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@Schema(description = "Новый пользователь")
public class CreateUserDTO {
    @ValidEmail
    @Schema(description = "Email пользователя")
    private String username;
}
