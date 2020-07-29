package org.openmrs.module.integration.api.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Helper {

  public   String chargeDateFormat(Date date){
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    int changeIdentifier (String ID){
        return Integer.parseInt(ID.replaceAll("[\\D]", ""));
    };
}
