package it.unisa.diem.SEGroup9;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class TypeConstant {
    public static final Map<Integer, String> TRIGGERTYPES_CONSTANTS;
    static {
        // Creazione del dizionario
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "timetrigger");
       
        
        TRIGGERTYPES_CONSTANTS = Collections.unmodifiableMap(map);
    }

}
