package com.yferhaoui.connect4_minimax;

import com.yferhaoui.basic.helper.StreamHelper;
import com.yferhaoui.basic.helper.TimeHelper;

public final class Timeout extends Thread {

	private final static long TIMEOUT = 8000;
	private final static long STEP = 1200;

	// -------------------------------- //

	private final AIWorker[] workers;
	private int depthMin;
	private boolean isRunning = true;

	public Timeout(final AIWorker[] workers, final int depthMin) {
		this.workers = workers;
		this.depthMin = depthMin;
	}

	@Override
	public final void run() {

		TimeHelper.sleepUninterruptibly(TIMEOUT);

		while (this.depthMin > 0 && this.isRunning) {

			// Decrement depth step by step
			for (final AIWorker worker : this.workers) {
				worker.decrementDepth();
			}
			this.depthMin--;
			StreamHelper.printlnWithTime("TIMEMOUT : " + this.depthMin, 0);
			TimeHelper.sleepUninterruptibly(STEP);
		}
	}
	
	// Setter
	public final void stopTimeout() {
		this.isRunning = false;
	}
}
