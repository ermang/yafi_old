package egcom.yafi.dto.validator;

import egcom.yafi.dto.ThreadDTO;
import org.springframework.stereotype.Component;

@Component
public class ThreadDTOValidator {

    public void validate(ThreadDTO threadDTO) {
        if (threadDTO == null)
            throw new IllegalArgumentException("threadDTO can not be null");
        else if (threadDTO.username == null)
            throw new IllegalArgumentException("username can not be null");
        else if (threadDTO.username.isEmpty())
            throw new IllegalArgumentException("username can not be empty");
        else if (threadDTO.topicName == null)
            throw new IllegalArgumentException("topicName can not be null");
        else if (threadDTO.topicName.isEmpty())
            throw new IllegalArgumentException("topicName can not be empty");
        else if (threadDTO.content == null)
            throw new IllegalArgumentException("content can not be null");
        else if (threadDTO.content.isEmpty())
            throw new IllegalArgumentException("content can not be empty");
    }
}
