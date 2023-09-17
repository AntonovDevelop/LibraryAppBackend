package client.data.repository;

import client.data.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_ItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = "select * from order_item oi where oi.order_fk = :orderId", nativeQuery = true)
    List<OrderItem> findByOrderId(Long orderId);
}
