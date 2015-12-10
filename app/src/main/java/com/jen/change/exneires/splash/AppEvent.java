package com.jen.change.exneires.splash;

/**
 * Created by Administrator on 2015/12/10.
 */

import android.content.Context;
import android.os.Bundle;

public class AppEvent {
    public AppEvent(Context context, int eventId, Bundle params) {
        this.context = context;
        this.eventId = eventId;
        this.params = params;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Bundle getParams() {
        return params;
    }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public final static int EVE_NONE = 0;
    public final static int EVE_SPLASH_END = 1; // Splash界面显示时间到期

    private Context context = null;
    private int eventId = 0;
    private Bundle params = null;
}

