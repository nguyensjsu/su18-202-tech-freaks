package com.app.starbucks.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import starbucks.*;

import com.starbucks.controller.IOrderAndItem;
import com.starbucks.controller.OrderAndItemManager;
import com.starbucks.library.*;
import com.starbucks.model.ItemInfo;
import com.starbucks.model.OrderResponse;
import com.starbucks.model.OrderedItemsInfo;

import java.sql.Connection;

@RestController
public class StarbucksController {
	starbucks.ManagePayments mp = new starbucks.ManagePayments();
	List<starbucks.Payment> paymentsList = new ArrayList<>();

	// Author - Harini Balakrishnan
	// Database credentials and initialize classes from AddCard JAR file
	com.starbucks.library.MySqlConnection mysql = new com.starbucks.library.MySqlConnection();
	String url = "jdbc:mysql://dbinstancestarbucks.cdw04dgws34h.us-west-1.rds.amazonaws.com:3306/dbstarbucks"; // Enter the AWS RDS endpoint here
	String username = "root"; // Enter the AWS RDS username here
	String password = "techfreaks"; // Enter the AWS RDS password here
	Connection connection = mysql.getConnection(url, username, password);
	com.starbucks.library.MyCards mycards = new com.starbucks.library.MyCards();
	com.starbucks.library.AddCard addcard = new com.starbucks.library.AddCard();
	com.starbucks.library.Card card = new com.starbucks.library.Card();
	starbucks.ConnectionManager con = new starbucks.ConnectionManager(url, username, password);
	
	//@Supreetha . TO call Rest api end points.
	RestTemplate restTemplate = new RestTemplate();
	
	OrderResponse orderDetails=new OrderResponse();
	IOrderAndItem orderInfo=new OrderAndItemManager();

			
	@RequestMapping("/")
	public String home() {
		System.out.println("Connection Details: \n" + connection);

		return "Hello from Techfreaks!";
	}

	/*
	 * @Supreetha for saving payment details to DB.
	 */
	@PostMapping("/MakePayment")
	public boolean makePayment() {
		String cardNo = getCardID();
		double currentBal = card.getCardBalance();
		if (currentBal > orderDetails.getTotalPrice()) {
			double newBalance = currentBal - orderDetails.getTotalPrice();
			mp.makePayment(getCardID(), orderDetails.getOrderNumber(), orderDetails.getTotalPrice(), newBalance);
			card.setCardBalance(newBalance);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Card> request = new HttpEntity<Card>(card, headers);
			String url = "http://localhost:8080/update/" + card.getCardID();

			// Calling rest api to update card balance.
			restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

			return true;

		} else {
			return false;
		}

	}
	/*
	 * @Supreetha for calling REST API for getting card id.
	 */
	private String getCardID() {
		card= restTemplate.getForObject("http://localhost:8080/mycards/active" , Card.class);
		return card.getCardID();
	}

	
	
	
	/*
	 * @Supreetha for retrieving payment all payment details from DB.
	 */
	@GetMapping("/getPayments")
	public ArrayList<starbucks.Payment> getAllPayments() {

		return (mp.getAllPayments());
	}
	
	/*
	 * @Supreetha for retrieving payment all payment details from DB.
	 */
	
	 @RequestMapping(value = "/getPayments/{id}", method = RequestMethod.GET)
	 public  starbucks.Payment getPayment(@PathVariable int id){
	   return (mp.getPayment(id));
	  } 
	

	// AddCard API - Harini Balakrishnan
	// GET - current active card
	@RequestMapping("/mycards/active")
	public com.starbucks.library.Card getActiveCard() {
		return mycards.getActiveCard();
	}

	// AddCard API - Harini Balakrishnan
	// GET - all cards in the table
	@RequestMapping("/mycards/all")
	public ArrayList<com.starbucks.library.Card> getAllCards() {
		return mycards.getAllCards();
	}

	// AddCard API - Harini Balakrishnan
	// POST - create a new card with JSON format user input
	@PostMapping(path = "/addcard", consumes = "application/json")
	public void insertCard(@RequestBody com.starbucks.library.Card card) {
		System.out.println("id: " + card.getCardID());
		addcard.insertCard(card.getCardID(), card.getCardCode(), card.getCardBalance(), card.getCardUserID(),
				card.getActiveStatus());
	}

	// AddCard API - Harini Balakrishnan
	// PUT - update the card's balance with user input
	@PutMapping(path = "/update/{cardID}", consumes = "application/json")
	public boolean updateCard(@PathVariable String cardID, @RequestBody com.starbucks.library.Card card) {
		return mycards.updateCardBalance(cardID, card.getCardBalance());
	}

	// AddCard API - Harini Balakrishnan
	// DELETE - delete a card from the database
	@DeleteMapping(path = "/delete/{cardID}")
	public boolean deleteCard(@PathVariable String cardID) {
		return mycards.deleteCard(cardID);
	}
	

/*
	 * @Ravali 
	 * Managed Order API - to get Menu Items
	 */
	

	@GetMapping("/getMenuItems")
	public ArrayList<ItemInfo> getAllItems() {
		orderInfo.setConnectionInfo(url,username,password);		
		//List of Items.
		ArrayList<ItemInfo> items=orderInfo.getMenuItems();
		return items;		
	}	
	
	/*
	 * @Ravali 
	 * Managed Order API - to place order
	 */
	String cardID = card.getCardID();
	@GetMapping(path = "/placeOrder/{cardID}/{items}")
	public boolean placeOrder(@PathVariable String cardID, @PathVariable String items) 
	{
		orderInfo.setConnectionInfo(url,username,password);	
		ArrayList<String> itemListOrdered=new ArrayList<String>();
		itemListOrdered.addAll(Arrays.asList(items.split(",")));

		int temp=orderInfo.placeOrder(itemListOrdered, cardID);
		orderDetails.setOrderNumber(Integer.toString(temp));
		if (temp!=0)
				return true;
		else
			return false;
	}
	
	/*
	 * @Ravali 
	 * Managed Order API - to get Order
	 */
	
	@GetMapping(path = "/getOrder/{orderNumber}")
	public OrderedItemsInfo getOrder(@PathVariable int orderNumber) 
	{
		orderInfo.setConnectionInfo(url,username,password);	
		OrderedItemsInfo itemsInfo=orderInfo.getOrderDetails(orderNumber);
		orderDetails.setTotalPrice(itemsInfo.getTotalPrice().doubleValue());
		return itemsInfo;
		
	} 
	
	/*
	 * @Ravali 
	 * Managed Order API - to cancel order
	 */
	@GetMapping(path = "/cancelOrder/{orderNumber}")
	public Boolean cancelOrder(@PathVariable int orderNumber) 
	{
		orderInfo.setConnectionInfo(url,username,password);	
		return orderInfo.cancelOrder(orderNumber);
		
	} 


}