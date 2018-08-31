package egcom.yafi.dto.validator;

import egcom.yafi.dto.CreateUserDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDTOValidator {

    private final String username_can_not_be_null;
    private final String username_can_not_be_empty;

    public CreateUserDTOValidator() {
        username_can_not_be_null = "username can not be null";
        username_can_not_be_empty = "username can not be empty";
    }

    public void validate(CreateUserDTO userDTO) {
        if (userDTO.username == null)
            throw new IllegalArgumentException(username_can_not_be_null);
        else if (userDTO.username.isEmpty())
            throw new IllegalArgumentException(username_can_not_be_empty);
    }

}