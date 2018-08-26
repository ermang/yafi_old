package egcom.yafi.test;

import egcom.yafi.dto.*;
import egcom.yafi.packy.ActiveUserResolver;
import egcom.yafi.service.MainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
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

        CreateTopicDTO topicDTO = testFactory.createTopicDTO();
        Long result = service.createTopic(topicDTO);

        Assert.assertNotNull(result);
    }

    @Test
    public void create_user_and_topic_read_topics() {
        UserDTO userDTO = testFactory.userDTO();
        service.createUser(userDTO);

        CreateTopicDTO topicDTO = testFactory.createTopicDTO();

        CreateTopicDTO topicDTO2 = new CreateTopicDTO();

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

        CreateTopicDTO topicDTO = testFactory.createTopicDTO();
        service.createTopic(topicDTO);

        CreateThreadDTO createThreadDTO = new CreateThreadDTO();
        createThreadDTO.content = "this da content";
        createThreadDTO.topicName = "topic1";

        Long result =service.createThread(createThreadDTO);

        Assert.assertNotNull(result);
    }

}