package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestsRepository {

    /**
     * List of requests
     */
    private final List<Request> requests = new ArrayList<>();

    /**
     * Method to add a request to the list of requests
     * @param request
     * @return
     */
    public Optional<Request> add(Request request) {
        Optional<Request> newRequest = Optional.empty();
        boolean operationSuccess = false;

        if (validateRequest(request)) {
            newRequest = Optional.of(request.clone());
            operationSuccess = requests.add(newRequest.get());
        }

        if (!operationSuccess) {
            newRequest = Optional.empty();
        }

        return newRequest;
    }

    /**
     * Method to check if the request is already in the requests list
     * @param request
     * @return true/false
     */
    private boolean validateRequest(Request request) {
        boolean isValid = !requests.contains(request);

        return isValid;
    }

    /**
     * Get the requests list
     * @return list of requests
     */
    public List<Request> getRequestList(){
        return requests;
    }


}
