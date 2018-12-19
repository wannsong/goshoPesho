package scripts.api.utils;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSMenuNode;

public class Utils
{
	private final static int[] FREE_WORLDS = { 301, 308, 316, 326, 335, 381,
		382, 383, 384, 393, 394 }; // Free worlds
	
	private static Image paintImage;
	
	public static void handleGui(final JFrame gui)
	{	
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{	
					gui.setLocationRelativeTo(null);
					gui.setAlwaysOnTop(true);
					
					//Position GUI in center of screen
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
					gui.setLocation((int)(screenSize.getWidth() / 2), (int)(screenSize.getHeight() / 2));
					
					//Make GUI visible
					gui.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Image loadImage(String url)
	{	
		paintImage = null;
		
		new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try 
                {
                	paintImage = ImageIO.read(new URL(url));
					this.finalize();
				} 
                catch (Throwable e) 
                {
					e.printStackTrace();
				}
            }
        }).run();
		
		return paintImage;
	}
	
	public static boolean isPlayerMember()
	{
		return WorldHopper.isMembers(WorldHopper.getWorld());
	}
	
	public static RSMenuNode getOption(String str)
	{
		for(RSMenuNode node : ChooseOption.getMenuNodes())
		{
			if(node.getAction().equalsIgnoreCase(str))
			{
				return node;
			}
		}
		
		return null;
	}
	
	public static int getWildyLevel()
	{
		RSInterface inter = Interfaces.get(90, 26);
		if (inter != null)
		{
			String text = inter.getText();
			
			return text == null || text.length() < 8 ? 0 : Integer.parseInt(text.substring(7));
		} else
		{
			return 0;
		}
	}

	public static boolean isMember()
	{
		for(int w : FREE_WORLDS)
		{
			if(Game.getCurrentWorld() == w)
			{
				return false;		
			}			
		}
		
		return true;		
	}
}
