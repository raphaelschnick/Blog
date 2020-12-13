package de.rapha.Blog.web;

import de.rapha.Blog.blog.Blog;
import de.rapha.Blog.blog.BlogService;
import de.rapha.Blog.category.Category;
import de.rapha.Blog.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;

    /**
     * Root request of web app.
     *
     * @param model the model of MVC pattern which is passsed to the view (html)
     * @return name of the view. the postfix '.html' is added by framework.
     */
    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String home(Model model) {

        List<Category> categoryList = categoryService.getList(true);
        List<Blog> blogList = blogService.getList(false);
        Blog newBlog = blogList.get(0);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("blogList", blogList);
        model.addAttribute("newBlog", newBlog);

        return "index";
    }
}
