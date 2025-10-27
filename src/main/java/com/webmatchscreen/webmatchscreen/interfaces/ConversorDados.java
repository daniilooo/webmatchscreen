package com.webmatchscreen.webmatchscreen.interfaces;

public interface ConversorDados {
    <T> T castData(String json, Class<T> classe);
}
