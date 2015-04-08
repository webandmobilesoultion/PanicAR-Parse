package com.raoul.cudenver.utill;

/**
 * Created by mobile_perfect on 20/03/15.
 */
public class Position {

    Double position_latitude;
    Double position_longitude;
    String position_id;
    String position_name;
    String position_adress;
    String image_url;
    String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setPosition_latitude(Double position_latitude) {
        this.position_latitude = position_latitude;
    }

    public void setPosition_longitude(Double position_longitude) {
        this.position_longitude = position_longitude;
    }

    public Double getPosition_latitude() {
        return position_latitude;
    }

    public Double getPosition_longitude() {
        return position_longitude;
    }

    public void setPosition_adress(String position_adress) {
        this.position_adress = position_adress;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPosition_adress() {
        return position_adress;
    }

    public String getPosition_id() {
        return position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

}
