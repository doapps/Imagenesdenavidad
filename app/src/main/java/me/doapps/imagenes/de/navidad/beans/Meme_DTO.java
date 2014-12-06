package me.doapps.imagenes.de.navidad.beans;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by jonathan on 04/12/2014.
 */
@ParseClassName("Memes")
public class Meme_DTO extends ParseObject{
    private String image_url;

    public Meme_DTO(){}

    public Meme_DTO(String image_url){
        this.image_url = image_url;
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
    public static ParseQuery<Meme_DTO> getQuery() {
        return ParseQuery.getQuery(Meme_DTO.class);
    }
}
