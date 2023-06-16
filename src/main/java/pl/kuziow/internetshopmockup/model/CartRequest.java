package pl.kuziow.internetshopmockup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    private String customerId;
    private Map<String, Integer> products;
    private PaymentRequest payment;

}
