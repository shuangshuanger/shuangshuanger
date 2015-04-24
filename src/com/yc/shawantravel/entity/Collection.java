package com.yc.shawantravel.entity;

import org.kymjs.kjframe.database.annotate.Id;

public class Collection {
	
	@Id()
	private int id;
	private String name;
	private String kind;
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
	
	public String getKind()
	{
		return kind;
	}
	
	public void setKind( String kind )
	{
		this.kind = kind;
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
