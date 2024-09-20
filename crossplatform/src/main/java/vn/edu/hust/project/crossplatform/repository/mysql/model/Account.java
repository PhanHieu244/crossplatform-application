package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Lob
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "token")
    private String token;

    @Column(name = "session")
    private String session;

    @Column(name = "status", nullable = false)
    private String status = "Kích hoạt";  // Thiết lập giá trị mặc định trong Java


}