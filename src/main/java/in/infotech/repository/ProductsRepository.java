package in.infotech.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.infotech.model.Product;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {

}
