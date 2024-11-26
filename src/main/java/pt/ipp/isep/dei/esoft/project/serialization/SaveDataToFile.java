package pt.ipp.isep.dei.esoft.project.serialization;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class SaveDataToFile implements Runnable{

    /**
     * Run method
     */
    public void run(){
        saveEmployee();
        saveProperty();
        saveClient();
        saveRequest();
        saveAnnouncement();
        saveOrder();
        saveAgency();
        saveRole();
    }

    /**
     * Write binary employee file
     */
    private void saveEmployee(){
        Object obj = (Repositories.getInstance().getEmployeeRepository().getEmployees());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/employee.ser", obj);
    }

    /**
     * Write binary property file
     */
    private void saveProperty(){
        Object obj = (Repositories.getInstance().getPropertiesRepository().getPropertiesList());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/property.ser", obj);
    }

    /**
     * Write binary client file
     */
    private void saveClient(){
        Object obj = (Repositories.getInstance().getClientRepository().getClients());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/client.ser", obj);
    }

    /**
     * Write binary request file
     */
    private void saveRequest(){
        Object obj = (Repositories.getInstance().getRequestsRepository().getRequestList());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/request.ser", obj);
    }

    /**
     * Write binary announcement file
     */
    private void saveAnnouncement(){
        Object obj = (Repositories.getInstance().getSaleAnnouncementRepository().getAnnouncementsListSorted());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/announcement.ser", obj);
    }

    /**
     * Write binary order file
     */
    private void saveOrder(){
        Object obj = (Repositories.getInstance().getOrderRepository().getOrders());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/order.ser", obj);
    }

    /**
     * Write binary agency file
     */
    private void saveAgency(){
        Object obj = (Repositories.getInstance().getAgencyRepository().getAgencies());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/agency.ser", obj);
    }

    /**
     * Write binary role file
     */
    private void saveRole(){
        Object obj = (Repositories.getInstance().getRoleRepository().getRoles());
        ReadAndWriteBinaryFile.writeBinaryFile("serialization/role.ser", obj);
    }
}
