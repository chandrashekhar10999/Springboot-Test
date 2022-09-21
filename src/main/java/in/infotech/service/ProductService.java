package in.infotech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import in.infotech.model.Category;
import in.infotech.model.Product;
import in.infotech.repository.CategoryRepository;
import in.infotech.repository.ProductsRepository;

@Service
public class ProductService {

	@Autowired
	private ProductsRepository pr;
	@Autowired
	private CategoryRepository cr;

	// METHOD TO GET ALL PRODUCTS
	public List<Product> getAllProducts(Integer page) {
		Pageable paging = PageRequest.ofSize(page);
		Page<Product> pagedResult = this.pr.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Product>();
		}
	}

	// METHOD TO GET PRODUCT BY ID
	public Product getProductsyById(Integer id) {
		return this.pr.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found !");
		});
	}

	// METHOD TO UPDATE PRODUCT BY ID
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product requestedProduct) {
		return this.pr.findById(id).map(product -> {
			product.setName(requestedProduct.getName());
			product.setPrice(requestedProduct.getPrice());
			return this.pr.save(product);
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		});
	}

	// METHOD TO DELETE PRODUCT BY ID
	public void deleteProductsById(Integer id) {
		this.pr.deleteById(id);
	}
}
