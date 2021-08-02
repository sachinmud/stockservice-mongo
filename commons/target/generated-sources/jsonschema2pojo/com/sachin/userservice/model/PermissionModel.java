
package com.sachin.userservice.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.security.core.GrantedAuthority;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "authority"
})
public class PermissionModel implements Serializable, GrantedAuthority
{

    /**
     * id
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("id")
    @Size(min = 1, max = 255)
    @NotNull
    private String id;
    /**
     * authority
     * (Required)
     * 
     */
    @JsonProperty("authority")
    @JsonPropertyDescription("authority")
    @Size(min = 1, max = 255)
    @NotNull
    private String authority;

    /**
     * id
     * (Required)
     * 
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * id
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public PermissionModel withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * authority
     * (Required)
     * 
     */
    @JsonProperty("authority")
    public String getAuthority() {
        return authority;
    }

    /**
     * authority
     * (Required)
     * 
     */
    @JsonProperty("authority")
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public PermissionModel withAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PermissionModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("authority");
        sb.append('=');
        sb.append(((this.authority == null)?"<null>":this.authority));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
