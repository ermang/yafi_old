package egcom.yafi.packy;

import egcom.yafi.dto.*;
import egcom.yafi.dto.validator.CreateThreadDTOValidator;
import egcom.yafi.dto.validator.CreateTopicDTOValidator;
import egcom.yafi.dto.validator.CreateUserDTOValidator;
import egcom.yafi.service.MainService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //TODO: FIXME
public class MainController {

    private final MainService mainService;
    private final CreateUserDTOValidator createUserDTOValidator;
    private final CreateTopicDTOValidator createTopicDTOValidator;
    private final CreateThreadDTOValidator createThreadDTOValidator;

    public MainController(MainService mainService, CreateUserDTOValidator createUserDTOValidator,
                          CreateTopicDTOValidator createTopicDTOValidator,
                          CreateThreadDTOValidator createThreadDTOValidator) {
        this.mainService = mainService;
        this.createUserDTOValidator = createUserDTOValidator;
        this.createTopicDTOValidator = createTopicDTOValidator;
        this.createThreadDTOValidator = createThreadDTOValidator;
    }

    @RequestMapping("/")
    public String greeting() {
        return "Welcome to YAFI";
    }

    @PostMapping("/login")
    public boolean login() {
        return true;
    }

    @PostMapping("/topic")
    public Long createTopic(@RequestBody CreateTopicDTO createTopicDTO) {
        createTopicDTOValidator.validate(createTopicDTO);
        Long result = mainService.createTopic(createTopicDTO);

        return result;
    }

    @PostMapping("/user")
    public Long createUser(@RequestBody CreateUserDTO createUserDTO) {
        createUserDTOValidator.validate(createUserDTO);
        Long result = mainService.createUser(createUserDTO);

        return result;
    }

    @PostMapping("/thread")
    public Long createThread(@RequestBody CreateThreadDTO createThreadDTO) {
        createThreadDTOValidator.validate(createThreadDTO);
        Long result = mainService.createThread(createThreadDTO);

        return result;
    }

    @PostMapping("/likethread/{threadId}")
    public Long likeThread(@PathVariable Long threadId) {
        Long result = mainService.likeThread(threadId);

        return result;
    }

    @GetMapping("/topic/{topicName}")
    public ThreadPageDTO readThreadsFromTopic(@PathVariable String topicName, @RequestParam("page") int page) {
        ThreadPageDTO threadDTOs = mainService.readThreadsFromTopic(topicName,  PageRequest.of(page, 2));

        return threadDTOs;
    }

    @GetMapping("/thread/{username}")
    public ThreadPageDTO readThreadsFromUser(@PathVariable String username, @RequestParam("page") int page) {
        ThreadPageDTO threadPageDTO= mainService.readThreadsFromUser(username, PageRequest.of(page, 2));

        return threadPageDTO;
    }

    @GetMapping("/topics")
    public List<TopicDTO> readTopics() {
        List<TopicDTO> topicDTOs = mainService.readTopics();

        return topicDTOs;
    }

    @GetMapping("/topics/recent")
    public List<TopicDTO> readMostRecentlyUpdatedTopics() {
        List<TopicDTO> topicDTOs = mainService.readMostRecentlyUpdatedTopics();

        return topicDTOs;
    }

    @GetMapping("/threads/recent")
    public ThreadPageDTO readRecentThreads(@RequestParam("page") int page) {
        ThreadPageDTO threadPageDTO = mainService.readRecentThreads(PageRequest.of(page, 25));

        return  threadPageDTO;
    }
}
