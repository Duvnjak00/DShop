package ba.sum.fsre.dshop.services;

import ba.sum.fsre.dshop.model.Product;
import ba.sum.fsre.dshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    public void addProduct(Product product){
        productRepo.save(product);
    }
    public void removeProductById(long id){
        productRepo.deleteById(id);
    }
    public Optional<Product> getProductById(long id){
        return productRepo.findById(id);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return productRepo.findAllByCategory_Id(id);
    }
}
