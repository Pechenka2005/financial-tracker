package sirius.tinkoff.financialTracker.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sirius.tinkoff.financialTracker.converters.CreateUserDTOToUserConverter;
import sirius.tinkoff.financialTracker.converters.UserToUserDTOConverter;
import sirius.tinkoff.financialTracker.exceptions.EntityNotFoundException;
import sirius.tinkoff.financialTracker.exceptions.ExistingUserException;
import sirius.tinkoff.financialTracker.models.Category;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.dto.CreateUserDTO;
import sirius.tinkoff.financialTracker.models.dto.UserDTO;
import sirius.tinkoff.financialTracker.repository.CategoryRepository;
import sirius.tinkoff.financialTracker.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserToUserDTOConverter userConverter = new UserToUserDTOConverter();
    private final CreateUserDTOToUserConverter createUserConverter = new CreateUserDTOToUserConverter();

    public List<UserDTO> findAll() {
        return userRepository
                .findAllByOrderByIdDesc()
                .stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        Optional<UserDTO> foundUser = userRepository.findById(id).map(userConverter::convert);
        if (foundUser.isEmpty())
            throw new EntityNotFoundException("User с таким id не был найден");
        return foundUser.get();
    }

    public UserDTO createUser(CreateUserDTO dto) throws Exception {
        var foundUser = userRepository.findByUsername(dto.getUsername());
        if (foundUser.isPresent()) {
            throw new ExistingUserException(
                    "this user already exists: " +
                            new ObjectMapper().writeValueAsString(userConverter.convert(foundUser.get())));
        }
        User user = userRepository.save(createUserConverter.convert(dto));

        // create default categories
        var DEFAULT_CATEGORIES = List.of(
                new Category().setIconId(3L).setIconColor("#7765C0").setIsIncome(false).setName("Кафе и рестораны"),
                new Category().setIconId(0L).setIconColor("#339FEE").setIsIncome(false).setName("Супермаркеты"),
                new Category().setIconId(10L).setIconColor("#994747").setIsIncome(false).setName("Спортзал"),
                new Category().setIconId(8L).setIconColor("#EE33BA").setIsIncome(false).setName("Общественный транспорт"),
                new Category().setIconId(4L).setIconColor("#16DC71").setIsIncome(false).setName("Медицина"),
                new Category().setIconId(30L).setIconColor("#EEA333").setIsIncome(false).setName("Бензин"),
                new Category().setIconId(31L).setIconColor("#91397D").setIsIncome(false).setName("Квартплата"),
                new Category().setIconId(13L).setIconColor("#EEDB33").setIsIncome(false).setName("Отпуск"),
                new Category().setIconId(32L).setIconColor("#00B92D").setIsIncome(true).setName("Зарплата"),
                new Category().setIconId(32L).setIconColor("#00B92D").setIsIncome(true).setName("Подработка"),
                new Category().setIconId(17L).setIconColor("#00B92D").setIsIncome(true).setName("Подарок"),
                new Category().setIconId(32L).setIconColor("#00B92D").setIsIncome(true).setName("Капитализация")
        );

        for (Category category : DEFAULT_CATEGORIES) {
            category.setUserId(user.getId());
            categoryRepository.save(category);
        }

        return userConverter.convert(user);
    }

    public UserDTO updateUser(CreateUserDTO dto, User user) {
        user.setUsername(dto.getUsername());
        userRepository.save(user);
        return userConverter.convert(user);
    }
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
