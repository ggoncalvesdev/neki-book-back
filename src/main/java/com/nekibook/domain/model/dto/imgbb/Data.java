package com.nekibook.domain.model.dto.imgbb;

public class Data {
	private String id;
	private String title;
	private String url_viewer;
	private String url;
	private Image image;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl_viewer() {
		return url_viewer;
	}
	public void setUrl_viewer(String url_viewer) {
		this.url_viewer = url_viewer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Data [id=" + id + ", title=" + title + ", url_viewer=" + url_viewer + ", url=" + url + ", image="
				+ image + "]";
	}
}

