package de.rapha.Blog.blog;

import de.rapha.Blog.category.Category;
import de.rapha.Blog.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")

    @Column(name ="id", nullable = false, unique = false)
    private Long id;

    @Column(name="name", nullable = false, unique = false)
    private String name;

    @Column(name="date", nullable = false, unique = false)
    private Date date;

    @Column(name="description", nullable = false, unique = false)
    private String description;

    @Column(name="text", nullable = false, unique = false)
    private String text;

    @Column(nullable = true, length = 64)
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false, unique = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false, unique = false)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDateAsString() {
        final String PATTERN = "dd.MM.yyyy";
        DateFormat df = new SimpleDateFormat(PATTERN);
        return df.format(this.getDate());
    }

    @Transient
    public String getPhotosImagePath() {
        if (thumbnail == null || id == null) return null;

        return "/data/blogs/" + id + "/" + thumbnail;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
