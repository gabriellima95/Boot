import javax.swing.*;     // Para os Frames
import java.awt.*;        // Para a interface grafica
import java.awt.event.*;  // Para os eventos
import java.util.ArrayList;
import java.awt.Font;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Anotacao {

	private String Texto;
	private String MetaTag;
	private String Nome;
	private DateFormat SimpleData;
	private Date Data;
	
	public void Anotacao ()
	{
		Texto = "";
		MetaTag = "";
		Nome = "";
		Data = new Date();
		SimpleData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}
	
	public void Escrever_Texto (String escrever)
	{
		Texto = escrever;
	}
	
	public void Escrever_Data ()
	{
		Data = new Date();
		SimpleData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
	}
	
	public String getData ()
	{
		return SimpleData.format(Data);
	}

	public Date getDate ()
	{
		
		return Data; 
	}
	
	public String getTexto ()
	{
		return Texto;
	}
	
	public String getMetaTag()
	{
		return MetaTag;
	}
	
	public String getNome()
	{
		return Nome;
	}
}