package de.rapha.Blog.web;

import de.rapha.Blog.blog.Blog;
import de.rapha.Blog.blog.BlogService;
import de.rapha.Blog.category.Category;
import de.rapha.Blog.category.CategoryService;
import de.rapha.Blog.user.User;
import de.rapha.Blog.user.UserService;
import de.rapha.Blog.utils.FileUploadUtil;
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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/user")
    public String getUserById(@RequestParam(name="id", required=false) Long id, Model model) {

        User user = userService.get(id);
        List<Blog> blogList = blogService.getBlogsByUser(user);
        List<Category> categoryList = categoryService.getList(true);

        model.addAttribute("user", user);
        model.addAttribute("blogList", blogList);
        model.addAttribute("categoryList", categoryList);

        return "user";
    }

    @PostMapping("/user")
    public String submit(@ModelAttribute("user") User user,
                         @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String uploadDir = "data/user/" + user.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "user" + user.getId();
    }
}
