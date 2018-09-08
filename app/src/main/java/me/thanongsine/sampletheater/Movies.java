package me.thanongsine.sampletheater;

import android.widget.ImageView;
import android.widget.TextView;

public class Movies {
    private Integer imgRes;
    private String title;

    Movies(Integer imgRes, String title) {
        this.imgRes = imgRes;
        this.title = title;
    }

    public Integer getImgRes() {
        return imgRes;
    }

    public String getTitle() {
        return title;
    }
}
