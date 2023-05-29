package rikkei.academy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.model.Product;
import rikkei.academy.service.IProductService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "product"})
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String showListProduct(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("listProduct", productList);
        model.addAttribute("message", "<button>ok</button>");
        return "product/list";
    }

    @GetMapping("/{id}")
    public String detailProduct(@PathVariable long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("productDetail", product);
        return "product/detail";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Product product = new Product();
        model.addAttribute("productCreate", product);
        return "product/create";
    }

    @PostMapping("/create")
    public String actionCreate(Product product) {
        long id = 0;
        if (productService.findAll().size() == 0) {
            id = 1;
        } else {
            id = productService.findAll().get(productService.findAll().size() - 1).getId() + 1;
        }
        product.setId(id);
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("productEdit", product);
        return "product/edit";
    }

    @PostMapping("/edit")
    public String actionEdit(Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String showFormDelete(@PathVariable long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("productDelete", product);
        return "product/delete";
    }

    @PostMapping("/delete")
    public String actionDelete(Product product) {
        productService.deleteById(product.getId());
        return "redirect:/";
    }
}
