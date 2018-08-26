package egcom.yafi.dto.validator;

import egcom.yafi.dto.CreateThreadDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateThreadDTOValidator {

    public void validate(CreateThreadDTO createThreadDTO) {
        if (createThreadDTO == null)
            throw new IllegalArgumentException("createThreadDTO can not be null");
        else if (createThreadDTO.topicName == null)
            throw new IllegalArgumentException("topicName can not be null");
        else if (createThreadDTO.topicName.isEmpty())
            throw new IllegalArgumentException("topicName can not be empty");
        else if (createThreadDTO.content == null)
            throw new IllegalArgumentException("content can not be null");
        else if (createThreadDTO.content.isEmpty())
            throw new IllegalArgumentException("content can not be empty");
    }
}
