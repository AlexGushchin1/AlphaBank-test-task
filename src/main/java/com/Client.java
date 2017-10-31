package com;

public class Client {
	
private Integer  id;
private String   name;
private String   lastname;
private String   adress;
private Integer  creditamount;
private Integer  rejectionreasonid;

public Client(){} 
public Client(Integer id, String name, String lastname, String adress, Integer creditamount, Integer rejectionreasonid) {
	super();
	this.id = 					id;
	this.name = 				name;
	this.lastname = 			lastname;
	this.adress = 				adress;
	this.creditamount = 		creditamount;
	this.rejectionreasonid = 	rejectionreasonid;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getAdress() {
	return adress;
}

public void setAdress(String adress) {
	this.adress = adress;
}

public Integer getRejectionreasonid() {
	return rejectionreasonid;
}

public void setRejectionreasonid(Integer rejectionreasonid) {
	this.rejectionreasonid = rejectionreasonid;
}

public Integer getCreditamount() {
	return creditamount;
}

public void setCreditamount(Integer creditamount) {
	this.creditamount = creditamount;
}


public void printinfo() {
	System.out.println("Id :"+ id + "  / " + name + " " + lastname + " размер запрашиваемой суммы : " +creditamount );
}


}
