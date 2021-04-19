package ru.geekbrains.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    // [http://localhost:8189/app]/students/create?id=10&name=Nicolas&score=95
//    @PostMapping("/create")
//    public String createNewStudent(@RequestParam Long id, @RequestParam String name, @RequestParam int score) {
//        Student student = new Student(id, name, score);
//        studentService.save(student);
//        return "redirect:/students/all";
//    }

        @PostMapping("/create")
       public String createNewProduct(@ModelAttribute Product product) {
        productService.save(product);
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

    @GetMapping("/incPrice/{id}")
    public String incScore(@PathVariable Long id)
    {
        productService.incPrice(id);
        return "redirect:/";
    }

    @GetMapping("/decPrice/{id}")
    public String decScore(@PathVariable Long id)
    {
        productService.decPrice(id);
        return "redirect:/";
    }
}

