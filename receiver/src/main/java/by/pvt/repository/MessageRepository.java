package by.pvt.repository;

import by.pvt.pojo.Message;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Cacheable(value = "message", key = "#idThing")
    @Query("from Message where id = (select max(id) from Message where idThing =:idThing)")
    Message findLastByIdThing(@Param("idThing") String idThing);

}
