package scripts.framework.script;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.stream.Stream;

import org.tribot.api.General;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.api.abc.PersistantABCUtil;
import scripts.api.banking.listening.FCBankObserver;
import scripts.framework.paint.FCPaint;
import scripts.framework.paint.FCPaintable;

public abstract class FCScript extends Script implements FCPaintable, Painting, Starting, Ending
{	
	public final ScriptManifest MANIFEST = (ScriptManifest)this.getClass().getAnnotation(ScriptManifest.class);
	public final FCBankObserver BANK_OBSERVER = new FCBankObserver();
	
	public FCPaint paint = new FCPaint(this, Color.WHITE);
	public transient ABCUtil abc = new ABCUtil();
	public transient PersistantABCUtil abc2 = new PersistantABCUtil();
	
	protected abstract int mainLogic();
	protected abstract String[] scriptSpecificPaint();
	
	public void run()
	{
		while(true)
		{
			handleAbc2Reset();
			
			int sleep = mainLogic();
			
			if(sleep == -1)
				return;
			
			sleep(sleep);
		}
	}
	
	public void onStart()
	{
		General.useAntiBanCompliance(true);
		ThreadSettings.get().setClickingAPIUseDynamic(true);
		println("Started " + MANIFEST.name() + " v" + MANIFEST.version() + " by " + MANIFEST.authors()[0]);
	}
	
	public void onEnd()
	{
		abc2.close();
		BANK_OBSERVER.isRunning = false;
		println("Thank you for running " + MANIFEST.name() + " v" + MANIFEST.version() + " by " + MANIFEST.authors()[0]);
	}
	
	protected String[] basicPaint()
	{
		return new String[]{MANIFEST.name() + " v" + MANIFEST.version() + " by " + MANIFEST.authors()[0],
				"Time ran: " + paint.getTimeRan()};
	}
	
	public String[] getPaintInfo()
	{
		return Stream.concat(Arrays.stream(basicPaint()), Arrays.stream(scriptSpecificPaint())).toArray(String[]::new);
	}
	
	public void onPaint(Graphics g)
	{
		paint.paint(g);
	}
	
	public FCPaint getPaint()
	{
		return paint;
	}
	
	private void handleAbc2Reset()
	{
		if(abc2.needsReset()) //new RS account logs in
		{
			abc2.close();
			abc2 = new PersistantABCUtil();
		}
	}
	
}
