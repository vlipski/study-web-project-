package by.pvt.repository;



import by.pvt.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
public class UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Long save(User user) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(user);

    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public User getUserByLogin(String login){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where login = :login ", User.class)
                .setParameter("login", login)
                .uniqueResultOptional()
                .orElse(null);
    }

    public User get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }
}
