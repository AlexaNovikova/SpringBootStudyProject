package ru.geekbrains.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.models.Category;
import ru.geekbrains.spring.models.Product;
import ru.geekbrains.spring.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // [http://localhost:8189/app]/
    @GetMapping("/")
    public String showAllProductsPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

//    @GetMapping("/find/{category_id}")
//    public String showProductsByCategory( @PathVariable(name = "category_id") Long id, Model model) {
//        List<Product> products = productService.findProductsByCategory(id);
//        model.addAttribute("products", products);
//        return "productsByCategory";
//    }

    @GetMapping("/find")
    public String showProductsByCategory( @RequestParam Long id, Model model) {
        List<Product> products = productService.findProductsByCategory(id);
        model.addAttribute("products", products);
        return "productsByCategory";
    }

    @GetMapping("/{id}")
    public String showProductInfo(@PathVariable(name = "id") Long id, Model model) {
        Optional<Product> product = productService.findOneById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        return "product_info";
    }


    @GetMapping("/create")
    public String showCreator() {
        return "create_product_form";
    }


        @PostMapping("/create")
       public String createNewProduct(@ModelAttribute Product product, @RequestParam String category_name) {
        productService.save(product, category_name);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
       productService.deleteById(id);
        return "redirect:/";
  }

    @PostMapping("/search")
    public String searchProduct(@RequestParam Long id, Model model) {
       return showProductInfo(id, model);
    }

}

