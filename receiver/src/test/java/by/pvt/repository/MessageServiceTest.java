package by.pvt.repository;


import by.pvt.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    MessageRepository messageRepository;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFindLast() {
        System.out.println(messageRepository.findLastByIdThing("conditioner0001"));
    }
}
