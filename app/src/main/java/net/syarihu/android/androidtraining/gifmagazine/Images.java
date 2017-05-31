package net.syarihu.android.androidtraining.gifmagazine;

import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("default")
    public GifImage defaultImage;
    @SerializedName("medium")
    public GifImage mediumImage;
    @SerializedName("small")
    public GifImage smallIamage;
}
