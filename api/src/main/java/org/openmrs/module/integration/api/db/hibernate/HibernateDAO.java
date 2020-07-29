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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernateDAO implements IntegrationDAO {
	protected Log log;
    private DbSessionFactory sessionFactory;
    private String dataResult=null;
    @Autowired
    private Helper helper;

    public HibernateDAO() {
        this.log = LogFactory.getLog((Class)this.getClass());
    }

    public void setSessionFactory(final DbSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DbSessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Override
    public String getUserPrescription(String orderNumber,String actionStatus) {
        this.log.info((Object)("MAT testing logs "));
        String sql = null;
        boolean checkStatus=this.checkOrderNumber(orderNumber,actionStatus);
        if (checkStatus==true){
            sql ="SELECT o.order_id,o.concept_id,(od.dose*od.duration*od.frequency)as Quantity,o.scheduled_date,pi.identifier,o.order_action,pn.given_name as PatientFirstname,pn.family_name as PatientLastname,p.birthdate,concat(pa.city_village,' ',pa.county_district) as PatientAddress,pa.state_province as PatientCity,pn2.person_id as Doctorid, pn2.given_name as DoctorFirstname, pn2.family_name as DoctorLastname FROM orders o  inner join drug_order od on o.order_id=od.order_id inner join person_name pn on pn.person_id=o.patient_id inner join person p on p.person_id=pn.person_id inner join patient_identifier pi on pi.patient_id=p.person_id inner join person_address pa on pa.person_id=pn.person_id inner join users u on u.user_id=o.creator inner join person_name pn2 on pn2.person_id=u.person_id where o.order_id='"+orderNumber+"';";
        }else{
            sql ="SELECT o.order_id,o.concept_id,(od.dose*od.duration*od.frequency)as Quantity,o.scheduled_date,pi.identifier,o.order_action,pn.given_name as PatientFirstname,pn.family_name as PatientLastname,p.birthdate,concat(pa.city_village,' ',pa.county_district) as PatientAddress,pa.state_province as PatientCity,pn2.person_id as Doctorid, pn2.given_name as DoctorFirstname, pn2.family_name as DoctorLastname FROM orders o  inner join drug_order od on o.order_id=od.order_id inner join person_name pn on pn.person_id=o.patient_id inner join person p on p.person_id=pn.person_id inner join patient_identifier pi on pi.patient_id=p.person_id inner join person_address pa on pa.person_id=pn.person_id inner join users u on u.user_id=o.creator inner join person_name pn2 on pn2.person_id=u.person_id where o.order_id='"+orderNumber+"';";
        }
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Prescription.class));
        List data=query.list();
        this.log.info((Object)("MAT sql: " + sql));
        if (data!=null){
            Prescription getPrescription = (Prescription) data.get(0);
            dataResult=String.valueOf(getPrescription.getOrder_id())+','+String.valueOf(getPrescription.getOrder_id())+','+String.valueOf(getPrescription.getOrder_id())+','+getPrescription.getQuantity()+','+getPrescription.getIdentifier().replaceAll("MAT", "")+','+getPrescription.getPatientLastname()+','+getPrescription.getPatientFirstname()+','+getPrescription.getPatientAddress()+','+getPrescription.getPatientCity()+','+getPrescription.getDoctorid()+','+getPrescription.getDoctorid()+','+getPrescription.getDoctorLastname()+','+getPrescription.getPatientFirstname()+','+helper.chargeDateFormat(getPrescription.getScheduled_date())+','+1+','+helper.chargeDateFormat(getPrescription.getBirthdate())+','+getPrescription.getConcept_id()+','+"MAT"+','+"COM";
            return  dataResult;
        }else {
            return "No data";
        }
    }

    private boolean checkOrderNumber(String orderNumber, String orderStatus) {
        String sql ="";
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CheckOrder.class));
        List data=query.list();
        this.log.info((Object)("MAT sql(Checking OrderNumber): " + sql));
        if (data.size()>0){
            return true;
        }else {
            return false;
        }
    }


    public String addOrderLog(String orderNumber, String logType, String logMessage) {
        String sql ="";
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        this.log.info((Object)("MAT sql(Adding Order logs): " + sql));
        if (query.executeUpdate() == 1) {
            return "success";
        }else{
            return "failed";
        }
    }

}