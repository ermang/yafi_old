package egcom.yafi.packy;

import egcom.yafi.dto.*;
import egcom.yafi.dto.validator.CreateThreadDTOValidator;
import egcom.yafi.dto.validator.CreateTopicDTOValidator;
import egcom.yafi.dto.validator.UserDTOValidator;
import egcom.yafi.service.MainService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //TODO: FIXME
public class MainController {

    private final MainService mainService;
    private final UserDTOValidator userDTOValidator;
    private final CreateTopicDTOValidator createTopicDTOValidator;
    private final CreateThreadDTOValidator createThreadDTOValidator;

    public MainController(MainService mainService, UserDTOValidator userDTOValidator, CreateTopicDTOValidator createTopicDTOValidator,
                          CreateThreadDTOValidator createThreadDTOValidator) {
        this.mainService = mainService;
        this.userDTOValidator = userDTOValidator;
        this.createTopicDTOValidator = createTopicDTOValidator;
        this.createThreadDTOValidator = createThreadDTOValidator;
    }

    @RequestMapping("/")
    public String greeting() {
        return "Welcome to YAFI";
    }

    @PostMapping("/topic")
    public Long createTopic(@RequestBody CreateTopicDTO createTopicDTO) {
        createTopicDTOValidator.validate(createTopicDTO);
        Long result = mainService.createTopic(createTopicDTO);

        return result;
    }

    @PostMapping("/user")
    public Long createUser(@RequestBody UserDTO userDTO , Authentication authentication, @AuthenticationPrincipal User activeUser) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        userDTOValidator.validate(userDTO);
        Long result = mainService.createUser(userDTO);

        return result;
    }

    @PostMapping("/thread")
    public Long createThread(@RequestBody CreateThreadDTO createThreadDTO) {
        createThreadDTOValidator.validate(createThreadDTO);
        Long result = mainService.createThread(createThreadDTO);

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
