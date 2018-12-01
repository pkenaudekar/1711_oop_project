package chess;

import java.awt.*;
import javax.swing.*;

import pieces.*;

/**
 * This is the Cell Class. It is the token class of our GUI.
 * There are total of 144 cells that together makes up the Chad Board
 *
 */
public class Cell extends JPanel implements Cloneable{
	
	//Member Variables
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
	int x,y;                             //is public because this is to be accessed by all the other class
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	//Constructors
	public Cell(int x,int y,Piece p)
	{		
		this.x=x;
		this.y=y;
		
		setLayout(new BorderLayout());
		
	 
		if((x+y)%2==0  && !((x==1 && (y== 7 || y==8 || y==9)) ||(x==2 && (y==6 || y==10))||(x==3 && (y==6 || y==10))||(x==4 && (y==6 || y==10))||(x==5 && (y== 7 || y==8 || y==9))||(x==6 && (y== 2 || y==3 || y==4))||(x==5 && (y== 7 || y==8 || y==9))||(x==6 && (y== 2 || y==3 || y==4))||(x==7 && (y== 1 || y==5))||(x==8 && (y== 1 || y==5))||(x==9&& (y== 1 || y==5))||(x==10 && (y== 2 || y==3 || y==4))) )
			setBackground(new Color(255,255,0));
	 
		else if(((x==1 && (y== 7 || y==9)) ||(x==2 && (y==6 || y==10))||(x==4 && (y==6 || y==10))||(x==5 && (y== 7 || y==9))||(x==6 && (y== 2 || y==4))||(x==7 && (y== 1 || y==5)) ||(x==9 && (y== 1 || y==5))||(x==10 && (y== 2 || y==4))))
			setBackground(new Color(153,76,0));
	 
		else if((x==1 && y==8 )||(x==3 && (y==6 || y==10))||(x==5 && y==8)||(x==6 && y==3)||(x==8 && (y==1 || y==5))||(x==10 && y==3))
			setBackground(new Color(102,51,0));
	 
		else
			setBackground(new Color(255,255,153));
	 
		if(p!=null)
			setPiece(p);
	}
	
	//A constructor that takes a cell as argument and returns a new cell will the same data but different reference
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.x=cell.x;
		this.y=cell.y;
		setLayout(new BorderLayout());
		if((x+y)%2==0  && !((x==1 && (y== 7 || y==8 || y==9)) ||(x==2 && (y==6 || y==10))||(x==3 && (y==6 || y==10))||(x==4 && (y==6 || y==10))||(x==5 && (y== 7 || y==8 || y==9))||(x==6 && (y== 2 || y==3 || y==4))||(x==5 && (y== 7 || y==8 || y==9))||(x==6 && (y== 2 || y==3 || y==4))||(x==7 && (y== 1 || y==5))||(x==8 && (y== 1 || y==5))||(x==9&& (y== 1 || y==5))||(x==10 && (y== 2 || y==3 || y==4))) )
			setBackground(new Color(255,255,0));
			 
		else if(((x==1 && (y== 7 || y==9)) ||(x==2 && (y==6 || y==10))||(x==4 && (y==6 || y==10))||(x==5 && (y== 7 || y==9))||(x==6 && (y== 2 || y==4))||(x==7 && (y== 1 || y==5)) ||(x==9 && (y== 1 || y==5))||(x==10 && (y== 2 || y==4))))
			  setBackground(new Color(153,76,0));
		 
		else if((x==1 && y==8 )||(x==3 && (y==6 || y==10))||(x==5 && y==8)||(x==6 && y==3)||(x==8 && (y==1 || y==5))||(x==10 && y==3))
			  setBackground(new Color(102,51,0));
		
		else
			setBackground(new Color(255,255,153));
		
		if(cell.getpiece()!=null)
		{
			setPiece(cell.getpiece().getcopy());
		}
		else
			piece=null;
	}
	
	public void setPiece(Piece p)    //Function to inflate a cell with a piece
	{
		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		content=new JLabel(img);
		this.add(content);
	}
	
	public void removePiece()      //Function to remove a piece from the cell
	{
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{
			piece=null;
			this.remove(content);
		}
	}
	
	
	public Piece getpiece()    //Function to access piece of a particular cell
	{
		return this.piece;
	}
	
	public void select()       //Function to mark a cell indicating it's selection
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected=true;
	}
	
	public boolean isselected()   //Function to return if the cell is under selection
	{
		return this.isSelected;
	}
	
	public void deselect()      //Function to delselect the cell
	{
		this.setBorder(null);
		this.isSelected=false;
	}
	
	public void setpossibledestination()     //Function to highlight a cell to indicate that it is a possible valid move
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.ispossibledestination=true;
	}
	
	public void removepossibledestination()      //Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.ispossibledestination=false;
	}
	
	public boolean ispossibledestination()    //Function to check if the cell is a possible destination 
	{
		return this.ispossibledestination;
	}
	
	public void setcheck()     //Function to highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}
	
	public void removecheck()   //Function to deselect check
	{
		this.setBorder(null);
		if((x+y)%2==0  && !((x==1 && (y== 7 || y==8 || y==9)) ||(x==2 && (y==6 || y==10))||(x==3 && (y==6 || y==10))||(x==4 && (y==6 || y==10))||(x==5 && (y== 7 || y==8 || y==9))||(x==6 && (y== 2 || y==3 || y==4))||(x==5 && (y== 7 || y==8 || y==9))||(x==6 && (y== 2 || y==3 || y==4))||(x==7 && (y== 1 || y==5))||(x==8 && (y== 1 || y==5))||(x==9&& (y== 1 || y==5))||(x==10 && (y== 2 || y==3 || y==4))) )
			setBackground(new Color(255,255,0));
			 
		else if(((x==1 && (y== 7 || y==9)) ||(x==2 && (y==6 || y==10))||(x==4 && (y==6 || y==10))||(x==5 && (y== 7 || y==9))||(x==6 && (y== 2 || y==4))||(x==7 && (y== 1 || y==5)) ||(x==9 && (y== 1 || y==5))||(x==10 && (y== 2 || y==4))))
			  setBackground(new Color(153,76,0));
		 
		else if((x==1 && y==8 )||(x==3 && (y==6 || y==10))||(x==5 && y==8)||(x==6 && y==3)||(x==8 && (y==1 || y==5))||(x==10 && y==3))
			  setBackground(new Color(102,51,0));
		
		else
			setBackground(new Color(255,255,153));
		
		this.ischeck=false;
	}
	
	public boolean ischeck()    //Function to check if the current cell is in check
	{
		return ischeck;
	}
}