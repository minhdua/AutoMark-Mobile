package com.vothanhhien.automarkmobile.activities.Setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.NhapDapAn.Hand.MakeActivity;

public class SettingsActivity extends AppCompatPreferenceActivity {

    public SwitchPreference setting_simplify;
    public SwitchPreference setting_update;
    public SwitchPreference setting_vibrate;
    public SwitchPreference setting_auto;
    public EditTextPreference setting_timeDelay;
    public ListPreference setting_language;
    EditTextPreference app_name;
    EditTextPreference app_version;
    EditTextPreference app_publicDay;
    EditTextPreference app_authorEmail;
    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;
    Setting _Setting;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == MakeActivity.BackButton)
        {
            onBackPressed();
            finish();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        addPreferencesFromResource(R.xml.my_setting);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefEditor = pref.edit();
        setPreferenceAddress();
        _Setting = new Setting(getApplicationContext());
        LoadDataFromSettingClass();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    void setPreferenceAddress() {
        setting_simplify = (SwitchPreference) findPreference(Setting.keySimplify);
        setting_update = (SwitchPreference) findPreference(Setting.keyUpdate);
        setting_vibrate = (SwitchPreference) findPreference(Setting.keyVibrate);
        setting_auto = (SwitchPreference) findPreference(Setting.keyAutoScan);
        setting_timeDelay = (EditTextPreference) findPreference(Setting.keyTimeDelay);
        setting_language = (ListPreference) findPreference(Setting.keyLanguageId);
        app_name = (EditTextPreference) findPreference(Setting.keyAppName);
        app_version = (EditTextPreference) findPreference(Setting.keyAppVersion);
        app_publicDay = (EditTextPreference) findPreference(Setting.keyAppPublicDay);
        app_authorEmail = (EditTextPreference) findPreference(Setting.keyAppAuthorEmail);
    }

    void LoadDataFromSettingClass() {
        setting_simplify.setChecked(_Setting.simplify);
        setting_update.setChecked(_Setting.update);
        setting_vibrate.setChecked(_Setting.vibrate);
        setting_auto.setChecked(_Setting.autoScan);

        setting_timeDelay.setDefaultValue(_Setting.timeDelay);
        setting_timeDelay.setTitle("Nhập thời gian xem");
        setting_timeDelay.setDialogMessage("Ở đây đơn vị là ms (mili giây).\nCứ mỗi 1000ms = 1s.");
        setting_timeDelay.setPositiveButtonText("Xác nhận");
        setting_timeDelay.setSummary(
                Integer.toString(_Setting.timeDelay / 1000) + " giây.");
        setting_timeDelay.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                String timeDelay = (String)o;
                setting_timeDelay.setSummary(
                        Integer.toString(Integer.parseInt(timeDelay) / 1000) + " giây.");
                return true;
            }
        });

        setting_language.setDefaultValue(_Setting.languageId);
        setting_language.setEntries(SupportLanguages.List);
        setting_language.setEntryValues(SupportLanguages.getIndex());
        setting_language.setValueIndex(_Setting.languageId);
        setting_language.setSummary(SupportLanguages.List[_Setting.languageId]);
        setting_language.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                String languageId = (String) o;
                setting_language.setEntries(SupportLanguages.List);
                setting_language.setEntryValues(SupportLanguages.getIndex());
                setting_language.setValueIndex(Integer.parseInt(languageId));
                setting_language.setSummary(SupportLanguages.List[Integer.parseInt(languageId)]);
                return true;
            }
        });

        app_name.setSummary(Setting.applicationName);
        app_name.setSelectable(false);
        app_version.setSummary(Setting.applicationVersion);
        app_version.setSelectable(false);
        app_publicDay.setSummary(Setting.applicationPublicDay);
        app_publicDay.setSelectable(false);
        app_authorEmail.setSummary(Setting.applicationAuthorEmail);
        app_authorEmail.setSelectable(false);
    }
}
