package afekaton.afekatontests.models.members;

import org.hibernate.annotations.Target;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Member member;

    private String username;
    private char[] password;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

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
}
