package com.zeroone_creative.basicapplication.controller.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shunhosaka on 2015/04/09.
 */
public class SharedPreferencesUtil {
    private static Map<PrefKey, SharedPreferences> mSharedPreferencesMap = new HashMap<>();
    private Object lock = new Object();

    public enum PrefKey {
        Setting("setting", 1)
        ;
        public String name;
        public int version;
        PrefKey(String name, int version) {
            this.name = name;
            this.version = version;
        }
    }

    /**
     * Getting new sharedpreferences key.
     * @param prefKey
     * @return
     */
    public static SharedPreferences getPreferences(Context context, PrefKey prefKey) {
        if (!mSharedPreferencesMap.containsKey(prefKey)) {
            //新しいSharedPreferencesを用意する
            SharedPreferences sharedPreferences = context.getSharedPreferences(prefKey.name + String.valueOf(prefKey.version), Context.MODE_PRIVATE);
            mSharedPreferencesMap.put(prefKey, sharedPreferences);
        }
        return mSharedPreferencesMap.get(prefKey);
    }

}
