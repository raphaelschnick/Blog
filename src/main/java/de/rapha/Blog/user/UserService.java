package de.rapha.Blog.user;

import de.rapha.Blog.blog.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Add new User
     *
     * @param user to add
     * @return User in Database
     */
    public User add(@NonNull User user) {
        LOG.debug("-> add() user={}", user);

        if(user.getName().isEmpty() || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name must be set.");
        }

        User result = this.repository.save(user);

        LOG.debug("<- add() result={}", result);
        return result;
    }

    public User get(@NonNull Long id) {
        LOG.debug("-> get() id={}", id);

        User result = this.repository.findById(id).orElseThrow();

        LOG.debug("<- get() result{}", result);

        return result;
    }

    public List<User> getList(Boolean asc) {

        List<User> list;

        if(asc) {
            list = this.repository.findAll(Sort.by(Sort.Order.asc("name").ignoreCase()));
        } else {
            list = this.repository.findAll(Sort.by(Sort.Order.desc("name").ignoreCase()));
        }

        return list;
    }

    public User update(@NonNull User user) {
        LOG.debug("-> update() user={}", user);

        User result = this.repository.save(user);

        LOG.debug("<- update() result{}", result);
        return result;
    }

    public void delete(@NonNull User user) {
        this.repository.delete(user);
    }
}
