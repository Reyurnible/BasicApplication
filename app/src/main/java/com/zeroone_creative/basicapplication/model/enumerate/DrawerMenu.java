package com.zeroone_creative.basicapplication.model.enumerate;

/**
 * Created by shunhosaka on 2015/03/21.
 */
public enum DrawerMenu {
    Main(0, 0)
    ;
    public static final int NO_RESOURCE = 0x0100;

    public int nameId;
    public int imageId;
    DrawerMenu(int nameId, int imageId) {
        this.nameId = nameId;
        this.imageId = imageId;
    }
}
