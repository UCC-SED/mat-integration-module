package org.openmrs.module.integration.api.util;

import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

   public String prescriptionDate(Date firstDate, int numberOfDays) throws ParseException {
       //Specifying date format that matches the given date
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
       Calendar c = Calendar.getInstance();
       //Setting the date to the given date
       c.setTime(sdf.parse(chargeDateFormat(firstDate)));
       //Number of Days to add
       c.add(Calendar.DAY_OF_MONTH, numberOfDays-1);
       //Date after adding the days to the given date
       String newDate = sdf.format(c.getTime());
       return newDate;
   }
}
