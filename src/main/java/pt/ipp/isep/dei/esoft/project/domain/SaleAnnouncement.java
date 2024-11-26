package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;

public class SaleAnnouncement implements TodayDate, Serializable {

    private static final long serialVersionUID = 6833930017724886113L;

    private String dateAnnouncement;
    private String dateSale;
    private Commission commission;
    private Property property;
    private Integer contractDuration;
    private Employee agent;

    public SaleAnnouncement(){
    }

    public SaleAnnouncement(Property property){
        this.property=property;

    }
    public SaleAnnouncement(Property property,Commission commission){
        this.property=property;
        this.commission=commission;
        this.dateAnnouncement=todayDate();
    }

    public SaleAnnouncement(Property property,Commission commission,String date){
        this.property=property;
        this.commission=commission;
        this.dateAnnouncement=date;
    }
    public SaleAnnouncement(Property property,Commission commission,Integer contractDuration,String dateAnnouncement){
        this.property=property;
        this.commission=commission;
        this.contractDuration = contractDuration;
        this.dateAnnouncement = dateAnnouncement;
    }
    public SaleAnnouncement(Property property,Commission commission,Integer contractDuration){
        this.property=property;
        this.commission=commission;
        this.contractDuration = contractDuration;
        this.dateAnnouncement = todayDate();
    }


    public String getDateAnnouncement() {
        return dateAnnouncement;
    }

    public void setDateAnnouncement(String dateAnnouncement) {
        this.dateAnnouncement = dateAnnouncement;
    }

    public String getDateSale() {
        return dateSale;
    }

    public void setDateSale(String dateSale) {
        this.dateSale = dateSale;
    }

    public Property getProperty() {
        return property;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(Integer contractDuration) {
        this.contractDuration = contractDuration;
    }

    public Employee getAgent() {
        return agent;
    }

    public void setAgent(Employee agent) {
        this.agent = agent;
    }

    public SaleAnnouncement clone(){
        return new SaleAnnouncement(getProperty(),getCommission(), getDateAnnouncement());   //getDate was missing!!!!!!!
    }


}
