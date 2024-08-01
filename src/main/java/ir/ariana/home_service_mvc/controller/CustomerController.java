package ir.ariana.home_service_mvc.controller;

import ir.ariana.home_service_mvc.dto.*;
import ir.ariana.home_service_mvc.enums.OfferStatus;
import ir.ariana.home_service_mvc.enums.OrderStatus;
import ir.ariana.home_service_mvc.mapper.CommentMapper;
import ir.ariana.home_service_mvc.mapper.CustomerMapper;
import ir.ariana.home_service_mvc.mapper.OfferMapper;
import ir.ariana.home_service_mvc.mapper.OrderMapper;
import ir.ariana.home_service_mvc.model.*;
import ir.ariana.home_service_mvc.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final SubServiceService subServiceService;
    private final OfferService offerService;
    private final CommentService commentsService;
    private final CommentService commentService;


    @PostMapping("registerCustomer")
    public ResponseEntity<CustomerReturn> registerCustomer(@Valid @RequestBody CustomerSaveRequest request) {
        Customer mappedCustomer = CustomerMapper.INSTANCE.customerSaveRequestToModel(request);
        Customer savedCustomer = customerService.save(mappedCustomer);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelCustomerToSaveResponse(savedCustomer),
                HttpStatus.CREATED);
    }

    @GetMapping("customer_SignIn")
    public ResponseEntity<CustomerReturn> customerSignIn(@RequestParam String email, String password) {
        Customer customer = customerService.singInCustomer(email, password);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelCustomerToSaveResponse(customer),
                HttpStatus.FOUND);
    }

    @GetMapping("find_By_CustomerId")
    public ResponseEntity<CustomerReturn> findByCustomerId(@RequestParam Long id) {
        Customer customer = customerService.findById(id);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelCustomerToSaveResponse(customer),
                HttpStatus.FOUND);
    }

    @GetMapping("update_Customer_Password")
    public String updateCustomerPassword(@RequestParam Long id, String oldPassword, String newPassword,
                                         String confirmPassword) {
        Customer customer = customerService.findById(id);
        Customer updatedCustomer = customerService.UpdatePassword(oldPassword, newPassword, confirmPassword, customer);
        return updatedCustomer.getPassword();
    }


    @DeleteMapping("delete_customer")
    public String removeCustomer(@RequestParam Long id) {
        customerService.deleteCustomer(id);
        return "customer and All relations have been deleted";
    }

    @DeleteMapping("delete_order")
    public String removeOrder(@RequestParam Long id) {
        orderService.removeOrder(id);
        return "order and All relations have been deleted";
    }

    @PostMapping("make_Order")
    public ResponseEntity<OrderReturn> makeOrder(@Valid @RequestBody OrderSaveRequest orderSaveRequest) {
        Order mappedOrder = OrderMapper.INSTANCE.orderSaveRequestToModel(orderSaveRequest);
        Order savedOrder = orderService.addOrderByCustomer(mappedOrder, orderSaveRequest.subServiceId(),
                orderSaveRequest.customerId());
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelOrderToSaveResponse(savedOrder), HttpStatus.CREATED);
    }


    @GetMapping("allOrders_OfCustomer")
    public List<OrderReturn> allOrdersOfCustomer(@RequestParam Long id) {
        Customer customer = customerService.findById(id);
        List<Order> orders = orderService.findCustomerOrders(customer);
        return OrderMapper.INSTANCE.listOrderToSaveResponse(orders);
    }

    @GetMapping("find_By_OrderId")
    public ResponseEntity<OrderReturn> findByOrderId(@RequestParam Long id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelOrderToSaveResponse(order),
                HttpStatus.FOUND);
    }

    @GetMapping("findBy_SubServiceOrders")
    public List<OrderReturn> findBySubServiceOrders(@RequestParam Long id) {
        SubService subService = subServiceService.findById(id);
        List<Order> orders = orderService.findSubServiceOrders(subService);
        return OrderMapper.INSTANCE.listOrderToSaveResponse(orders);
    }

    @GetMapping("findBy_OrderStatus")
    public List<OrderReturn> findByOrderStatus(@RequestParam OrderStatus orderStatus) {
        List<Order> orders = orderService.findByOrderStatus(orderStatus);
        return OrderMapper.INSTANCE.listOrderToSaveResponse(orders);
    }


    @GetMapping("show_offers_ByPrice")
    public List<OfferReturn> showOffersByPrice(@RequestParam Long id) {
        Order order = orderService.findById(id);
        List<Offer> offers = offerService.setOffersByPrice(order);
        return OfferMapper.INSTANCE.listOfferToSaveResponse(offers);
    }

    @GetMapping("show_offers_BySpecialistScore")
    public List<OfferReturn> showOffersBySpecialistScore(@RequestParam Long id) {
        Order order = orderService.findById(id);
        List<Offer> offers = offerService.setOffersBySpecialistScore(order);
        Collections.reverse(offers);
        return OfferMapper.INSTANCE.listOfferToSaveResponse(offers);
    }

    @GetMapping("accept_Offer")
    public OrderReturn acceptOffer(@RequestParam Long orderId, Long offerId) {
        Order order = orderService.findById(orderId);
        Offer offer = offerService.findById(offerId);
        offerService.updateOfferStatus(OfferStatus.ACCEPTED, offer);
        Order acceptedOrder = orderService.updateOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION, order);
        return OrderMapper.INSTANCE.modelOrderToSaveResponse(acceptedOrder);
    }

    @PatchMapping("make_Order_ToCome")
    public OrderReturn makeOrderToCome(@RequestParam Long id) {
        Order order = orderService.findById(id);
        Order orderToCome = orderService.makeOrderToCome(order);
        return OrderMapper.INSTANCE.modelOrderToSaveResponse(orderToCome);
    }

    @GetMapping("make_Order_Done")
    public OrderReturn makeOrderDone(@RequestParam Long id) {
        Order order = orderService.findById(id);
        Order doneOrder = orderService.makeOrderDone(order);
        return OrderMapper.INSTANCE.modelOrderToSaveResponse(doneOrder);
    }

    @PostMapping("save_Comment")
    public ResponseEntity<CommentReturn> saveComment(@Valid @RequestBody CommentSaveRequest commentSaveRequest) {
        Comment mappedComment = CommentMapper.INSTANCE.INSTANCE.commentSaveRequestToModel(commentSaveRequest);
        Comment savedComment = commentService.saveComment(mappedComment);
        commentsService.addScoreToSpecialist(savedComment);
        return new ResponseEntity<>(CommentMapper.INSTANCE.modelCommentToSaveResponse(savedComment),
                HttpStatus.CREATED);
    }

    @GetMapping("/show-my-credit")
    public Long showCustomerCredit(@RequestParam Long id) {
        Customer customer = customerService.findById(id);
        return customer.getCredit();
    }

    @PatchMapping("add_credit")
    public ResponseEntity<CustomerReturn> addCredit(@RequestParam Long id, Long credit) {
        Customer customer = customerService.findById(id);
        Customer updatedCustomer = customerService.addCredit(customer,credit);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelCustomerToSaveResponse(updatedCustomer),
                HttpStatus.OK);
    }

}
