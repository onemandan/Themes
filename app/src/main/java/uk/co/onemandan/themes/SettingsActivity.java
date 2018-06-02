package uk.co.onemandan.themes;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences _sharedPreferences;
    Switch _settingsTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean useDarkTheme = _sharedPreferences.getBoolean(getString(R.string.shared_preferences_theme_dark), false);
        setTheme(useDarkTheme ? R.style.BaseTheme_Dark : R.style.BaseTheme_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar _toolbar        = findViewById(R.id.toolbar);
        TextView _toolbarTitle  = findViewById(R.id.tv_toolbar_title);
        _settingsTheme          = findViewById(R.id.sw_settings_theme);

        setSupportActionBar(_toolbar);
        _toolbarTitle.setText(getString(R.string.app_name));
        _settingsTheme.setChecked(useDarkTheme);

        _settingsTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = _sharedPreferences.edit();
                editor.putBoolean(getString(R.string.shared_preferences_theme_dark), b);
                editor.commit();

                refresh();
            }
        });
    }

    private void refresh(){
        startActivity(getIntent());
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
