package egcom.yafi.dto.validator;

import egcom.yafi.dto.CreateTopicDTO;
import egcom.yafi.dto.TopicDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateTopicDTOValidator {

    private final String topicDTO_can_not_be_null;
    private final String name_can_not_be_null;
    private final String name_can_not_be_empty;

    public CreateTopicDTOValidator() {
        topicDTO_can_not_be_null = "TopicDTO can not be null";
        name_can_not_be_null = "name can not be null";
        name_can_not_be_empty = "name can not be empty";
    }

    public void validate(CreateTopicDTO topicDTO) {
        if (topicDTO == null)
            throw new IllegalArgumentException(topicDTO_can_not_be_null);
        else if (topicDTO.name == null)
            throw new IllegalArgumentException(name_can_not_be_null);
        else if (topicDTO.name.isEmpty())
            throw new IllegalArgumentException(name_can_not_be_empty);
    }
}
