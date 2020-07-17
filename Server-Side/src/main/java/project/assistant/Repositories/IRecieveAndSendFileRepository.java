package project.assistant.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.assistant.Model.Beans.RecieveAndSendFileBean;


@Repository
public interface IRecieveAndSendFileRepository extends CrudRepository<RecieveAndSendFileBean,Long> {

}
