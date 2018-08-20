package egcom.yafi.packy;

import egcom.yafi.dto.ThreadDTO;
import egcom.yafi.dto.TopicDTO;
import egcom.yafi.dto.UserDTO;
import egcom.yafi.service.MainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/")
    public String greeting() {
        return "Welcome to YAF";
    }

    @PostMapping("/topic")
    public Long createTopic(@RequestBody TopicDTO topicDTO) {
        Long result = mainService.createTopic(topicDTO);

        return result;
    }

    @PostMapping("/user")
    public Long createUser(@RequestBody UserDTO userDTO) {
        Long result = mainService.createUser(userDTO);

        return result;
    }

    @PostMapping("/thread")
    public Long createThread(@RequestBody ThreadDTO threadDTO) {
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
