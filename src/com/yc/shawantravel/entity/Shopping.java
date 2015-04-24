package com.yc.shawantravel.entity;

import org.kymjs.kjframe.database.annotate.Id;

public class Shopping {

	@Id()
	private int id;
	private String name;

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
}

