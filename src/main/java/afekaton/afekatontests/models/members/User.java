package afekaton.afekatontests.models.members;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Enumerated(EnumType.STRING)
    private AfekaRole afekaRole;
    @Enumerated(EnumType.STRING)
    private Department department;

    @Email(message = "חובה למלא כתובת מייל תקינה")
    private String email;
    private String username;
    private char[] password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
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
