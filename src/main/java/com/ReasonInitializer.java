package com;

import java.util.EnumMap;
import java.util.Map.Entry;

public class ReasonInitializer {
	private EnumMap<RejectionReasons, Long> reasonMap;
	
public ReasonInitializer() {
		
	reasonMap = new EnumMap <RejectionReasons, Long>(RejectionReasons.class);
	
	reasonMap.put(RejectionReasons.LOW_INCOME,								33309900L);
	reasonMap.put(RejectionReasons.NEGATIVE_CREDIT_HISTORY,					33309901L);
	reasonMap.put(RejectionReasons.WORKLESS_BORROWER,						33309902L);
	reasonMap.put(RejectionReasons.LOW_WORK_EXPERIENCE,						33309903L);
	reasonMap.put(RejectionReasons.PROFESSION_OF_BORROWER_IS_NOT_DEMAND,	33309904L);
	reasonMap.put(RejectionReasons.CLOSE_RELATIVE_HAS_BAD_CREDIT_HISTORY,   33309905L);
	reasonMap.put(RejectionReasons.LACK_OF_OWNERSHIP,           			33309906L);   
}

public RejectionReasons getDeniedReason (Long reasonNumber) {
	for (Entry<RejectionReasons, Long> entry : reasonMap.entrySet()) {
        if (entry.getValue().equals(reasonNumber)) {
            return entry.getKey();
        }
    }
return null;
}


	
}
