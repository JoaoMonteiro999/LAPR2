package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order implements Serializable, Comparable<Order> {

    private static final long serialVersionUID = 4522537543049477651L;

    private Property property;

    private Integer orderAmount;
    private String clientEmail;

    private boolean orderAccepted;

    private LocalDate dateAccepted;

    public Order(){
    }

    /**
     * This method is the constructor of the order.
     */
    public Order(Property property, Integer orderAmount, String clientEmail){
        setProperty(property);
        setOrderAmount(orderAmount);
        setClientEmail(clientEmail);
    }

    /**
     * Get order's property
     * @return property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Change order's property
     * @param property
     */
    public void setProperty(Property property) {
        if (property == null){
            throw new IllegalArgumentException("Property is invalid!");
        }
        this.property = property;
    }

    /**
     * Get order amount
     * @return
     */
    public Integer getOrderAmount() {
        return orderAmount;
    }

    /**
     * Change order amount value
     * @param orderAmount
     */
    public void setOrderAmount(Integer orderAmount) {
        if (!(orderAmount <= property.getPrice())){
            throw new IllegalArgumentException("Order Amount is invalid!");
        }
        this.orderAmount = orderAmount;
    }

    /**
     * Get client's email in the order
     * @return
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * Change client's email in the order
     * @param clientEmail
     */
    public void setClientEmail(String clientEmail) {
        if (!(clientEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%-]+\\.[A-Za-z]{2,}$"))){
            throw new IllegalArgumentException("Client Email Address is invalid!");
        }
        this.clientEmail = clientEmail;
    }

    /**
     * Check is it is a deal or not (if order is accepted or not)
     * @return true/false
     */
    public boolean isOrderAccepted() {
        return orderAccepted;
    }

    /**
     * Change order acceptance value
     * @param orderAccepted
     */
    public void setOrderAccepted(boolean orderAccepted) {
        this.orderAccepted = orderAccepted;
        this.dateAccepted = LocalDate.now();
    }

    /**
     * Get date that the order is accepted
     * @return date
     */
    public LocalDate getDateAccepted() {
        return dateAccepted;
    }

    /**
     * Change date that the order is accepted
     * @param dateAccepted
     */
    public void setDateAccepted(LocalDate dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    /**
     * This method verifies if two orders are equal based on the client email.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;

        return property.equals(order.property) && orderAmount.equals(order.orderAmount) && clientEmail.equals(order.clientEmail);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Order clone() {
        return new Order(this.property,this.orderAmount,this.clientEmail);
    }

    @Override
    public int compareTo(Order order) {
        Integer area1 = this.property.getArea();
        Integer area2 = order.property.getArea();
        return area1.compareTo(area2);
    }


    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        return "Order:" +
                "\nAmount=" + orderAmount +
                "\nClient Email=" + clientEmail +
                "\nAccepted=" + (orderAccepted ? "Yes" : "No") +
                "\nAccepted Date=" + (dateAccepted == null ? "" : dateAccepted.format(DateTimeFormatter.ISO_DATE) )+
                property;
    }
}
