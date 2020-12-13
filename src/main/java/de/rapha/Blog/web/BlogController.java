package de.rapha.Blog.web;

import de.rapha.Blog.blog.Blog;
import de.rapha.Blog.blog.BlogService;
import de.rapha.Blog.category.Category;
import de.rapha.Blog.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/blog")
    public String getBlogById(@RequestParam(name="id", required=false) Long id, Model model) {

        Blog blog = blogService.get(id);
        List<Category> categoryList = categoryService.getList(true);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("blog", blog);

        return "blog";
    }
}
