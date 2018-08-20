package egcom.yaf.packy;

import egcom.yaf.dto.ThreadDTO;
import egcom.yaf.dto.TopicDTO;
import egcom.yaf.dto.UserDTO;
import egcom.yaf.service.MainService;
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
}
