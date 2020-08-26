package org.openmrs.module.integration.api.util;

import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Helper {

  public   String chargeDateFormat(Date date){
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

  public  String changeIdentifier (String ID){
        return ID.replaceFirst(".*?(\\d+).*", "$1");

    };

  public int DrugConcept(){
       String  concept= Context.getAdministrationService().getGlobalPropertyValue("MAT.meth.DIN","");
       return Integer.valueOf(concept);
   }
}
