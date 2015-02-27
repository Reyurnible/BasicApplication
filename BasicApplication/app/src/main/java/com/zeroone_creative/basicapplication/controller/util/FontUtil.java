package com.zeroone_creative.basicapplication.controller.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by shunhosaka on 2015/01/18.
 */
public class FontUtil {

    /**
     * assetにある.ttfか.zipのファイルからフォントを取得する。
     * パスはフルパス　font/フィアル名
     *
     * @param fontFilePath
     * @param context
     * @return
     */
    public static Typeface getFont(String fontFilePath, Context context) {
        if (fontFilePath.contains(".ttf")) {
            return getFontFromTtf(fontFilePath, context);
        } else if (fontFilePath.contains(".zip")) {
            return getFontFromZip(fontFilePath, context);
        }
        return null;
    }

    /**
     * フォントファイルをassetから取得する。
     *
     * @param fontFileName
     * @param context
     * @return
     */
    private static Typeface getFontFromTtf(String fontFileName, Context context) {
        AssetManager am = context.getAssets();
        return Typeface.createFromAsset(am, fontFileName);
    }

    /**
     * zip圧縮されているフォントファイルをassetから解凍し、dataフォルダに格納する
     * assetのファイルはある程度大きいとzip圧縮されていないとAndroidで認識してくれない
     *
     * @param fontFileName
     * @param context
     * @return
     */
    private static Typeface getFontFromZip(String fontFileName, Context context) {
        Typeface ret = null;
        File zipFile = null;
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(fontFileName, AssetManager.ACCESS_STREAMING);

            ZipInputStream zis = new ZipInputStream(is);
            ZipEntry ze = zis.getNextEntry();
            if (ze != null) {
                zipFile = new File(context.getFilesDir(), ze.getName());

                //フォントがすでに解凍されていればなにもしない
                if (zipFile.exists()) {
                    return Typeface.createFromFile(zipFile.getPath());
                }
                FileOutputStream fos = new FileOutputStream(zipFile, false);
                byte[] buf = new byte[1024];
                int size = 0;
                while ((size = zis.read(buf, 0, buf.length)) > -1) {
                    fos.write(buf, 0, size);
                }
                fos.close();
                zis.closeEntry();
                ret = Typeface.createFromFile(zipFile.getPath());
            }
            zis.close();
        } catch (Exception e) {
            Log.e("FontUtil", "font extract fail", e);
            if (zipFile != null && zipFile.exists())
                zipFile.delete();
        }
        return ret;
    }

}