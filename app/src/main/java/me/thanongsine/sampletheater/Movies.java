package me.thanongsine.sampletheater;

public class Movies {
    private String imgRes;
    private String title;

    public Movies(String imgRes, String title) {
        this.imgRes = imgRes;
        this.title = title;
    }

    public String getImgRes() {
        return imgRes;
    }

    public String getTitle() {
        return title;
    }
}
