package egcom.yaf.test;

import egcom.yaf.packy.Application;
import egcom.yaf.repo.UserRepo;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;


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

        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        Assert.assertNotNull(context);
    }
}
