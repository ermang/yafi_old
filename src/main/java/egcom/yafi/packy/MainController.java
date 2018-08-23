package egcom.yafi.packy;

import egcom.yafi.dto.ThreadDTO;
import egcom.yafi.dto.TopicDTO;
import egcom.yafi.dto.UserDTO;
import egcom.yafi.dto.validator.ThreadDTOValidator;
import egcom.yafi.dto.validator.TopicDTOValidator;
import egcom.yafi.dto.validator.UserDTOValidator;
import egcom.yafi.service.MainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private final MainService mainService;
    private final UserDTOValidator userDTOValidator;
    private final TopicDTOValidator topicDTOValidator;
    private final ThreadDTOValidator threadDTOValidator;

    public MainController(MainService mainService, UserDTOValidator userDTOValidator, TopicDTOValidator topicDTOValidator,
                          ThreadDTOValidator threadDTOValidator) {
        this.mainService = mainService;
        this.userDTOValidator = userDTOValidator;
        this.topicDTOValidator = topicDTOValidator;
        this.threadDTOValidator = threadDTOValidator;
    }

    @RequestMapping("/")
    public String greeting() {
        return "Welcome to YAF";
    }

    @PostMapping("/topic")
    public Long createTopic(@RequestBody TopicDTO topicDTO) {
        topicDTOValidator.validate(topicDTO);
        Long result = mainService.createTopic(topicDTO);

        return result;
    }

    @PostMapping("/user")
    public Long createUser(@RequestBody UserDTO userDTO) {
        userDTOValidator.validate(userDTO);
        Long result = mainService.createUser(userDTO);

        return result;
    }

    @PostMapping("/thread")
    public Long createThread(@RequestBody ThreadDTO threadDTO) {
        threadDTOValidator.validate(threadDTO);
        Long result = mainService.createThread(threadDTO);

        return result;
    }

    @GetMapping("/topic/{topicName}")
    public List<ThreadDTO> readThreadsFromTopic(@PathVariable String topicName) {
        List<ThreadDTO> threadDTOs = mainService.readThreadsFromTopic(topicName);

        return threadDTOs;
    }

    @GetMapping("/topics")
    public List<TopicDTO> readTopics() {
        List<TopicDTO> topicDTOs = mainService.readTopics();

        return topicDTOs;
    }
}
