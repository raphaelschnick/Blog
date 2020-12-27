package de.rapha.Blog.blog;

import de.rapha.Blog.category.Category;
import de.rapha.Blog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private final BlogRepository repository;

    public BlogService(BlogRepository repository) {
        this.repository = repository;
    }

    /**
     * Add new Blog
     *
     * @param blog to add.
     * @return new Blog in database
     */

    public Blog add(@NonNull Blog blog) {

        if(blog.getName().isEmpty() || blog.getName().isBlank()) {
            throw new IllegalArgumentException("Name must be set.");
        }
        if(blog.getId() != null) {
            throw new IllegalArgumentException("This blog exists already!");
        }

        return this.repository.save(blog);
    }

    public Blog get(@NonNull Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public List<Blog> getList(@NonNull Boolean asc) {

        List<Blog> list;

        if(asc) {
            list = this.repository.findAll(Sort.by(Sort.Order.asc("date")));
        } else {
            list = this.repository.findAll(Sort.by(Sort.Order.desc("date")));
        }

        return list;
    }

    public List<Blog> getCategory(@NonNull Category category) {
        List<Blog> blogList = new ArrayList<>();

        for(Blog blog : this.getList(false)) {
            if(blog.getCategory().getId().equals(category.getId())) {
                blogList.add(blog);
            }
        }
        return blogList;
    }

    public List<Blog> getBlogsByUser(@NonNull User user) {
        List<Blog> blogList = new ArrayList<>();
        for (Blog blog : this.getList(false)) {
            if(blog.getUser().getId().equals(user.getId())) {
                blogList.add(blog);
            }
        }
        return blogList;
    }

    public Blog update(@NonNull Blog blog) {
        return this.repository.save(blog);
    }

    public void delete(@NonNull Blog blog) {
        this.repository.delete(blog);
    }
}
