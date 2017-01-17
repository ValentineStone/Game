package com.valentine.game.utils;

public final class ProcExt
{
	private ProcExt()
	{}
	
	public static long measureExecutionTimeNanos(Runnable _r)
	{
		long begining;
		long ending;
		
		begining = System.nanoTime();
		_r.run();
		ending   = System.nanoTime();
		
		return ending - begining;
	}

}
