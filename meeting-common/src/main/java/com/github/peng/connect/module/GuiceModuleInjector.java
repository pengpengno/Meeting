package com.github.peng.connect.module;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * 基于 Guice 的 Module 注入
 */
public class GuiceModuleInjector {

    public static Injector injector = Guice.createInjector(new ConnectionModule());

}
