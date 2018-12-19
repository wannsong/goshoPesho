package scripts.framework.goal;

import java.io.Serializable;

public abstract class Goal implements Serializable
{		
	protected static final long serialVersionUID = 162018050902816620L;
	
	public abstract String getName();
	public abstract boolean hasReached();
	public abstract String getCompletionMessage();
}
