package com.innowave.cursomc.services.validations.utils;

public class PT {

    public static boolean isValidPersonalNif(String nif)
    {
        return (nif.matches("^[2][0-9]{5,}$"));
    }

    public static boolean isValidCorplNif(String nif)
    {
        return (nif.matches("^[5][0-9]{5,}$"));
    }
}
