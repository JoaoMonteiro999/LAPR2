package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

public class VisitScheduleRequestController {

    private AuthenticationRepository authenticationRepository;
    private Repositories repositories;
    private PropertiesRepository propertiesRepository;
    private SaleAnnouncementRepository saleAnnouncementRepository;

    private VisitScheduleRequestRepository visitScheduleRequestRepository;
    private ClientRepository clientRepository;

    public VisitScheduleRequestController(){
        this.repositories = Repositories.getInstance();

        this.propertiesRepository = repositories.getPropertiesRepository();
        this.saleAnnouncementRepository = repositories.getSaleAnnouncementRepository();
        this.visitScheduleRequestRepository = repositories.getVisitScheduleRequestRepository();
        this.clientRepository = repositories.getClientRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
    }

    public VisitScheduleRequestController(PropertiesRepository propertiesRepository,
                                          SaleAnnouncementRepository saleAnnouncementRepository,
                                          VisitScheduleRequestRepository visitScheduleRequestRepository,
                                          AuthenticationRepository authenticationRepository,
                                          ClientRepository clientRepository) {

        this.propertiesRepository = propertiesRepository;
        this.saleAnnouncementRepository = saleAnnouncementRepository;
        this.visitScheduleRequestRepository = visitScheduleRequestRepository;
        this.authenticationRepository = authenticationRepository;
        this.clientRepository = clientRepository;
    }

    public List<Property> getListOfPropertiesSorted(){
        List<SaleAnnouncement> announcements = saleAnnouncementRepository.getAnnouncementsListSorted();
        List<Property> properties = new ArrayList<>();

        for(SaleAnnouncement announcement: announcements){
            properties.add(announcement.getProperty());
        }

        return properties;
    }


    public void submitVisitScheduleRequest(Property property, List<ScheduleSlot> scheduleSlots) {
        Email userID = authenticationRepository.getCurrentUserSession().getUserId();
        Client client = clientRepository.getClient(userID);

        VisitScheduleRequest v = new VisitScheduleRequest(property, client, scheduleSlots);

        visitScheduleRequestRepository.addVisitScheduleRequest(v);
    }
}
