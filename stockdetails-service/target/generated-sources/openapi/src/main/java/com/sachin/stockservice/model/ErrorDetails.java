package com.sachin.stockservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ErrorDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-15T23:32:04.763918800+05:30[Asia/Calcutta]")

public class ErrorDetails   {
  @JsonProperty("errorcode")
  private String errorcode;

  @JsonProperty("errordesc")
  private String errordesc;

  public ErrorDetails errorcode(String errorcode) {
    this.errorcode = errorcode;
    return this;
  }

  /**
   * Get errorcode
   * @return errorcode
  */
  @ApiModelProperty(value = "")


  public String getErrorcode() {
    return errorcode;
  }

  public void setErrorcode(String errorcode) {
    this.errorcode = errorcode;
  }

  public ErrorDetails errordesc(String errordesc) {
    this.errordesc = errordesc;
    return this;
  }

  /**
   * Get errordesc
   * @return errordesc
  */
  @ApiModelProperty(value = "")


  public String getErrordesc() {
    return errordesc;
  }

  public void setErrordesc(String errordesc) {
    this.errordesc = errordesc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDetails errorDetails = (ErrorDetails) o;
    return Objects.equals(this.errorcode, errorDetails.errorcode) &&
        Objects.equals(this.errordesc, errorDetails.errordesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorcode, errordesc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDetails {\n");
    
    sb.append("    errorcode: ").append(toIndentedString(errorcode)).append("\n");
    sb.append("    errordesc: ").append(toIndentedString(errordesc)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

