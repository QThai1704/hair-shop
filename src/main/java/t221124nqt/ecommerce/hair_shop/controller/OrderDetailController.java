package t221124nqt.ecommerce.hair_shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import t221124nqt.ecommerce.hair_shop.service.imp.IOrderDetailService;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailController {
    private final IOrderDetailService orderDetailService;

    public OrderDetailController(IOrderDetailService orderDetailService){
        this.orderDetailService = orderDetailService;
    }
}
