/*
 * Timeline
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * Invention
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-03-03T09:51:15.093Z")public class Invention   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("photoUrls")
  private List<String> photoUrls = new ArrayList<String>();

  @JsonProperty("tags")
  private List<Tag> tags = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("startdate")
  private String startdate = null;

  @JsonProperty("finsihdate")
  private String finsihdate = null;

  @JsonProperty("file")
  private String file = null;

  public Invention id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * object ID in inventory
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "object ID in inventory")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Invention name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @JsonProperty("name")
  @ApiModelProperty(example = "commodore64", required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Invention photoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  public Invention addPhotoUrlsItem(String photoUrlsItem) {
    this.photoUrls.add(photoUrlsItem);
    return this;
  }

  /**
   * Get photoUrls
   * @return photoUrls
   **/
  @JsonProperty("photoUrls")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public List<String> getPhotoUrls() {
    return photoUrls;
  }

  public void setPhotoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
  }

  public Invention tags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  public Invention addTagsItem(Tag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<Tag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   **/
  @JsonProperty("tags")
  @ApiModelProperty(value = "")
  @Valid
  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Invention status(String status) {
    this.status = status;
    return this;
  }

  /**
   * inventions status in the dataBase
   * @return status
   **/
  @JsonProperty("status")
  @ApiModelProperty(value = "inventions status in the dataBase")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Invention startdate(String startdate) {
    this.startdate = startdate;
    return this;
  }

  /**
   * Get startdate
   * @return startdate
   **/
  @JsonProperty("startdate")
  @ApiModelProperty(value = "")
  public String getStartdate() {
    return startdate;
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }

  public Invention finsihdate(String finsihdate) {
    this.finsihdate = finsihdate;
    return this;
  }

  /**
   * Get finsihdate
   * @return finsihdate
   **/
  @JsonProperty("finsihdate")
  @ApiModelProperty(value = "")
  public String getFinsihdate() {
    return finsihdate;
  }

  public void setFinsihdate(String finsihdate) {
    this.finsihdate = finsihdate;
  }

  public Invention file(String file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
   **/
  @JsonProperty("file")
  @ApiModelProperty(value = "")
  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Invention invention = (Invention) o;
    return Objects.equals(this.id, invention.id) &&
        Objects.equals(this.name, invention.name) &&
        Objects.equals(this.photoUrls, invention.photoUrls) &&
        Objects.equals(this.tags, invention.tags) &&
        Objects.equals(this.status, invention.status) &&
        Objects.equals(this.startdate, invention.startdate) &&
        Objects.equals(this.finsihdate, invention.finsihdate) &&
        Objects.equals(this.file, invention.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, photoUrls, tags, status, startdate, finsihdate, file);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Invention {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    startdate: ").append(toIndentedString(startdate)).append("\n");
    sb.append("    finsihdate: ").append(toIndentedString(finsihdate)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
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

