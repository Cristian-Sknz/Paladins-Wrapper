package me.skiincraft.api.paladins.internal.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class PaladinsLogger {

    private static PaladinsLogger instance;
    private static final Map<String, Logger> simpleLoggers = new HashMap<>();
    private static boolean SLFJ4_ENABLED;

    private PaladinsLogger() {
        boolean found = false;
        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            found = true;
        } catch (ClassNotFoundException impl){
            try {
                Class<?> serviceProviderClass = Class.forName("org.slf4j.spi.SLF4JServiceProvider");
                found = ServiceLoader.load(serviceProviderClass).iterator().hasNext();
            } catch (ClassNotFoundException spi){
                lockAndWaitForWarning();
            }
        }
        SLFJ4_ENABLED = found;
    }

    private void lockAndWaitForWarning(){
        try {
            LoggerFactory.getLogger(PaladinsLogger.class);
            Thread.sleep(2500);
        } catch (Exception ignored){}
    }

    public Logger getLoggerInstance(String name){
        if (SLFJ4_ENABLED)
            return LoggerFactory.getLogger(name);

        synchronized (simpleLoggers) {
            return simpleLoggers.computeIfAbsent(name, SimpleLogger::new);
        }
    }

    public Logger getLoggerInstance(Class<?> clazz){
        if (SLFJ4_ENABLED)
            return LoggerFactory.getLogger(clazz);

        synchronized (simpleLoggers) {
            return simpleLoggers.computeIfAbsent(clazz.getSimpleName(), SimpleLogger::new);
        }
    }

    public static Logger getLogger(String name){
        return getInstance().getLoggerInstance(name);
    }

    public static Logger getLogger(Class<?> clazz){
        return getInstance().getLoggerInstance(clazz);
    }

    public static PaladinsLogger getInstance() {
        if (instance == null) {
            instance = new PaladinsLogger();
            return instance;
        }
        return instance;
    }
}
