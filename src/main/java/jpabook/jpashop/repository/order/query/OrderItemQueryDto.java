package jpabook.jpashop.repository.order.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderItemQueryDto {

    @JsonIgnore
    private Long orderId;

    private String name;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(Long orderId, String name, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
