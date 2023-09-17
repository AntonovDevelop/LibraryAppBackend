package client.data.repository;

import client.data.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Book, Long> {
    Book findOneByNameIgnoreCase(String name);

    @Query(value = "select * from product p inner join category c on p.category_fk = c.id where c.name = :categoryName",  nativeQuery = true)
    List<Book> findProductsByCategory(String categoryName);

    @Query(value = "select * from product p inner join order_item oi on p.id = oi.product_fk inner join orders o on oi.order_fk = o.id inner join client c on o.client_fk = c.id where c.id = :id and o.id = :cartId",  nativeQuery = true)
    List<Book> findProductsByClient(Long id, Long cartId);

    @Query(value = "select * from product p inner join review r on p.id = r.product_fk inner join client c on r.client_fk = c.id where r.liked = 1 and c.id = :id",  nativeQuery = true)
    List<Book> findLikedProductsByClient(Long id);
}
