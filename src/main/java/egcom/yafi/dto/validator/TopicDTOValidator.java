package egcom.yafi.dto.validator;

import egcom.yafi.dto.TopicDTO;
import org.springframework.stereotype.Component;

@Component
public class TopicDTOValidator {

    private final String topicDTO_can_not_be_null;
    private final String createdBy_can_not_be_null;
    private final String createdBy_can_not_be_empty;
    private final String name_can_not_be_null;
    private final String name_can_not_be_empty;

    public TopicDTOValidator() {
        topicDTO_can_not_be_null = "TopicDTO can not be null";
        createdBy_can_not_be_null = "createdBy can not be null";
        createdBy_can_not_be_empty = "createdBy can not be empty";
        name_can_not_be_null = "name can not be null";
        name_can_not_be_empty = "name can not be empty";
    }

    public void validate(TopicDTO topicDTO) {
        if (topicDTO == null)
            throw new IllegalArgumentException(topicDTO_can_not_be_null);
        else if (topicDTO.createdBy == null)
            throw new IllegalArgumentException(createdBy_can_not_be_null);
        else if (topicDTO.createdBy.isEmpty())
            throw new IllegalArgumentException(createdBy_can_not_be_empty);
        else if (topicDTO.name == null)
            throw new IllegalArgumentException(name_can_not_be_null);
        else if (topicDTO.name.isEmpty())
            throw new IllegalArgumentException(name_can_not_be_empty);
    }
}
