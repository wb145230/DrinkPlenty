package com.wb.drinkplenty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wb.drinkplenty.base.BaseActivity;
import com.wb.drinkplenty.util.SharedPreferenceSetting;
import com.xdandroid.hellodaemon.DaemonEnv;
import com.xdandroid.hellodaemon.IntentWrapper;

public class MainActivity extends BaseActivity implements OnClickListener {

    private Button mButton;
    private Button mButton2;
    private TextView mTextView;
    private TextView mTextView2;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView2 = (TextView) findViewById(R.id.textView2);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);

        mImageView = (ImageView) findViewById(R.id.imageView);

        updateDrinkView(SharedPreferenceSetting.getCupService(this));

        if (!SharedPreferenceSetting.getCupWhiteListMatters(this)) {
            SharedPreferenceSetting.setCupWhiteListMatters(this,true);
            IntentWrapper.whiteListMatters(this, "轨迹跟踪服务的持续运行");
        }
    }

    private void updateDrinkView(Boolean service) {
        if (service) {
            findViewById(R.id.mainLayout).setBackgroundResource(R.color.main_open_bg);
            mButton.setBackgroundResource(R.drawable.main_cup_service_switch_on);
            mTextView.setTextColor(getResources().getColor(R.color.main_open_tip));
            mTextView.setText(R.string.main_open_tips);
            mTextView2.setTextColor(getResources().getColor(R.color.main_open_tip));
            mTextView2.setText(R.string.main_switch_open_tips);
            mButton2.setBackgroundResource(R.drawable.main_cup_service_open_setting);
            mImageView.setBackgroundResource(R.drawable.main_cup_have);

        } else {
            findViewById(R.id.mainLayout).setBackgroundResource(R.color.main_close_bg);
            mButton.setBackgroundResource(R.drawable.main_cup_service_switch_off);
            mTextView.setTextColor(getResources().getColor(R.color.main_close_tip));
            mTextView.setText(R.string.main_close_tips);
            mTextView2.setTextColor(getResources().getColor(R.color.main_close_tip));
            mTextView2.setText(R.string.main_switch_close_tips);
            mButton2.setBackgroundResource(R.drawable.main_cup_service_close_setting);
            mImageView.setBackgroundResource(R.drawable.main_cup_empty);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Boolean status = !SharedPreferenceSetting.getCupService(this);
                SharedPreferenceSetting.setCupService(this, status);

                updateDrinkView(status);

                if (status) {
                    CoreService.sShouldStopService = false;
                    DaemonEnv.startServiceMayBind(CoreService.class);
                } else {
                    CoreService.stopService();
                }

                break;
            case R.id.button2:

                startActivity(new Intent(this, SettingActivity.class));

                break;
            default:
                break;
        }
    }

    //防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀
    public void onBackPressed() {
        IntentWrapper.onBackPressed(this);
    }
}
