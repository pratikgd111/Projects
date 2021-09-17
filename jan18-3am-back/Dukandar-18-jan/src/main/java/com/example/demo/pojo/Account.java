package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/*
  account_id | int          | NO   | PRI | NULL    | auto_increment |
| auth_key   | varchar(100) | YES  |     | NULL    |                |
| is_active  | bit(1)       | NO   |     | NULL    |                |
| mobile     | varchar(20)  | YES  |     | NULL    |                |
| password   | varchar(100) | YES  |     | NULL    |                |
| role       | varchar(10)  | YES  |     | NULL

*/

@Entity(name = "account")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Account implements Serializable,UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(length = 20)
    @JsonProperty("mobile")
    private String mobile;

    @Column(length = 100)
    @JsonProperty("password")
    private String password;

    @Column(length = 100)
    @JsonProperty("auth_key")
    private String authKey;

    @JsonProperty("is_active")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @JsonProperty("role")
    private Role role;
    
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    /*@OneToOne(mappedBy = "acc", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("acc")
    @JsonIgnore*/

    @OneToOne(mappedBy = "acc", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Vendor v;


    @OneToOne(mappedBy = "acc", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("acc")
    @JsonIgnore
    private User u;
    
    

    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	public Vendor getV() {
		return v;
	}

	public void setV(Vendor v) {
		this.v = v;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public void addVendor(Vendor vendor){
        this.setV(vendor);
        vendor.setAcc(this);
    }

    public void addUser(User user){
        this.setU(user);
        user.setAcc(this);
    }

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", mobile=" + mobile + ", password=" + password + ", authKey="
				+ authKey + ", isActive=" + isActive + ", role=" + role + ", roles=" + roles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		
		return this.mobile;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
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
    
    
        
}
