package com.tangem;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0016\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/tangem/Log;", "", "()V", "loggerList", "", "Lcom/tangem/LoggerInterface;", "addLogger", "", "logger", "e", "logTag", "", "message", "i", "removeLogger", "v", "write", "Lcom/tangem/LogMessage;", "tangem-core"})
public final class Log {
    private static final java.util.List<com.tangem.LoggerInterface> loggerList = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.tangem.Log INSTANCE = null;
    
    public final void i(@org.jetbrains.annotations.NotNull()
    java.lang.String logTag, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void e(@org.jetbrains.annotations.NotNull()
    java.lang.String logTag, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void v(@org.jetbrains.annotations.NotNull()
    java.lang.String logTag, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void write(@org.jetbrains.annotations.NotNull()
    com.tangem.LogMessage message) {
    }
    
    public final void addLogger(@org.jetbrains.annotations.NotNull()
    com.tangem.LoggerInterface logger) {
    }
    
    public final void removeLogger(@org.jetbrains.annotations.NotNull()
    com.tangem.LoggerInterface logger) {
    }
    
    private Log() {
        super();
    }
}