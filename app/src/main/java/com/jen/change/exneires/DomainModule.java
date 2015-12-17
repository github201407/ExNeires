package com.jen.change.exneires;

import com.jen.change.exneires.splash.AppController;

/**
 * Created by chenmingqun on 2015/12/15.
 */
public class DomainModule {
    public AppController provideAnalyticsManager(AppModel app){
        return new AppController(app);
    }
}
