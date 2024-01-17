package com.example.h2;

import java.util.UUID;

public class GenId {
    public static Long getLongId() {
        return UUID.randomUUID().getLeastSignificantBits();
    }

    public static String getStringId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
