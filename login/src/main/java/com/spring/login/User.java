package com.spring.login;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "users")
public class User {
    
	
	
	private @Id @GeneratedValue long id;
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank String user_key = UUID.randomUUID().toString();
    private @NotNull boolean loggedIn;
    
    public User() {
    }
    public User(@NotBlank String username, 
                @NotBlank String password) {
        this.username = username;
        this.password = password;
        //this.user_key = user_key;
        this.loggedIn = false;
    }
    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    
    public String getuser_key() {
        return user_key;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setuser_key(String user_key) {
        this.user_key = user_key;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, 
                            loggedIn);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_key='" + user_key + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}