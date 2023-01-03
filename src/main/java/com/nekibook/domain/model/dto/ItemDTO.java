package com.nekibook.domain.model.dto;

public class ItemDTO {
    // public String kind;
    public String id;
    public String etag;
    // public String selfLink;
    public VolumeInfoDTO volumeInfo;

    public VolumeInfoDTO getVolumeInfo() {
        return volumeInfo;
    }
    public void setVolumeInfo(VolumeInfoDTO volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEtag() {
        return etag;
    }
    public void setEtag(String etag) {
        this.etag = etag;
    }

}
