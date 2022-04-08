package com.example.SpringBootPostgres.service;

public class Helper {

    public static double estimateTime(long dt) {
        return dt/1000000.; // 1_000_000. breaks cobertura
    }
}
