package com.video.app.base.load;

public enum LoadStatus {
    NORMAL(0x00000002), REFRESH(0x00000004), LOADMORE(0x00000008);

    private int state;

    LoadStatus(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
