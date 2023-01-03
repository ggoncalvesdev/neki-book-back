package com.nekibook.domain.model.dto;

import java.util.List;

public class ConsultaLivroDTO {

    // private String kind;
    // private String totalItems;
    private List<ItemDTO> items;

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }


    // public String getKind() {
    //     return kind;
    // }
    // public void setKind(String kind) {
    //     this.kind = kind;
    // }
    // public String getTotalItems() {
    //     return totalItems;
    // }
    // public void setTotalItems(String totalItems) {
    //     this.totalItems = totalItems;
    // }
 
}
