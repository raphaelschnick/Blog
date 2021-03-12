package de.rapha.Blog.web;

import de.rapha.Blog.blog.Blog;
import de.rapha.Blog.blog.BlogService;
import de.rapha.Blog.category.Category;
import de.rapha.Blog.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{name}")
    public String getCategoryByName(@PathVariable(name = "name") String name, Model model) {

        List<Category> categoryList = categoryService.getList(true);
        Category category = categoryService.getByName(name);
        List<Blog> blogList = blogService.getCategory(category);
        Blog newBlog = blogList.get(0);

        model.addAttribute("category", category);
        model.addAttribute("blogList", blogList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("newBlog", newBlog);

        return "category";
    }
}
