package de.rapha.Blog.user;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")

    @Column(name ="id", nullable = false, unique = false)
    private Long id;

    @Column(name="name", nullable = false, unique = false)
    private String name;

    @Column(name="email", nullable = true, unique = false)
    private String email;

    @Column(name="description", nullable = true, unique = false)
    private String description;

    @Column(name="password", nullable = false, unique = false)
    private String password;

    @Column(name="photo", nullable = true, unique = false)
    private String photo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Transient
    public String getPhotoPath() {
        if(photo == null || id == null) return null;

        return "/data/user/" + id + "/" + photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
