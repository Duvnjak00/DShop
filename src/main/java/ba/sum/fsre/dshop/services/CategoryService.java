package ba.sum.fsre.dshop.services;

import ba.sum.fsre.dshop.model.Category;
import ba.sum.fsre.dshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    public void addCategory(Category category){
        categoryRepo.save(category);
    }
    public void removeCategoryById(int id){
        categoryRepo.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id){
        return categoryRepo.findById(id);
    }

}
