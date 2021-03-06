package com.example.lu.activity;;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.example.lu.MyNotificationListenerService;
import com.example.lu.R;
import com.example.lu.log.DebugLog;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /**
     * 是否打开了通知权限
     *
     * @return
     */
    private boolean isNotificationEnabled() {
        ContentResolver contentResolver = getContentResolver();
        String enabledListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        if (!TextUtils.isEmpty(enabledListeners)) {
            return enabledListeners.contains(MyNotificationListenerService.class.getName());
        } else {
            return false;
        }
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btnStartService:
//                startService(new Intent(this,MyNotificationListenerService.class));
                break;
            case R.id.btnTestService:
                DebugLog.d("toggleNotificationListenerService");
                toggleNotificationListenerService();
                break;
            case R.id.btnEnableService:
//                isNotificationEnabled();
                startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 1);
                break;
        }

    }
    /**
     * 重新启动服务
     */
    private void toggleNotificationListenerService() {
        ComponentName thisComponent = new ComponentName(this,  MyNotificationListenerService.class);
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,MyNotificationListenerService.class));
    }
}
