package sirius.tinkoff.financialTracker.converters;

import org.springframework.stereotype.Component;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.dto.UserDTO;

@Component
public class UserToUserDTOConverter {
    public UserDTO convert(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername());
    }
}
