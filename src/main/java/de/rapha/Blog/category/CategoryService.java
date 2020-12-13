package de.rapha.Blog.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category add(@NonNull Category category) {

        if(category.getName().isEmpty() || category.getName().isBlank()) {
            throw new IllegalArgumentException("Name must be set.");
        }
        if(category.getId() != null) {
            throw new IllegalArgumentException("This category exists already!");
        }

        return this.repository.save(category);
    }

    public Category get(@NonNull Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public List<Category> getList(Boolean asc) {

        List<Category> list;

        if(asc) {
            list = this.repository.findAll(Sort.by(Sort.Order.asc("name").ignoreCase()));
        } else {
            list = this.repository.findAll(Sort.by(Sort.Order.desc("name").ignoreCase()));
        }

        return list;
    }

    public Category update(@NonNull Category category) {
        return this.repository.save(category);
    }

    public void delete(@NonNull Category category) {
        this.repository.delete(category);
    }
}
