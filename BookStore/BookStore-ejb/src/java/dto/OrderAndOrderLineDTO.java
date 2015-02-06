/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Phat Huy
 */
public class OrderAndOrderLineDTO implements Serializable {

    private OrderDTO orderDTO;
    private ArrayList orderLinesList;

    public OrderAndOrderLineDTO() {
    }

    public OrderAndOrderLineDTO(OrderDTO orderDTO, ArrayList orderLinesList) {
        this.orderDTO = orderDTO;
        this.orderLinesList = orderLinesList;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public ArrayList getOrderLinesList() {
        return orderLinesList;
    }

    public void setOrderLinesList(ArrayList orderLinesList) {
        this.orderLinesList = orderLinesList;
    }
}
