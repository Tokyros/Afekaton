package afekaton.afekatontests.models.members;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Enumerated(EnumType.STRING)
    private AfekaRole afekaRole;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    @Size(min = 6)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AfekaRole getAfekaRole() {
        return afekaRole;
    }

    public void setAfekaRole(AfekaRole afekaRole) {
        this.afekaRole = afekaRole;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "afekaRole=" + afekaRole +
                ", department=" + department +
                ", username='" + username + '\'' +
                '}';
    }
}
