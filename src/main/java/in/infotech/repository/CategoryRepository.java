package in.infotech.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.infotech.model.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}