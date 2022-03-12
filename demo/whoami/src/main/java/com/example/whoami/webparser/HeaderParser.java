package com.example.whoami.webparser;

import java.util.List;

public interface HeaderParser<R extends Header> {
    public List<R> parseHeaders();
}
