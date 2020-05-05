package com.inayat.yourrooms.controllers;

import java.io.IOException;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.service.HotelService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	HotelService hotelservice;

	@RequestMapping(value = "/{mode}", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> payment(@RequestBody BookingDTO dto, @PathVariable("mode") String mode)
			throws NumberFormatException, IOException {
		if (mode.equals("online")) {
			ApiResponse resp = hotelservice.payment(dto);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			ApiResponse resp = hotelservice.paymentPayAthotel(dto);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/update-order", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> updateOrder(
			@QueryParam("payment_id") String payment_id,
			@QueryParam("payment_status") String payment_status,
			@QueryParam("id") String id,
			@QueryParam(value = "hash") String hash) throws NumberFormatException, IOException {
		ApiResponse resp = hotelservice.updateOrder(payment_id, payment_status, id, hash);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/getPaymentByOrderId/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getPaymentByOrderId(@PathVariable("orderId") String orderId)
			throws NumberFormatException, IOException {
		ApiResponse resp = hotelservice.getPaymentByOrderId(orderId);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/create-refund/{paymentId}/{amount}", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> createRefund(@PathVariable("amount") Double amount,
			@PathVariable("paymentId") String paymentId) throws NumberFormatException, IOException {
		ApiResponse resp = hotelservice.createRefund(paymentId, amount);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createHash", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> createHash(@QueryParam("payment_id") String payment_id,
			@QueryParam("payment_status") String payment_status, @QueryParam("id") String id,
			@QueryParam(value = "hash") String hash) throws NumberFormatException, IOException {
		ApiResponse resp = hotelservice.createHash(payment_id, payment_status, id, hash);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
