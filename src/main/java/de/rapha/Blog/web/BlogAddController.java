package de.rapha.Blog.web;

import de.rapha.Blog.blog.Blog;
import de.rapha.Blog.blog.BlogService;
import de.rapha.Blog.blog.FileUploadUtil;
import de.rapha.Blog.category.Category;
import de.rapha.Blog.category.CategoryService;
import de.rapha.Blog.user.User;
import de.rapha.Blog.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
public class BlogAddController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {

        Blog blog = new Blog();

        List<Category> categoryList = categoryService.getList(true);

        model.addAttribute("blog", blog);
        model.addAttribute("categoryList", categoryList);

        return "blogadd";
    }

    @PostMapping("/blog/add")
    public String submit(@ModelAttribute("blog") Blog blog,
                         @RequestParam("image") MultipartFile multipartFile) throws IOException {

        User user = userService.get(2L);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        blog.setImage(fileName);
        blog.setDate(new Date());
        blog.setUser(user);

        Blog result = blogService.add(blog);

        String uploadDir = "blog/" + result.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "blog";
    }
}
