package com.vothanhhien.automarkmobile.activities.Setting;

public class SupportLanguages {
    public final static String[] List = {
      "Tiếng Việt",
      "Tiếng Anh"
    };
    public static String[] getIndex()
    {
        String[] a = new String[List.length];
        for (int i = 0;i < List.length;i++)
            a[i] = Integer.toString(i);
        return a;
    }
}
