package pieces;

import java.util.ArrayList;

import chess.Cell;

public class King extends Piece{
	
	private int x,y; //Extra variables for King class to keep a track of king's position
	
	//King Constructor
	public King(String i,String p,int c,int x,int y)
	{
		setx(x);
		sety(y);
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//general value access functions
	public void setx(int x)
	{
		this.x=x;
	}
	public void sety(int y)
	{
		this.y=y;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	//Move Function for King Overridden from Pieces
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//King can move only one step. So all the adjacent 8 cells have been considered.
		possiblemoves.clear();
		int posx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
		int posy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if((state[posx[i]][posy[i]].getpiece()==null||state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor()))
					possiblemoves.add(state[posx[i]][posy[i]]);
		return possiblemoves;
	}
	
	
	
	//Function to check if king is under threat
	//It checks whether there is any piece of opposite color that can attack king for a given board state
	public boolean isindanger(Cell state[][])
    {
		
		//Checking for attack from left,right,up and down
    	for(int i=x+1;i<12;i++)
    	{
    		if(state[i][y].getpiece()==null)
    			continue;
    		else if(state[i][y].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[i][y].getpiece() instanceof Rook) || (state[i][y].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=x-1;i>=0;i--)
    	{
    		if(state[i][y].getpiece()==null)
    			continue;
    		else if(state[i][y].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[i][y].getpiece() instanceof Rook) || (state[i][y].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y+1;i<12;i++)
    	{
    		if(state[x][i].getpiece()==null)
    			continue;
    		else if(state[x][i].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[x][i].getpiece() instanceof Rook) || (state[x][i].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y-1;i>=0;i--)
    	{
    		if(state[x][i].getpiece()==null)
    			continue;
    		else if(state[x][i].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[x][i].getpiece() instanceof Rook) || (state[x][i].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	
    	//checking for attack from diagonal direction
    	int tempx=x+1,tempy=y-1;
		while(tempx<12&&tempy>=0)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx++;
				tempy--;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x-1;tempy=y+1;
		while(tempx>=0&&tempy<12)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx--;
				tempy++;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x-1;tempy=y-1;
		while(tempx>=0&&tempy>=0)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx--;
				tempy--;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x+1;tempy=y+1;
		while(tempx<12&&tempy<12)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx++;
				tempy++;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		
		return false;
    }
}