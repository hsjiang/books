package com.books.application.common.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by riven_chris on 2017/4/13.
 */

public class BaseActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {

    MyActivityManager mAppManager;

    public BaseActivityLifecycleCallback(MyActivityManager appManager) {
        this.mAppManager = appManager;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mAppManager.addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mAppManager.setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (mAppManager.getCurrentActivity() == activity) {
            mAppManager.setCurrentActivity(null);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mAppManager.removeActivity(activity);
    }
}
