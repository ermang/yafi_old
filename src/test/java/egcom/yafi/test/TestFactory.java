package egcom.yafi.test;

import egcom.yafi.dto.CreateTopicDTO;
import egcom.yafi.dto.TopicDTO;
import egcom.yafi.dto.UserDTO;

public class TestFactory {

    public UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.username = "user";
        userDTO.password = "password";

        return userDTO;
    }

    public CreateTopicDTO createTopicDTO() {
        CreateTopicDTO createTopicDTO = new CreateTopicDTO();
        createTopicDTO.name = "topic1";

        return createTopicDTO;
    }
}
