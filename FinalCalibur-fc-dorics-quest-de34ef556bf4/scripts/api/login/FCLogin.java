package scripts.api.login;

import org.tribot.api.Clicking;
import org.tribot.api.Timing;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

import scripts.api.generic.FCConditions;
import scripts.api.worldhopping.FCInGameHopper;

public class FCLogin
{
	private final static int LOGOUT_BUTTON_MASTER = 182;
	private final static int LOGOUT_BUTTON_CHILD = 6;
	
	public static boolean logout()
	{
		if(GameTab.open(TABS.LOGOUT))
		{
			if((!FCInGameHopper.isHopperUp() || FCInGameHopper.closeHopper()) && clickLogoutButton())
				return Timing.waitCondition(FCConditions.NOT_IN_GAME_CONDITION, 1500);
		}
		
		return false;
	}
	
	private static boolean clickLogoutButton()
	{
		RSInterface button = Interfaces.get(LOGOUT_BUTTON_MASTER, LOGOUT_BUTTON_CHILD);
		
		if(button != null)
			return Clicking.click(button);
		
		return false;
	}
}
