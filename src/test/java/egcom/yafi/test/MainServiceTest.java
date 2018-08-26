package egcom.yafi.test;

import egcom.yafi.dto.ThreadDTO;
import egcom.yafi.dto.TopicDTO;
import egcom.yafi.dto.UserDTO;
import egcom.yafi.service.MainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
public class MainServiceTest {

    private final TestFactory testFactory;

    public MainServiceTest() {
        this.testFactory = new TestFactory();
    }

    @Autowired
    private MainService service;

    @Test
    public void create_user()  {
        UserDTO userDTO = testFactory.userDTO();
        Long result = service.createUser(userDTO);

        Assert.assertNotNull(result);
    }

    @Test
    public void create_user_and_topic() {
        UserDTO userDTO = testFactory.userDTO();
        service.createUser(userDTO);

        TopicDTO topicDTO = testFactory.topicDTO();
        Long result = service.createTopic(topicDTO);

        Assert.assertNotNull(result);
    }

    @Test
    public void create_user_and_topic_read_topics() {
        UserDTO userDTO = testFactory.userDTO();
        service.createUser(userDTO);

        TopicDTO topicDTO = testFactory.topicDTO();

        TopicDTO topicDTO2 = new TopicDTO();
        topicDTO2.createdBy = "root";
        topicDTO2.name = "topic2";

        service.createTopic(topicDTO);
        service.createTopic(topicDTO2);

        List<TopicDTO> actual = service.readTopics();

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void create_user_and_topic_and_thread() {
        UserDTO userDTO = testFactory.userDTO();
        service.createUser(userDTO);

        TopicDTO topicDTO = testFactory.topicDTO();
        service.createTopic(topicDTO);

        ThreadDTO threadDTO = new ThreadDTO();
        threadDTO.content = "this da content";
        threadDTO.username = "root";
        threadDTO.topicName = "topic1";

        Long result =service.createThread(threadDTO);

        Assert.assertNotNull(result);
    }

}