package tacos.data;

import tacos.pojo.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
