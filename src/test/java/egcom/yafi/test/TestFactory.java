package egcom.yafi.test;

import egcom.yafi.dto.TopicDTO;
import egcom.yafi.dto.UserDTO;

public class TestFactory {

    public UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.username = "root";
        userDTO.password = "root";

        return userDTO;
    }

    public TopicDTO topicDTO() {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.name = "topic1";
        topicDTO.createdBy = "root";

        return topicDTO;
    }
}
