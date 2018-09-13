package com.video.app.base.bottombar;

/**
 * @ClassName: TabException
 * @author: 张京伟
 * @date: 2017/1/3 10:29
 * @Description: Tab异常类
 * @version: 1.0
 */
public class TabException extends NullPointerException {
    public TabException() {
        super();
    }

    public TabException(String detailMessage) {
        super(detailMessage);
    }
}
