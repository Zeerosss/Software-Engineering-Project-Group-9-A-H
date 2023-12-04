package it.unisa.diem.SEGroup9;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class TypeConstant {
    public static final Map<Integer, String> TRIGGERTYPES_CONSTANTS;
    public static final Map<Integer, String> ACTIONTYPES_CONSTANTS;
    static {
        // Creazione del dizionario
        Map<Integer, String> map1= new HashMap<>();
        Map<Integer, String> map2= new HashMap<>();

        map1.put(0, "timetrigger");
        map1.put(1,"exitstatustrigger");
        
        map2.put(0,"audioaction");
        map2.put(1,"dialogaction");
        map2.put(2,"copyfileaction");
        map2.put(3,"movefileaction");
        map2.put(4,"deletefileaction");
        map2.put(5,"appendfileaction");

       
        
        TRIGGERTYPES_CONSTANTS = Collections.unmodifiableMap(map1);
        ACTIONTYPES_CONSTANTS = Collections.unmodifiableMap(map2);
    }

}
