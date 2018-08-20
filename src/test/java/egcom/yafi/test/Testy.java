package egcom.yafi.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;


@TestPropertySource(locations="classpath:application-test.properties")
//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
//@Import(TestConfiguration.class)
//@SpringBootTest(classes = TestConfiguration.class)
//@DataJpaTest
public class Testy {

//    @Autowired
//    private UserRepo userRepo;

    @Test
    public void testy() {

        ApplicationContext context = new AnnotationConfigApplicationContext(egcom.yaf.test.TestConfiguration.class);

        Assert.assertNotNull(context);
    }
}
