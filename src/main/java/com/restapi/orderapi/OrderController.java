package com.restapi.orderapi;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	private Ordermgmt o;

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/specificorder")
	public Optional<Ordermgmt> getOrder(@RequestParam Integer id){
		return userRepository.findById(id);
	}	
	
	@GetMapping("/addorder")
	public String submitAnOrder(@RequestParam String product, @RequestParam int quantity){
		o = new Ordermgmt();
		o.setProduct(product);
		o.setQuantity(quantity);
	    userRepository.save(o);
		return "submitted";
	}	
	
	@RequestMapping("/deleteorder")
	public String deleteOrder(@RequestParam Integer id){
		userRepository.deleteById(id);		
		return "deleted";
	}
	
	
	@RequestMapping("/updateorder")
	public String updateOrder(@RequestParam Integer id, @RequestParam String product){
		o = new Ordermgmt();
		o.setId(id);
		o.setProduct(product);
		userRepository.save(o);
		return "updated";
	}
	
	@RequestMapping("/vieworders")
	public Iterable<Ordermgmt> getAllOrders(){
		return userRepository.findAll();
	}	
}
