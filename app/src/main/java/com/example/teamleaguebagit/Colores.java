package com.example.teamleaguebagit;

import java.util.HashMap;
import java.util.Map;

public class Colores {
    static HashMap<String,String> mapa =initMap();


    static public HashMap<String,String> initMap(){
        HashMap<String,String> mapa =new HashMap<>();
        mapa.put("ATL","#b93a3e");
        mapa.put("BKN", "#505050");
        mapa.put("BOS", "#006748");
        mapa.put("CHA","#006478");
        mapa.put("CHI", "#b0002d");
        mapa.put("CLE","#5b1c33");
        mapa.put("DAL","#003f94");
        mapa.put("DEN","#e0a710");
        mapa.put("DET","#aa001a");
        mapa.put("GS","#004d98");
        mapa.put("HOU","#b00023");
        mapa.put("IND","#e09d12");
        mapa.put("LAC","#00246c");
        mapa.put("LAL","#370765");
        mapa.put("MEM","#3f588b");
        mapa.put("MIA","#84001a");
        mapa.put("MIL","#145b2f");
        mapa.put("MIN","#0f4374");
        mapa.put("NO","#143f70");
        mapa.put("NY","#d76608");
        mapa.put("OKC","#005299");
        mapa.put("ORL","#005299");
        mapa.put("PHI","#004d8e");
        mapa.put("PHX","#c74200");
        mapa.put("POR","#b10018");
        mapa.put("SA","#a6b0b6");
        mapa.put("SAC","#46176d");
        mapa.put("TOR","#b00023");
        mapa.put("UTA","#e18600");
        mapa.put("WSH","#c50019");
        return mapa;

    }
}
