package egcom.yafi.service;

import egcom.yafi.dto.ThreadDTO;
import egcom.yafi.dto.TopicDTO;
import egcom.yafi.dto.UserDTO;
import egcom.yafi.entity.Thread;
import egcom.yafi.entity.Topic;
import egcom.yafi.entity.User;
import egcom.yafi.packy.Role;
import egcom.yafi.repo.ThreadRepo;
import egcom.yafi.repo.TopicRepo;
import egcom.yafi.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        User user = userRepo.findByUsername(topicDTO.createdBy);
        topic.setUser(user);
        topic.setCreatedOn(LocalDateTime.now());
        topic = topicRepo.save(topic);

        return topic.getId();
    }

    public Long createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.password);
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setRole(Role.USER.getValue());
        user.setCreatedOn(LocalDateTime.now());
        user = userRepo.save(user);

        return user.getId();
    }

    public Long createThread(ThreadDTO threadDTO) {
        User user = userRepo.findByUsername(threadDTO.username);
        Optional<Topic> topic = topicRepo.findByName(threadDTO.topicName);
        Thread thread = new Thread();
        thread.setContent(threadDTO.content);
        thread.setUser(user);
        thread.setTopic(topic.orElseThrow( () -> new RuntimeException("Topic with name " + threadDTO.topicName + " does not exist")));
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
            threadDTO.topicName = t.getTopic().getName();
            threadDTOs.add(threadDTO);
        }

        return threadDTOs;
    }

    public List<TopicDTO> readTopics() {
        List<Topic> topics = topicRepo.findAll();

        ArrayList<TopicDTO> topicDTOs = new ArrayList<>();
        for (Topic t: topics) {
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.name = t.getName();
            topicDTO.createdBy = t.getUser().getUsername();
            topicDTOs.add(topicDTO);
        }

        return topicDTOs;
    }
}
