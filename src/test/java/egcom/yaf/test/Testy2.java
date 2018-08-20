package egcom.yaf.test;

import egcom.yaf.entity.User;
import egcom.yaf.packy.Application;
import egcom.yaf.packy.MainConfiguration;
import egcom.yaf.repo.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestConfig2.class)
//@SpringBootTest(classes = {Application.class, MainConfiguration.class, UserRepo.class})
public class Testy2 {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo repository;

    @Test
    public void testExample()  {
        User user = new User();
        user.setUsername("osman");
        repository.save(user);
        Assert.assertNotNull(repository.findByUsername("osman"));
    }




    }
