package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Commission;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RequestsRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListRequestController {

    private static Commission commission;
    private RequestsRepository repository= new RequestsRepository();
    private PublishSaleAnnouncementController announcementController = new PublishSaleAnnouncementController();

    public void ListRequests(){

        System.out.println("----------------------------------------------\n" +
                           "              List Requests Menu            \n" +
                           "----------------------------------------------");

        List<Request> list = getRequestList(); //gets the list of requests

        if (!list.isEmpty()){

            Collections.sort(list); //order list by date


            System.out.println("\nList of Requests:");
            int i=1;
            for(Request request: list){
                Property property = request.getProperty();
                String date = request.getDate();
                System.out.println(i+". "+date+" "+ property);
                i++;
            }
            System.out.println();
            System.out.println("0 - Cancel");

        }else System.out.println("\nNo requests!\n");
    }

    public void ChooseOne(){
        Scanner input = new Scanner(System.in);

        List<Request> list = getRequestList(); //gets the list of requests

        if (!list.isEmpty()) {

            System.out.println("Choose one request to accept or delete:");
            int requestNum= input.nextInt();

            if (requestNum > 0 && requestNum <= (getRequestList().size())) {
                Request request = FindRequest(requestNum, list);
                System.out.println("Choose one option about the request:\n\t1.Accept request\n\t2.Delete request\n");
                System.out.println("0 - Cancel");
                int option = input.nextInt();
                if (option == 1) {
                    AcceptRequest(request);
                    if (commission!=null){
                        DeleteRequest(request, list);
                    }
                    ListRequests();
                    ChooseOne();
                } else if (option == 2) {
                    DeleteRequest(request, list);
                    Justification();
                    ListRequests();
                    ChooseOne();
                } else if (option == 0) {
                    ListRequests();
                    ChooseOne();
                } else throw new IllegalArgumentException("\nNo such option\n");
            } else if (requestNum == 0) {
            } else throw new IllegalArgumentException("No such option");


        }
    }

    private void Justification() {

        System.out.println("\nWrite a justification to the request delete:");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        System.out.println();

    }


    public Request FindRequest(int requestNum,List<Request> list){
        int cont=1;
        for (Request i : list){
            if (cont==requestNum){
                return i;
            }
            cont++;
        }
        return null;


    }

    public void AcceptRequest(Request request){
        commission = askCommission();
        if (commission !=null){
            announcementController.createSaleAnnouncement(request.getProperty(),commission);
            System.out.println("The add has been created!");

        }
    }

    public void DeleteRequest(Request request,List<Request> list){
        list.remove(request);
        System.out.println("The request has been removed from the list!");
    }






    private Commission askCommission(){
        Scanner input = new Scanner(System.in);

        List<String> optionList = List.of("Fixed","Percentage");

        int commissionType = Utils.showAndSelectIndex(optionList,"\nSelect commission type:");

        if (commissionType == 0){
            int value = announcementController.requestInt("commission value (in USD)");
            return new Commission(false,value);
        } else if (commissionType == 1) {
            int percentage = announcementController.requestInt("commission percentage");
            return new Commission(true,percentage);
        } else if (commissionType == -1) {
            return null;
        } else {
            throw new IllegalArgumentException("Invalid type!");
        }

    }

    public ListRequestController(){
        this.repository = Repositories.getInstance().getRequestsRepository();

    }

    public List<Request> getRequestList(){
        return repository.getRequestList();
    }

    public RequestsRepository getRepository() {
        return repository;
    }

    public void setRepository(RequestsRepository repository) {
        this.repository = repository;
    }

    public static Commission getCommission() {
        return commission;
    }

    public static void setCommission(Commission commission) {
        ListRequestController.commission = commission;
    }

    public PublishSaleAnnouncementController getAnnouncementController() {
        return announcementController;
    }

    public void setAnnouncementController(PublishSaleAnnouncementController announcementController) {
        this.announcementController = announcementController;
    }
}
