package scripts.framework;

import java.io.Serializable;

public class WorldHopSettings implements Serializable
{
	private static final long serialVersionUID = -5771913543672453230L;
	
	public int playersInArea = -1;
	public int resourceStolenPerHour = -1;
	public boolean noResourceAvailable = false;
}
