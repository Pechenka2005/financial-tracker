package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.dto.CreateUserDTO;

@Component
public class CreateUserDTOToUserConverter {
    public User convert(CreateUserDTO dto) {
        return new User()
                .setUsername(dto.getUsername());
    }
}
