package ba.sum.fsre.dshop.controller;

import ba.sum.fsre.dshop.dto.ProductDTO;
import ba.sum.fsre.dshop.model.Category;
import ba.sum.fsre.dshop.model.Product;
import ba.sum.fsre.dshop.model.Role;
import ba.sum.fsre.dshop.model.User;
import ba.sum.fsre.dshop.repositories.UserRepository;
import ba.sum.fsre.dshop.services.CategoryService;
import ba.sum.fsre.dshop.services.ProductService;
import ba.sum.fsre.dshop.services.RoleService;
import ba.sum.fsre.dshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @ModelAttribute("productDTO")
    public ProductDTO getProductDTO() {
        return new ProductDTO();
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category")Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }


    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        }
        else
            return "404";
    }

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProducts() );
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String productAddGet(Model model){
        model.addAttribute("productsDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Product product= new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else{
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/update/{id}")
    public String updateProductGet(@PathVariable long id, Model model){
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId((product.getCategory().getId()));
        productDTO.setWeight(product.getWeight());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);

        return "productsAdd";

    }
    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("activeLink", "Korisnici");
        return "users";
    }
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        User user = userService.getUserById(id).get();
        user.getRoles().clear();
        userRepository.save(user);
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/users/update/{id}")
    public String editUser(@PathVariable Integer id, Model model){
        User user = userService.getUserById(id).get();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "usersUpdate";
    }

    @Transactional
    @PostMapping("/admin/users/update")
    public String updateUserRole(@RequestParam Integer userId, @RequestParam Integer roleId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleService.getRoleById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().clear();
        user.getRoles().add(role);

        userRepository.save(user);

        return "redirect:/admin/users";
    }


}

