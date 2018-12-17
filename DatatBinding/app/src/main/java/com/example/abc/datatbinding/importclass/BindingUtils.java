package com.example.abc.datatbinding.importclass;

import android.util.Log;

public class BindingUtils {
    public static String capitalize(String text) {
        return text.toUpperCase();
    }
    public static String sumab(String a,String b){
        if (!a.equals("")&&!b.equals("")) {
            int k = Integer.parseInt(a.trim()) + Integer.parseInt(b.trim());
            return k+"";
        }
        return "ksj";

    }
}
