package by.pvt.repository;

import by.pvt.pojo.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class MessageRepository {

    @Autowired
    SessionFactory sessionFactory;



    public List<Message> getNotRead() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Message where isRead = false ",Message.class).list();
    }

    public void update(Message message) {
        Session session = sessionFactory.getCurrentSession();
                session.update(message);

    }


}
