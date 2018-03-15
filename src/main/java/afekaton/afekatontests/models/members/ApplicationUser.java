package afekaton.afekatontests.models.members;

import afekaton.afekatontests.models.questions.Rated;
import afekaton.afekatontests.models.questions.Vote;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {
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
//    @NotEmpty
//    @Min(value = 8)
//    @Max(value = 16)
    private String password;

    @OneToMany
    private List<Vote> ratings = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    public List<Vote> getRatings() {
        return ratings;
    }

    public void setRatings(List<Vote> ratings) {
        this.ratings = ratings;
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
