package com.github.shibadog;

import dagger.Component;

@Component(modules = { TryModule.class })
public interface MyContext {
    void inject(MyComponent Component);
}