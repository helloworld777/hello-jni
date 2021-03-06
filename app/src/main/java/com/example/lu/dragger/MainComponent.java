package com.example.lu.dragger;



import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {MainModule.class})
public interface MainComponent  extends AndroidInjector<DraggerTestActivity> {
//    void inject(DraggerTestActivity draggerTestActivity);

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DraggerTestActivity>{};
}
