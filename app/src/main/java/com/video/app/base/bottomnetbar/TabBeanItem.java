package com.video.app.base.bottomnetbar;

public class TabBeanItem {
    private String title;
    private String selectUrl;
    private String unSelectUrl;
    private int selectTextColor;
    private int unSelectTextColor;
    private int selectIcon;
    private int unSelectIcon;

    public TabBeanItem(String title, String selectUrl, String unSelectUrl, int selectTextColor, int unSelectTextColor, int selectIcon, int unSelectIcon) {
        this.title = title;
        this.selectUrl = selectUrl;
        this.unSelectUrl = unSelectUrl;
        this.selectTextColor = selectTextColor;
        this.unSelectTextColor = unSelectTextColor;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelectUrl() {
        return selectUrl;
    }

    public void setSelectUrl(String selectUrl) {
        this.selectUrl = selectUrl;
    }

    public String getUnSelectUrl() {
        return unSelectUrl;
    }

    public void setUnSelectUrl(String unSelectUrl) {
        this.unSelectUrl = unSelectUrl;
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }

    public int getUnSelectTextColor() {
        return unSelectTextColor;
    }

    public void setUnSelectTextColor(int unSelectTextColor) {
        this.unSelectTextColor = unSelectTextColor;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public int getUnSelectIcon() {
        return unSelectIcon;
    }

    public void setUnSelectIcon(int unSelectIcon) {
        this.unSelectIcon = unSelectIcon;
    }
}
