package by.pvt.repository;

import by.pvt.pojo.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Device device) {
        Session session = sessionFactory.getCurrentSession();
        session.save(device);
    }

    public Device getBySerialNumber(String serialNumber) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Device where serialNumber = :serialNumber ", Device.class)
                .setParameter("serialNumber", serialNumber)
                .uniqueResultOptional()
                .orElse(null);
    }


    public void update(Device device) {
        Session session = sessionFactory.getCurrentSession();
        session.update(device);
    }

}
