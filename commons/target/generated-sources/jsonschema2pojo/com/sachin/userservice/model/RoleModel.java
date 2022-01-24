
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


/**
 * RoleModel
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "rolename",
    "permissions"
})
public class RoleModel implements Serializable
{

    /**
     * id
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("id")
    @Size(min = 1, max = 255)
    private String id;
    /**
     * rolename
     * (Required)
     * 
     */
    @JsonProperty("rolename")
    @JsonPropertyDescription("rolename")
    @Size(min = 1, max = 255)
    @NotNull
    private String rolename;
    /**
     * permissions
     * <p>
     * permissions
     * 
     */
    @JsonProperty("permissions")
    @JsonPropertyDescription("permissions")
    @Valid
    private List<PermissionModel> permissions = new ArrayList<PermissionModel>();

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

    public RoleModel withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * rolename
     * (Required)
     * 
     */
    @JsonProperty("rolename")
    public String getRolename() {
        return rolename;
    }

    /**
     * rolename
     * (Required)
     * 
     */
    @JsonProperty("rolename")
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public RoleModel withRolename(String rolename) {
        this.rolename = rolename;
        return this;
    }

    /**
     * permissions
     * <p>
     * permissions
     * 
     */
    @JsonProperty("permissions")
    public List<PermissionModel> getPermissions() {
        return permissions;
    }

    /**
     * permissions
     * <p>
     * permissions
     * 
     */
    @JsonProperty("permissions")
    public void setPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
    }

    public RoleModel withPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RoleModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("rolename");
        sb.append('=');
        sb.append(((this.rolename == null)?"<null>":this.rolename));
        sb.append(',');
        sb.append("permissions");
        sb.append('=');
        sb.append(((this.permissions == null)?"<null>":this.permissions));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
