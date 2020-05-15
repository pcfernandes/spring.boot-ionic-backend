package com.innowave.cursomc.domain.enums;

public enum ClientType {

    PHYSICAL_PERSON(1,"Physical person"),
    LEGAL_PERSON(2,"Legal person")
    ;

    private int cod;
    private String description;

    ClientType(int cod, String description){
        this.cod=cod;
        this.description = description;
    }

    public int getCod(){
        return cod;
    }
    public String getDescription(){
        return description;
    }

    public static ClientType toEnum(Integer cod){
        if(cod==null){
            return null;
        }
        for (ClientType x : ClientType.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid Id " + cod);
    }
}
