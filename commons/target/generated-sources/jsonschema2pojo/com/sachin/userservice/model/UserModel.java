
package com.sachin.userservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * UserModel
 * <p>
 * User resource object
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "username",
    "fullname",
    "password",
    "enabled",
    "accountNonLocked",
    "accountNonExpired",
    "credentialsNonExpired",
    "authorities",
    "roles"
})
public class UserModel implements Serializable, UserDetails
{

    /**
     * id
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("id")
    private String id;
    /**
     * username
     * (Required)
     * 
     */
    @JsonProperty("username")
    @JsonPropertyDescription("username")
    @Size(min = 1, max = 10)
    @NotNull
    private String username;
    /**
     * fullname
     * 
     */
    @JsonProperty("fullname")
    @JsonPropertyDescription("fullname")
    @Size(min = 1, max = 50)
    private String fullname;
    /**
     * password
     * (Required)
     * 
     */
    @JsonProperty("password")
    @JsonPropertyDescription("password")
    @Size(min = 1, max = 20)
    @NotNull
    private String password;
    /**
     * enabled
     * 
     */
    @JsonProperty("enabled")
    @JsonPropertyDescription("enabled")
    private boolean enabled;
    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("accountNonLocked")
    @JsonPropertyDescription("accountNonLocked")
    private boolean accountNonLocked;
    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("accountNonExpired")
    @JsonPropertyDescription("accountNonLocked")
    private boolean accountNonExpired;
    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("credentialsNonExpired")
    @JsonPropertyDescription("accountNonLocked")
    private boolean credentialsNonExpired;
    /**
     * authorities
     * <p>
     * 
     * 
     */
    @JsonProperty("authorities")
    @Valid
    private List<PermissionModel> authorities = new ArrayList<PermissionModel>();
    /**
     * roles
     * <p>
     * 
     * 
     */
    @JsonProperty("roles")
    @Valid
    private List<RoleModel> roles = new ArrayList<RoleModel>();

    /**
     * id
     * 
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * id
     * 
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public UserModel withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * username
     * (Required)
     * 
     */
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    /**
     * username
     * (Required)
     * 
     */
    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public UserModel withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * fullname
     * 
     */
    @JsonProperty("fullname")
    public String getFullname() {
        return fullname;
    }

    /**
     * fullname
     * 
     */
    @JsonProperty("fullname")
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UserModel withFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    /**
     * password
     * (Required)
     * 
     */
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    /**
     * password
     * (Required)
     * 
     */
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * enabled
     * 
     */
    @JsonProperty("enabled")
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * enabled
     * 
     */
    @JsonProperty("enabled")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserModel withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("accountNonLocked")
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("accountNonLocked")
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public UserModel withAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("accountNonExpired")
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("accountNonExpired")
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public UserModel withAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("credentialsNonExpired")
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * accountNonLocked
     * 
     */
    @JsonProperty("credentialsNonExpired")
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public UserModel withCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    /**
     * authorities
     * <p>
     * 
     * 
     */
    @JsonProperty("authorities")
    public List<PermissionModel> getAuthorities() {
        return authorities;
    }

    /**
     * authorities
     * <p>
     * 
     * 
     */
    @JsonProperty("authorities")
    public void setAuthorities(List<PermissionModel> authorities) {
        this.authorities = authorities;
    }

    public UserModel withAuthorities(List<PermissionModel> authorities) {
        this.authorities = authorities;
        return this;
    }

    /**
     * roles
     * <p>
     * 
     * 
     */
    @JsonProperty("roles")
    public List<RoleModel> getRoles() {
        return roles;
    }

    /**
     * roles
     * <p>
     * 
     * 
     */
    @JsonProperty("roles")
    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public UserModel withRoles(List<RoleModel> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("fullname");
        sb.append('=');
        sb.append(((this.fullname == null)?"<null>":this.fullname));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("enabled");
        sb.append('=');
        sb.append(this.enabled);
        sb.append(',');
        sb.append("accountNonLocked");
        sb.append('=');
        sb.append(this.accountNonLocked);
        sb.append(',');
        sb.append("accountNonExpired");
        sb.append('=');
        sb.append(this.accountNonExpired);
        sb.append(',');
        sb.append("credentialsNonExpired");
        sb.append('=');
        sb.append(this.credentialsNonExpired);
        sb.append(',');
        sb.append("authorities");
        sb.append('=');
        sb.append(((this.authorities == null)?"<null>":this.authorities));
        sb.append(',');
        sb.append("roles");
        sb.append('=');
        sb.append(((this.roles == null)?"<null>":this.roles));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
