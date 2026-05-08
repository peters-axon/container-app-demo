package com.axonivy.demo.container.app;

import java.time.LocalDateTime;

import javax.faces.event.ActionEvent;

public class HelloWorldCtrl {
	private String message;
	
	public HelloWorldCtrl() {
		update(null);
	}
	
	public void update(ActionEvent event) {
		message = LocalDateTime.now().toString();
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
