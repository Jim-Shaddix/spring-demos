package com.example.whoami.webparser;

import java.util.List;

public interface HeaderParser<R> {
    public List<R> parseHeaders();
}
