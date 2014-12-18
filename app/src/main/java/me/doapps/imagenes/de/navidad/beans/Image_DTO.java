package me.doapps.imagenes.de.navidad.beans;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by jonathan on 18/12/2014.
 */
@ParseClassName("Navidad")
public class Image_DTO extends ParseObject {
    private String image_url;

    public Image_DTO(String image_url) {
        this.image_url = image_url;
    }

    public Image_DTO() {
    }

    public String getImage_url() {
        return getString("image_url");
    }

    public void setImage_url(String image_url) {
        put("image_url", image_url);
    }

    /**
     * querys*
     */
    public static ParseQuery<Image_DTO> getQuery() {
        return ParseQuery.getQuery(Image_DTO.class);
    }
}
