package it.unisa.diem.SEGroup9;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TypeConstant {
    // Constants representing trigger types
    public static final Map<Integer, String> TRIGGERTYPES_CONSTANTS;

    // Constants representing action types
    public static final Map<Integer, String> ACTIONTYPES_CONSTANTS;

    static {
        // Creation of the map for trigger types
        Map<Integer, String> triggerTypesMap = new HashMap<>();
        triggerTypesMap.put(0, "timetrigger");
        triggerTypesMap.put(1, "dayoftheweektrigger");
        triggerTypesMap.put(2, "dayofmonthtrigger");
        triggerTypesMap.put(3, "datetrigger");
        triggerTypesMap.put(4, "fileexiststrigger");
        triggerTypesMap.put(5, "filesizetrigger");
        triggerTypesMap.put(6, "exitstatustrigger");

        // Creation of the map for action types
        Map<Integer, String> actionTypesMap = new HashMap<>();
        actionTypesMap.put(0, "audioaction");
        actionTypesMap.put(1, "dialogaction");
        actionTypesMap.put(2, "appendfileaction");
        actionTypesMap.put(3, "copyfileaction");
        actionTypesMap.put(4, "movefileaction");
        actionTypesMap.put(5, "deletefileaction");
        actionTypesMap.put(6, "executeexternalprogramaction");

        // Assigning immutable maps to constants
        TRIGGERTYPES_CONSTANTS = Collections.unmodifiableMap(triggerTypesMap);
        ACTIONTYPES_CONSTANTS = Collections.unmodifiableMap(actionTypesMap);
    }
}
