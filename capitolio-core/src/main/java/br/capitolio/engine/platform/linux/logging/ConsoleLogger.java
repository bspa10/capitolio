package br.capitolio.engine.platform.linux.logging;

import br.capitolio.engine.logging.AbstractLogger;
import br.capitolio.engine.logging.LogLevel;
import br.capitolio.engine.logging.Logger;
import br.capitolio.engine.logging.LoggingSettings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class ConsoleLogger extends AbstractLogger implements Logger {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss.SSS", Locale.getDefault());

    private ConsoleLogger(){}

    private void log(String level, String message, Object... args) {
        System.out.printf(
                "%s %s %s [%s] - %s%n",
                dtf.format(LocalDateTime.now()),
                level,
                loggerName,
                Thread.currentThread().getName(),
                message.formatted(args)
        );
    }

    @Override
    public void fatal(String message, Object... args) {
        log(AnsiColor.RED + LogLevel.FATAL.formated() + AnsiColor.RESET, message, args);
    }

    @Override
    public void error(String message, Object... args) {
        log(AnsiColor.RED + LogLevel.ERROR.formated() + AnsiColor.RESET, message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        if (LoggingSettings.isWarning())
            log(AnsiColor.YELLOW + LogLevel.WARNING.formated() + AnsiColor.RESET, message, args);
    }

    @Override
    public void info(String message, Object... args) {
        if (LoggingSettings.isInfo())
            log(AnsiColor.GREEN + LogLevel.INFO.formated() + AnsiColor.RESET, message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        if (LoggingSettings.isDebug())
            log(AnsiColor.BLUE + LogLevel.DEBUG.formated() + AnsiColor.RESET, message, args);
    }

    @Override
    public void trace(String message, Object... args) {
        if (LoggingSettings.isTrace())
            log(AnsiColor.WHITE + LogLevel.TRACE.formated() + AnsiColor.RESET, message, args);
    }
}
