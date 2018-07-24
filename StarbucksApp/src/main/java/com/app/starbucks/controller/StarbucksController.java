package com.app.starbucks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import starbucks.*;
import com.starbucks.library.*;
import java.sql.Connection;

@RestController
public class StarbucksController {
	starbucks.ManagePayments mp = new starbucks.ManagePayments();
	List<starbucks.Payment> paymentsList = new ArrayList<>();
	
//	  Author - Harini Balakrishnan 
// 	  Database credentials and initialize classes from AddCard JAR file	
	com.starbucks.library.MySqlConnection mysql = new  com.starbucks.library.MySqlConnection();
	String url = "";    
    String username = ""; 
    String password = "";
    Connection connection = mysql.getConnection(url, username, password);
	com.starbucks.library.MyCards  mycards = new com.starbucks.library.MyCards();
	com.starbucks.library.AddCard addcard = new com.starbucks.library.AddCard();
	com.starbucks.library.Card card = new com.starbucks.library.Card();
	
	
	@RequestMapping("/")
    public String home(){
		System.out.println("Connection Details: \n" + connection) ;
        return "Hello World!";
    }


	@PostMapping("/MakePayment")
	public void makePayment()
	{
		mp.makePayment("116","128",5.00,20.00);
	
	}
	
	
//	  AddCard API - Harini Balakrishnan 
//	  GET - current active card
	@RequestMapping("/mycards/active")
	public  com.starbucks.library.Card getActiveCard() {
		return mycards.getActiveCard();
	}
	
//	  AddCard API - Harini Balakrishnan 
//	  GET - all cards in the table
	@RequestMapping("/mycards/all")
	public ArrayList<com.starbucks.library.Card> getAllCards() {
		return mycards.getAllCards();
	}
	
	
//	  AddCard API - Harini Balakrishnan 
//	  POST - create a new card with JSON format user input 
	@PostMapping(path = "/addcard", consumes = "application/json")
	public void insertCard(@RequestBody com.starbucks.library.Card card) {
		System.out.println("id: " + card.getCardID() );
		addcard.insertCard(card.getCardID() , card.getCardCode(), card.getCardBalance(),card.getCardUserID(), card.getActiveStatus());
	}
	
	
//	  AddCard API - Harini Balakrishnan 
//	  PUT - update the card's balance with user input 
	@PutMapping(path = "/update/{cardID}", consumes = "application/json")
	public boolean updateCard(@PathVariable String cardID, @RequestBody com.starbucks.library.Card card) {
		return mycards.updateCardBalance(cardID, card.getCardBalance());
	}

}
