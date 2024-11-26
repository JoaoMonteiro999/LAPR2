package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();

    RoleRepository roleRepository = new RoleRepository();
    AgencyRepository agencyRepository = new AgencyRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();

    AuthenticationRepository authenticationRepository = new AuthenticationRepository();

    SaleAnnouncementRepository saleAnnouncementRepository = new SaleAnnouncementRepository();
    PropertiesRepository propertiesRepository = new PropertiesRepository();
    RequestsRepository requestsRepository = new RequestsRepository();

    ClientRepository clientRepository = new ClientRepository();

    OrderRepository orderRepository = new OrderRepository();
    VisitScheduleRequestRepository visitScheduleRequestRepository = new VisitScheduleRequestRepository();

    CSVFileRepository csvFileRepository = new CSVFileRepository();

    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public AgencyRepository getAgencyRepository() {
        return agencyRepository;
    }

    public EmployeeRepository getEmployeeRepository(){return employeeRepository;}

    public SaleAnnouncementRepository getSaleAnnouncementRepository() {
        return saleAnnouncementRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public PropertiesRepository getPropertiesRepository() {
        return propertiesRepository;
    }

    public RequestsRepository getRequestsRepository() {
        return requestsRepository;
    }

    public OrderRepository getOrderRepository(){return orderRepository;}

    public VisitScheduleRequestRepository getVisitScheduleRequestRepository() {
        return visitScheduleRequestRepository;
    }

    public CSVFileRepository getCSVFileRepository(){return csvFileRepository;}
}
