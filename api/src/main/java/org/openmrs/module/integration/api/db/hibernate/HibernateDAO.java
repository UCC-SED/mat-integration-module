/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.integration.api.db.IntegrationDAO;
import org.openmrs.module.integration.api.model.CheckOrder;
import org.openmrs.module.integration.api.model.Prescription;
import org.openmrs.module.integration.api.util.Helper;
import org.openmrs.module.integration.api.util.TempFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

import static org.openmrs.module.integration.api.util.Constant.*;

public class HibernateDAO implements IntegrationDAO {
    protected final Log log;
    private DbSessionFactory sessionFactory;
    private String dataResult=null;
    @Autowired
    private Helper helper;
    @Autowired
    private TempFile tempService;

    public HibernateDAO() {
        this.log = LogFactory.getLog(this.getClass());
    }

    public void setSessionFactory(final DbSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DbSessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Override
    public String getUserPrescription(String orderNumber,String actionStatus) throws ParseException {
        this.log.info((Object)("MAT testing logs"));
        String sql = null;
        String methStatus=null;
        String datastatus=null;
        final int order_id=checkOrderNumber(orderNumber,actionStatus);
        if (order_id!=0 && !actionStatus.equals(order_status_changed)) {
            if (actionStatus.equals(order_status_new)) {
                methStatus="COM";
                sql = "SELECT o.order_id,o.concept_id,od.dose as Quantity,od.duration,o.scheduled_date,pi.identifier,o.order_action,pn.given_name as PatientFirstname,pn.family_name as PatientLastname,ifnull(pn.middle_name,'') as PatientSecondname,p.birthdate,concat(pa.city_village,' ',pa.county_district) as PatientAddress,pa.state_province as PatientCity,pn2.person_id as Doctorid, pn2.given_name as DoctorFirstname, pn2.family_name as DoctorLastname,ifnull(pn2.middle_name,'') as DoctorSecondname FROM orders o  inner join drug_order od on o.order_id=od.order_id inner join person_name pn on pn.person_id=o.patient_id inner join person p on p.person_id=pn.person_id inner join patient_identifier pi on pi.patient_id=p.person_id inner join person_address pa on pa.person_id=pn.person_id inner join users u on u.user_id=o.creator inner join person_name pn2 on pn2.person_id=u.person_id where o.order_id="+order_id+" and o.order_action='"+actionStatus+"';";
            } else if (actionStatus.equals(order_status_cancelled)) {
                methStatus="CAN";
                sql = "SELECT o.order_id as order_oauth,o.previous_order_id as order_id,o.concept_id,od.dose as Quantity,od.duration,o.scheduled_date,pi.identifier,o.order_action,pn.given_name as PatientFirstname,pn.family_name as PatientLastname,ifnull(pn.middle_name,'') as PatientSecondname,p.birthdate,concat(pa.city_village,' ',pa.county_district) as PatientAddress,pa.state_province as PatientCity,pn2.person_id as Doctorid, pn2.given_name as DoctorFirstname, pn2.family_name as DoctorLastname,ifnull(pn2.middle_name,'') as DoctorSecondname FROM orders o  inner join drug_order od on o.order_id=od.order_id inner join person_name pn on pn.person_id=o.patient_id inner join person p on p.person_id=pn.person_id inner join patient_identifier pi on pi.patient_id=p.person_id inner join person_address pa on pa.person_id=pn.person_id inner join users u on u.user_id=o.creator inner join person_name pn2 on pn2.person_id=u.person_id where o.previous_order_id="+order_id+" and o.order_action='"+actionStatus+"';";
            }
            DbSession session =this.sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Prescription.class));
            List data=query.list();

            if (data!=null){
                Prescription getPrescription = (Prescription) data.get(0);
                this.log.info((Object)("MAT duration: " +String.valueOf(getPrescription.getDuration())));
                if(getPrescription.getPatientCity()==null) {
                    getPrescription.setPatientCity("Dar es Salaam");
                    getPrescription.setPatientAddress("Mwananyamala Kinondoni");
                }
                /*
                   limit concept: Because Methameasure Only Use one DIN number  :-Methadone solution concept is 547
                 */
                if(getPrescription.getConcept_id()==helper.DrugConcept()) {

                    for(int i = 1; i <=getPrescription.getDuration();i++) {
                        if(methStatus == "COM") {
                           String dataResult = String.valueOf(getPrescription.getOrder_id()) + ',' + String.valueOf(getPrescription.getOrder_id()+".0"+i) + ',' + String.valueOf(getPrescription.getOrder_id()) + ',' + getPrescription.getQuantity() + ',' + helper.changeIdentifier(getPrescription.getIdentifier()) + ',' + getPrescription.getPatientLastname() + ',' + getPrescription.getPatientFirstname() + ' ' + getPrescription.getPatientSecondname() + ',' + getPrescription.getPatientAddress() + ',' + getPrescription.getPatientCity() + ',' + getPrescription.getDoctorid() + ',' + getPrescription.getDoctorid() + ',' + getPrescription.getDoctorLastname() + ',' + getPrescription.getDoctorFirstname() + ' ' + getPrescription.getDoctorSecondname() + ',' + helper.prescriptionDate(getPrescription.getScheduled_date(),i) + ',' + 1 + ',' + helper.chargeDateFormat(getPrescription.getBirthdate()) + ',' + getPrescription.getConcept_id() + ',' + "MAT" + ',' + methStatus + ",,,,";
                           /*
                           Create files
                            */
                            tempService.createTemp(dataResult);
                        } else {
                            String dataResult = String.valueOf(getPrescription.getOrder_id()) + ',' + String.valueOf(getPrescription.getOrder_id()+".0"+i) + ',' + String.valueOf(getPrescription.getOrder_oauth()) + ',' + getPrescription.getQuantity() + ',' + helper.changeIdentifier(getPrescription.getIdentifier()) + ',' + getPrescription.getPatientLastname() + ',' + getPrescription.getPatientFirstname() + ' ' + getPrescription.getPatientSecondname() + ',' + getPrescription.getPatientAddress() + ',' + getPrescription.getPatientCity() + ',' + getPrescription.getDoctorid() + ',' + getPrescription.getDoctorid() + ',' + getPrescription.getDoctorLastname() + ',' + getPrescription.getDoctorFirstname() + ' ' + getPrescription.getDoctorSecondname() + ',' + helper.prescriptionDate(getPrescription.getScheduled_date(),i) + ',' + 1 + ',' + helper.chargeDateFormat(getPrescription.getBirthdate()) + ',' + getPrescription.getConcept_id() + ',' + "MAT" + ',' + methStatus + ",,,,";
                           /*
                           Create Files
                            */
                            tempService.createTemp(dataResult);
                        }
                }

                    addOrderLog(getPrescription.getOrder_id(), "File created successfully");
                }else{
                    /*
                    Return no data: hence it will not create .meth file
                     */
                     dataResult="No data";
                }

            }
            return  dataResult;

        }else{
            return "No data";
        }
    }

    private int checkOrderNumber(String orderNumber, String status) {
        String sql ="select log.order_id from drug_log_meth log where log.order_id=(select o.order_id from orders o where o.order_number='"+orderNumber+"')";
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CheckOrder.class));
        List data=query.list();
        this.log.info((Object)("MAT sql(Checking OrderNumber): " + "OKAY"));
        if (data!=null && data.size()>0){
            if (status.equals(order_status_cancelled)){
                return getOrderID(orderNumber);
            }else {
                return 0;
            }
        }else {
            return getOrderID(orderNumber);
        }
    }

    private int getOrderID(String orderNumber) {
        String sql ="select o.order_id from orders o where o.order_number='"+orderNumber+"'";
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CheckOrder.class));
        List data=query.list();
        this.log.info((Object)("MAT sql(getting Order ID): " + sql));
        if (data!=null && data.size()>0){
            CheckOrder order=(CheckOrder)data.get(0);
            return order.getOrder_id();
        }else {
            return 0;
        }
    }


    public boolean addOrderLog(int order,String logMessage) {
        String sql ="insert into drug_log_meth (order_id,date_created,log_message,uuid)values("+order+",now(),'"+logMessage+"',uuid())";
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        this.log.info((Object)("MAT sql(Adding Order logs): " + "SUCCESS"));
        if (query.executeUpdate() == 1) {
            return true;
        }else{
            return false;
        }
    }

}