package pl.wsei.storespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsei.storespring.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
