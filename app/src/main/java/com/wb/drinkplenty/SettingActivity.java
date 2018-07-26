package com.wb.drinkplenty;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.wb.drinkplenty.base.BaseActivity;
import com.wb.drinkplenty.util.SharedPreferenceSetting;

public class SettingActivity extends BaseActivity implements OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        TextView mVersion = (TextView) findViewById(R.id.textView4);
        mVersion.setText(String.format(getString(R.string.setting_app_version), getVerName()));

        updateDrinkView(SharedPreferenceSetting.getCupNightModeService(this));

    }

    private String getVerName() {
        String verName = "";
        try {
            verName = getPackageManager().getPackageInfo("com.wb.drinkplenty", 0).versionName;
        } catch (NameNotFoundException e) {
        }
        return verName;
    }

    private void updateDrinkView(Boolean service) {
        if (service) {
            mButton.setBackgroundResource(R.drawable.main_cup_night_mode_service_switch_on);
        } else {
            mButton.setBackgroundResource(R.drawable.main_cup_night_mode_service_switch_off);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Boolean status = !SharedPreferenceSetting.getCupNightModeService(this);
                SharedPreferenceSetting.setCupNightModeService(this, status);

                updateDrinkView(status);
                break;
            default:
                break;
        }
    }

}
