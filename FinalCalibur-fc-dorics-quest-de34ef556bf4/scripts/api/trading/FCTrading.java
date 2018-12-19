package scripts.api.trading;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Trading;
import org.tribot.api2007.Trading.WINDOW_STATE;
import org.tribot.api2007.types.RSInterfaceChild;

public class FCTrading 
{
	private final static int TRADING_MASTER = 335;
	private final static int SECOND_WINDOW_MASTER = 334;
	private final static int SECOND_WINDOW_OPPONENT_CHILD = 36;
	private final static int SECOND_WINDOW_STATUS_CHILD = 29;
	
	private static RSInterfaceChild child;
	
	public static String getTradingWith()
	{
		WINDOW_STATE window = Trading.getWindowState();
		
		if(window == null)
		{
			return "";
		}	
		else if(window.equals(WINDOW_STATE.FIRST_WINDOW))
		{
			child = Interfaces.get(TRADING_MASTER, CHILDREN.TRADING_WITH.getId());
			
			if(child != null && child.getText().length() > 14)
			{
				String name = child.getText().substring(14);
				
				return name;
			}
		}
		else if(window.equals(WINDOW_STATE.SECOND_WINDOW))
		{
			child = Interfaces.get(SECOND_WINDOW_MASTER, SECOND_WINDOW_OPPONENT_CHILD);
			
			if(child != null && child.getText().length() > 17)
			{
				String name = child.getText().substring(17);
				
				return name;
			}
		}
		
		return "";
	}
	
	public static int getOpponentFreeSlots()
	{
		child = Interfaces.get(TRADING_MASTER, CHILDREN.FREE_SLOTS.getId());
		
		if(child != null)
		{
			String[] parts = child.getText().split(" ");
			
			return Integer.parseInt(parts[parts.length - 4]);
		}
		
		return -1;
	}
	
	public static boolean hasAccepted(boolean otherPlayer)
	{
		WINDOW_STATE window = Trading.getWindowState();
		
		String search = otherPlayer ? "Other player has accepted." : "Waiting for other player...";
		
		if(window == null)
		{
			return false;
		}	
		
		child = window.equals(WINDOW_STATE.FIRST_WINDOW) ? Interfaces.get(TRADING_MASTER, CHILDREN.BOTTOM_STATUS_TEXT.getId())
				: Interfaces.get(SECOND_WINDOW_MASTER, SECOND_WINDOW_STATUS_CHILD);
		
		if(child != null)
		{	
			return child.getText().equals(search);
		}
		
		return false;
	}
	
	private enum CHILDREN
	{
		FREE_SLOTS(8),
		ACCEPT(9),
		DECLINE(12),
		YOUR_OFFER(24),
		OPPONENTS_OFFER(27),
		BOTTOM_STATUS_TEXT(29),
		TRADING_WITH(30);
					
		private int id;
		
		CHILDREN(int id)
		{
			this.id = id;
		}
		
		public int getId()
		{
			return id;
		}
	}
}
