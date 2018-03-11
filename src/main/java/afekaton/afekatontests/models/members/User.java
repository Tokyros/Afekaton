package afekaton.afekatontests.models.members;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
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
    private String username;
    @NotEmpty
    @Min(value = 8)
//    @Max(value = 16)
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
        return "User{" +
                "afekaRole=" + afekaRole +
                ", department=" + department +
                ", username='" + username + '\'' +
                '}';
    }
}
