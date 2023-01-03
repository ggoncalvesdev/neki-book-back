package com.nekibook.domain.model.dto.imgbb;

public class Image {
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Image [filename=" + filename + "]";
	}
}
