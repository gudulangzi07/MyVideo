package com.video.takephoto;

import java.io.File;
import java.io.Serializable;

final class TakePhoto implements Serializable {
    public File file;//文件
    public int width;//宽
    public int height;//高

    public TakePhoto(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
