package com.example.teamleaguebagit;

public class lista_ligas {

    String nombre_liga;
    String participantes;

    public lista_ligas(){}

    public lista_ligas(String nombre_liga, String p) {
        this.nombre_liga = nombre_liga;
        this.participantes = p;
    }

    public String getNombre_liga() {
        return nombre_liga;
    }

    public void setNombre_liga(String nombre_liga) {
        this.nombre_liga = nombre_liga;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }
}
