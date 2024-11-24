package com.example.program.util.exception;

import java.util.UUID;

public class CodeFoundException extends RuntimeException {
    public CodeFoundException(UUID code) {
        super("Not found Code: " + code);
    }
}

