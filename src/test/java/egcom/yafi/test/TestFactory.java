package egcom.yafi.test;

import egcom.yafi.dto.CreateTopicDTO;
import egcom.yafi.dto.CreateUserDTO;

public class TestFactory {

    public CreateUserDTO createUserDTO() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.username = "user";
        createUserDTO.password = "password";

        return createUserDTO;
    }

    public CreateTopicDTO createTopicDTO() {
        CreateTopicDTO createTopicDTO = new CreateTopicDTO();
        createTopicDTO.name = "topic1";

        return createTopicDTO;
    }
}
