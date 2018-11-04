package egcom.yafi.service;

import egcom.yafi.dto.*;
import egcom.yafi.entity.Thread;
import egcom.yafi.entity.Topic;
import egcom.yafi.entity.YafiUser;
import egcom.yafi.packy.ActiveUserResolver;
import egcom.yafi.packy.Role;
import egcom.yafi.repo.ThreadRepo;
import egcom.yafi.repo.TopicRepo;
import egcom.yafi.repo.YafiUserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    private final TopicRepo topicRepo;
    private final YafiUserRepo yafiUserRepo;
    private final ThreadRepo threadRepo;
    private final ActiveUserResolver activeUserResolver;

    public MainService(TopicRepo topicRepo, YafiUserRepo yafiUserRepo, ThreadRepo threadRepo, ActiveUserResolver activeUserResolver) {
        this.topicRepo = topicRepo;
        this.yafiUserRepo = yafiUserRepo;
        this.threadRepo = threadRepo;
        this.activeUserResolver = activeUserResolver;
    }

    public Long createTopic(CreateTopicDTO createTopicDTO) {
        Topic topic = new Topic();
        topic.setName(createTopicDTO.name);
        YafiUser yafiUser = yafiUserRepo.findByUsername(activeUserResolver.getActiveUser().getUsername());
        topic.setYafiUser(yafiUser);
        topic.setCreatedOn(LocalDateTime.now());
        topic = topicRepo.save(topic);

        return topic.getId();
    }

    public Long createUser(CreateUserDTO createUserDTO) {
        YafiUser yafiUser = new YafiUser();
        yafiUser.setUsername(createUserDTO.username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(createUserDTO.password);
        yafiUser.setPassword(encodedPassword);
        yafiUser.setEnabled(true);
        yafiUser.setRole(Role.USER.getValue());
        yafiUser.setCreatedOn(LocalDateTime.now());
        yafiUser = yafiUserRepo.save(yafiUser);

        return yafiUser.getId();
    }

    public Long createThread(CreateThreadDTO createThreadDTO) {
        YafiUser yafiUser = yafiUserRepo.findByUsername(activeUserResolver.getActiveUser().getUsername());
        Optional<Topic> topic = topicRepo.findByName(createThreadDTO.topicName);
        Thread thread = new Thread();
        thread.setContent(createThreadDTO.content);
        thread.setYafiUser(yafiUser);
        thread.setTopic(topic.orElseThrow( () -> new RuntimeException("Topic with name " + createThreadDTO.topicName + " does not exist")));
        thread.setLikeCount(0L);
        thread.setCreatedOn(LocalDateTime.now());
        thread = threadRepo.save(thread);

        return thread.getId();
    }

    public ThreadPageDTO readThreadsFromTopic(String topicName, PageRequest pageRequest) {
        Page<Thread> threads = threadRepo.findAllByTopicNameOrderByCreatedOnAsc(topicName, pageRequest);

        ArrayList<ThreadDTO> threadDTOs = new ArrayList<>();
        for (Thread t: threads) {
            ThreadDTO threadDTO = new ThreadDTO();
            threadDTO.id = t.getId();
            threadDTO.content = t.getContent();
            threadDTO.username = t.getYafiUser().getUsername();
            threadDTO.topicName = t.getTopic().getName();
            threadDTO.likeCount = t.getLikeCount();
            threadDTO.createdOn = t.getCreatedOn();
            threadDTOs.add(threadDTO);
        }

        ThreadPageDTO threadPageDTO = new ThreadPageDTO();
        threadPageDTO.threadDTOs = threadDTOs;
        threadPageDTO.totalPageCount = threads.getTotalPages();

        return threadPageDTO;
    }

    public ThreadPageDTO readThreadsFromUser(String username, PageRequest pageRequest) {
        Page<Thread> threads = threadRepo.findAllByYafiUserUsernameOrderByCreatedOnAsc(username, pageRequest);

        ArrayList<ThreadDTO> threadDTOs = new ArrayList<>();
        for (Thread t: threads) {
            ThreadDTO threadDTO = new ThreadDTO();
            threadDTO.id = t.getId();
            threadDTO.content = t.getContent();
            threadDTO.username = t.getYafiUser().getUsername();
            threadDTO.topicName = t.getTopic().getName();
            threadDTO.likeCount = t.getLikeCount();
            threadDTO.createdOn = t.getCreatedOn();
            threadDTOs.add(threadDTO);
        }

        ThreadPageDTO threadPageDTO = new ThreadPageDTO();
        threadPageDTO.threadDTOs = threadDTOs;
        threadPageDTO.totalPageCount = threads.getTotalPages();

        return threadPageDTO;
    }

    public List<TopicDTO> readTopics() {
        List<Topic> topics = topicRepo.findAll();

        ArrayList<TopicDTO> topicDTOs = new ArrayList<>();
        for (Topic t: topics) {
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.name = t.getName();
            topicDTO.createdBy = t.getYafiUser().getUsername();
            topicDTOs.add(topicDTO);
        }

        return topicDTOs;
    }

    public long likeThread(Long threadId) {
        Optional<Thread> thread = threadRepo.findById(threadId);

        if (!thread.isPresent())
            throw new RuntimeException("Thread with id " + threadId + " doesn not exist");
        else {
            Thread t = thread.get();
            t.setLikeCount(t.getLikeCount() + 1);
            threadRepo.save(t);

            return t.getLikeCount();
        }
    }

    public List<TopicDTO> readMostRecentlyUpdatedTopics() {
        List<Topic> topics = topicRepo.readMostRecentlyUpdatedTopics();

        ArrayList<TopicDTO> topicDTOs = new ArrayList<>();
        for (Topic t: topics) {
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.name = t.getName();
            topicDTO.createdBy = t.getYafiUser().getUsername();
            topicDTOs.add(topicDTO);
        }

        return topicDTOs;
    }

    public ThreadPageDTO readRecentThreads(PageRequest pageRequest) {
        Page<Thread> threads = threadRepo.findFirst25ByOrderByCreatedOn_Desc(pageRequest);

        ArrayList<ThreadDTO> threadDTOs = new ArrayList<>();
        for (Thread t: threads) {
            ThreadDTO threadDTO = new ThreadDTO();
            threadDTO.id = t.getId();
            threadDTO.content = t.getContent();
            threadDTO.username = t.getYafiUser().getUsername();
            threadDTO.topicName = t.getTopic().getName();
            threadDTO.likeCount = t.getLikeCount();
            threadDTO.createdOn = t.getCreatedOn();
            threadDTOs.add(threadDTO);
        }

        ThreadPageDTO threadPageDTO = new ThreadPageDTO();
        threadPageDTO.threadDTOs = threadDTOs;
        threadPageDTO.totalPageCount = threads.getTotalPages();

        return threadPageDTO;
    }
}
