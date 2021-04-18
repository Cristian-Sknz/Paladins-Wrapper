package me.skiincraft.api.paladins.internal.logging;

import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.PrintStream;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Properties;

public class SimpleLogger extends MarkerIgnoringBase {

    private static int DEFAULT_LOG_LEVEL;
    private static boolean SHOW_DATETIME;
    private static DateTimeFormatter DATETIME_FORMAT;
    private static boolean ENABLE_UTC_TIME;
    private static Properties properties;
    private static boolean init;

    private final String name;

    SimpleLogger(String name) {
        if (!init){
            init();
        }
        this.name = name;
    }

    private void init(){
        try {
            properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("PaladinsLogger.properties"));
        } catch (Exception ignored){}
        DEFAULT_LOG_LEVEL = parseLevelString(getPropertyAsString("defaultLogLevel", "INFO"));
        SHOW_DATETIME = getPropertyAsBoolean("showDateTime", true);
        DATETIME_FORMAT = getPropertyAsDateTimeFormatter("dateTimeFormat","yyyy-MM-dd HH:mm:ss");
        ENABLE_UTC_TIME = getPropertyAsBoolean("enableUTCTime", true);
        init = true;
    }


    @Override
    public boolean isTraceEnabled() {
        return DEFAULT_LOG_LEVEL <= LocationAwareLogger.TRACE_INT;
    }

    @Override
    public void trace(String msg) {
        if (isTraceEnabled()){
            log(parseLevelString("TRACE"), msg);
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()){
            log(parseLevelString("TRACE"), MessageFormatter.format(format, arg).getMessage());
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()){
            log(parseLevelString("TRACE"), MessageFormatter.format(format, arg1, arg2).getMessage());
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()){
            log(parseLevelString("TRACE"), MessageFormatter.format(format, arguments).getMessage());
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        log(parseLevelString("TRACE"), msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return DEFAULT_LOG_LEVEL <= LocationAwareLogger.DEBUG_INT;
    }

    @Override
    public void debug(String msg) {
        if (isDebugEnabled()){
            log(parseLevelString("DEBUG"), msg);
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (isDebugEnabled()){
            log(parseLevelString("DEBUG"), MessageFormatter.format(format, arg).getMessage());
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (isDebugEnabled()){
            log(parseLevelString("DEBUG"), MessageFormatter.format(format, arg1, arg2).getMessage());
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (isDebugEnabled()){
            log(parseLevelString("DEBUG"), MessageFormatter.format(format, arguments).getMessage());
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (isInfoEnabled()) {
            log(parseLevelString("DEBUG"), msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return DEFAULT_LOG_LEVEL <= LocationAwareLogger.INFO_INT;
    }

    @Override
    public void info(String msg) {
        if (isInfoEnabled()){
            log(parseLevelString("INFO"), msg);
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (isInfoEnabled()){
            log(parseLevelString("INFO"), MessageFormatter.format(format, arg).getMessage());
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (isInfoEnabled()){
            log(parseLevelString("INFO"), MessageFormatter.format(format, arg1, arg2).getMessage());
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnabled()){
            log(parseLevelString("INFO"), MessageFormatter.format(format, arguments).getMessage());
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            log(parseLevelString("INFO"), msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return DEFAULT_LOG_LEVEL <= LocationAwareLogger.WARN_INT;
    }

    @Override
    public void warn(String msg) {
        if (isWarnEnabled()){
            log(parseLevelString("WARN"), msg);
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (isWarnEnabled()){
            log(parseLevelString("WARN"), MessageFormatter.format(format, arg).getMessage());
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnabled()){
            log(parseLevelString("WARN"), MessageFormatter.format(format, arguments).getMessage());
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (isWarnEnabled()){
            log(parseLevelString("WARN"), MessageFormatter.format(format, arg1,arg2).getMessage());
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (isWarnEnabled()){
            log(parseLevelString("WARN"), msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String msg) {
        if (isErrorEnabled()){
            log(parseLevelString("ERROR"), msg);
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (isErrorEnabled()){
            log(parseLevelString("ERROR"), MessageFormatter.format(format, arg).getMessage());
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (isErrorEnabled()){
            log(parseLevelString("ERROR"), MessageFormatter.format(format, arg1, arg2).getMessage());
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnabled()){
            log(parseLevelString("ERROR"), MessageFormatter.format(format, arguments).getMessage());
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            log(parseLevelString("ERROR"), msg, t);
        }
    }

    void log(int level, String message){
        log(level, message, null);
    }

    void log(int level, String message, Throwable t){
        StringBuilder builder = new StringBuilder();
        if (SHOW_DATETIME){
            if (ENABLE_UTC_TIME){
                builder.append('[').append(OffsetDateTime.now(Clock.systemUTC()).format(DATETIME_FORMAT))
                        .append(']');
            } else {
                builder.append('[').append(OffsetDateTime.now().format(DATETIME_FORMAT))
                        .append(']');
            }
            builder.append(" ");
        }
        builder.append(parseLevel(level))
                .append(" - ")
                .append(name)
                .append(" - ")
                .append(message);
        try {
            PrintStream printStream = (isWarnOrError(level)) ? System.err : System.out;
            printStream.println(builder.toString());
            if (Objects.nonNull(t)){
                t.printStackTrace(printStream);
            }
        } catch (Exception ignored){}
    }

    private boolean isWarnOrError(int level) {
        String levelString = parseLevel(level);
        return levelString.equalsIgnoreCase("error") || levelString.equalsIgnoreCase("warn");
    }

    private String parseLevel(int levelInt){
        if (levelInt <= LocationAwareLogger.TRACE_INT){
            return "TRACE";
        } else if (levelInt <= LocationAwareLogger.DEBUG_INT){
            return "DEBUG";
        } else if (levelInt <= LocationAwareLogger.INFO_INT){
            return "INFO";
        } else if (levelInt <= LocationAwareLogger.WARN_INT){
            return "WARN";
        } else {
            return "ERROR";
        }
    }

    private int parseLevelString(String levelInt){
        if (levelInt.equalsIgnoreCase("TRACE")){
            return LocationAwareLogger.TRACE_INT;
        } else if (levelInt.equalsIgnoreCase("DEBUG")){
            return LocationAwareLogger.DEBUG_INT;
        } else if (levelInt.equalsIgnoreCase("INFO")){
            return LocationAwareLogger.INFO_INT;
        } else if (levelInt.equalsIgnoreCase("WARN")){
            return LocationAwareLogger.WARN_INT;
        } else {
            return LocationAwareLogger.ERROR_INT;
        }
    }

    private boolean isInt(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private boolean isBoolean(String value){
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    private boolean isValidFormat(String value){
        try {
            DateTimeFormatter.ofPattern(value);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public String getPropertyAsString(String property, String defaultValue){
        if (properties == null){
            return null;
        }
        String prop = properties.getProperty(property);
        return prop == null ? defaultValue : prop;
    }

    public boolean getPropertyAsBoolean(String property, boolean defaultValue){
        String prop = getPropertyAsString(property, null);
        return prop == null ? defaultValue : isBoolean(prop) ? Boolean.parseBoolean(prop) : defaultValue;
    }

    public DateTimeFormatter getPropertyAsDateTimeFormatter(String property, String defaultValue){
        String prop = getPropertyAsString(property, null);
        DateTimeFormatter defaultDateTime = DateTimeFormatter.ofPattern(defaultValue);
        return prop == null ? defaultDateTime : isValidFormat(prop) ? DateTimeFormatter.ofPattern(prop) : defaultDateTime;
    }
}
