package com.nekibook.domain.model.dto;

import java.util.List;

public class VolumeInfoDTO {

  public String title;
  private String subtitle;
  private List<String> authors;
  private String publisher;
  private String publishedDate;
  private String description;
  private Integer pageCount;
  private List<String> categories;
  private ImageLinksDTO imageLinks;

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getdescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public void setImageLinks(ImageLinksDTO imageLinks) {
    this.imageLinks = imageLinks;
  }

  public ImageLinksDTO getImageLinks() {
    return imageLinks;
  }

  public void setImageLinks(ImageLinksDTO imageLinks, String title) {
    this.imageLinks = imageLinks;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
