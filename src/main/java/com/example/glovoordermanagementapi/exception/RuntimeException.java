package com.example.glovoordermanagementapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Getter
public class RuntimeException extends java.lang.RuntimeException {
    private String message;
}
