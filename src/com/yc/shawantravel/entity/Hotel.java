package com.yc.shawantravel.entity;

import org.kymjs.kjframe.database.annotate.Id;

public class Hotel {
	
	@Id()
	private int id;
	private String name;
	private String phoneNum;
	private String address;

	public int getId()
	{
		return id;
	}
	
	public void setId( int id )
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	public void setPhoneNum( String phoneNum )
	{
		this.phoneNum = phoneNum;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress( String address )
	{
		this.address = address;
	}
}
