package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.pojo.TacoOrder;


public interface OrderRepository extends CrudRepository<TacoOrder, String> {

}
