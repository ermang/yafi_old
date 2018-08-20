package egcom.yaf.service;

import egcom.yaf.dto.ThreadDTO;
import egcom.yaf.dto.TopicDTO;
import egcom.yaf.dto.UserDTO;
import egcom.yaf.entity.Thread;
import egcom.yaf.entity.Topic;
import egcom.yaf.entity.User;
import egcom.yaf.repo.ThreadRepo;
import egcom.yaf.repo.TopicRepo;
import egcom.yaf.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    private final TopicRepo topicRepo;
    private final UserRepo userRepo;
    private final ThreadRepo threadRepo;

    public MainService(TopicRepo topicRepo, UserRepo userRepo, ThreadRepo threadRepo) {
        this.topicRepo = topicRepo;
        this.userRepo = userRepo;
        this.threadRepo = threadRepo;
    }

    public Long createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setName(topicDTO.name);
        User user = userRepo.findByUsername(topicDTO.username);
        topic.setUser(user);
        topic.setCreatedOn(LocalDateTime.now());
        topic = topicRepo.save(topic);

        return topic.getId();
    }

    public Long createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username);
        user.setCreatedOn(LocalDateTime.now());
        user = userRepo.save(user);

        return user.getId();
    }

    public Long createThread(ThreadDTO threadDTO) {
        User user = userRepo.findByUsername(threadDTO.username);
        Optional<Topic> topic = topicRepo.findById(threadDTO.topicId);
        Thread thread = new Thread();
        thread.setContent(threadDTO.content);
        thread.setUser(user);
        thread.setTopic(topic.get());
        thread = threadRepo.save(thread);

        return thread.getId();
    }

    public List<ThreadDTO> readThreadsFromTopic(String topicName) {
        List<Thread> threads = threadRepo.findAllByTopicName(topicName);

        ArrayList<ThreadDTO> threadDTOs = new ArrayList<>();
        for (Thread t: threads) {
            ThreadDTO threadDTO = new ThreadDTO();
            threadDTO.content = t.getContent();
            threadDTO.username = t.getUser().getUsername();
            threadDTOs.add(threadDTO);
        }

        return threadDTOs;
    }
}
