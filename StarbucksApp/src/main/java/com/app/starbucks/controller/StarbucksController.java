package com.app.starbucks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import starbucks.*;
import com.starbucks.library.*;
import java.sql.Connection;

@RestController
public class StarbucksController {
	starbucks.ManagePayments mp = new starbucks.ManagePayments();
	com.starbucks.library.MySqlConnection mysql = new  com.starbucks.library.MySqlConnection();
	Connection connection=  mysql.getConnection();
	com.starbucks.library.MyCards  mycards;
	com.starbucks.library.AddCard addcard;
	com.starbucks.library.Card card;
	List<starbucks.Payment> paymentsList = new ArrayList<>();
	
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
	
	
	@RequestMapping("/mycards/active")
	public String getActiveCard() {
		mycards = new MyCards();
		return mycards.getActiveCard().getCardID();
	}
	
	
	@RequestMapping("/mycards/all")
	public ArrayList<com.starbucks.library.Card> getAllCards() {
		mycards = new MyCards();
		return mycards.getAllCards();
	}
	
	@PostMapping("/addcard")
	public void insert() {
		addcard = new AddCard();
		addcard.insertCard("987654321", "123", 20.00, 1, true);
    }
	
	
}
