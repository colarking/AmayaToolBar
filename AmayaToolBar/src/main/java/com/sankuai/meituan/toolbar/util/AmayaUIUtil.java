package com.sankuai.meituan.toolbar.util;

import android.os.Build;

/**
 * Created by amayababy.
 * User: amayababy
 * Date: 2014-11-10
 * Time: 下午3:11
 */
public class AmayaUIUtil {

    public static boolean supportAPI(int targetApi){
        return Build.VERSION.SDK_INT >= targetApi;

    }
}
