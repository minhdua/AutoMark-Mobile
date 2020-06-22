package com.vothanhhien.automarkmobile.activities.Setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Setting {
    public Setting(Context appContext)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(appContext);
        this.simplify = pref.getBoolean(Setting.keySimplify, this.simplify);
        this.update = pref.getBoolean(Setting.keyUpdate, this.update);
        this.vibrate = pref.getBoolean(Setting.keyVibrate, this.vibrate);
        this.autoScan = pref.getBoolean(Setting.keyAutoScan, this.autoScan);
        try
        {
            this.timeDelay = Integer.parseInt(
                    pref.getString(Setting.keyTimeDelay,
                            Integer.toString(this.timeDelay)));
        }
        catch (Exception e)
        {
            // Do no thing!
        }
        try
        {
            this.languageId = Integer.parseInt(
                    pref.getString(Setting.keyLanguageId,
                            Integer.toString(this.languageId)));
        }
        catch (Exception e)
        {
            // Do no thing!
        }
    }
    // Show Info
    public String ShowInfo() {
        String info =
                "Update:" + update +
                        "\nSimplify: " + simplify +
                        "\nVibrate: " + vibrate +
                        "\nAuto Scan: " + autoScan +
                        "\nTime Delay: " + timeDelay +
                        "\nLanguage ID: " + languageId;
        return info;
    }

    // Phần cài đặt thiết yếu
    public boolean simplify = false;
    public boolean update = true;
    public boolean vibrate = true;
    public boolean autoScan = true;
    public int timeDelay = 500;
    public int languageId = 0;
    // Phần set up thông tin
    public static final String applicationName = "Chấm Thi";
    public static final String applicationVersion = "v1.0";
    public static final String applicationPublicDay = "Ngày 10 tháng 9 năm 2018";
    public static final String applicationAuthorEmail = "hienb1606980@student.ctu.edu.vn";
    public static final String applicationAuthorFacebook = "https://www.facebook.com/hienb1606980";
    // Danh sách khóa của setting
    public static final String keySimplify = "setting_simplify";
    public static final String keyUpdate = "setting_update";
    public static final String keyVibrate = "setting_vibrate";
    public static final String keyAutoScan = "setting_auto";
    public static final String keyTimeDelay = "setting_timeDelay";
    public static final String keyLanguageId = "setting_language";
    public static final String keyAppName = "app_name";
    public static final String keyAppVersion = "app_version";
    public static final String keyAppPublicDay = "app_publicDay";
    public static final String keyAppAuthorEmail = "app_authorEmail";
}
