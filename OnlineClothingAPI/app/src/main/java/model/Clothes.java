package model;

public class Clothes {
    private String id;
    private String name;
    private String description;
    private String image;
    private String price;


    public Clothes(String id) {
        this.id = id;
    }

    public Clothes(String name, String description, String image,String price) {
        this.name = name;
        this.description = description;
        this.image=image;
        this.price=price;
    }


    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
