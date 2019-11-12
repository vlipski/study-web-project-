package by.pvt.repository;


import by.pvt.pojo.SensorValue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class SensorValueRepository {

    @Autowired
    SessionFactory sessionFactory;


    public List<SensorValue> getSensorValuesByIdAndDate(Long id, Date from, Date to) {
        Session session = sessionFactory.getCurrentSession();
        return session.
                createQuery("from SensorValue where sensor.id = :id and date between" +
                                "(select coalesce(max(date),:from) from SensorValue where sensor.id = :id and date < :from)" +
                                "and (select coalesce(min(date), :to) from SensorValue where sensor.id = :id and date > :to)",
                        SensorValue.class)
                .setParameter("id", id)
                .setParameter("from", from)
                .setParameter("to", to)
                .list();
    }

    public void save(SensorValue sensorValue) {
        sessionFactory.getCurrentSession().save(sensorValue);
    }

}
