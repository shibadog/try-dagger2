package com.github.shibadog;

import dagger.Module;
import dagger.Provides;

@Module
public class TryModule {
    @Provides
    static TryInterface provideTryInterface() {
        return new TryInterfaceImpl();
    }
}