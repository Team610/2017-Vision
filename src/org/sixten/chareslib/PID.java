package org.sixten.chareslib;

public class PID {

	private double p;
	private double d;
	private double i;
	private double max;
	private double min;
	private double curTarget;

	private double e;
	private double ePrev;
	private double eDiff;
	private double iCounter;

	private double output;

	//Initializes a PID object
	public PID() {
		e = 0;
		ePrev = 0;
		eDiff = 0;
		output = 0;
		curTarget = 0;
		min = -1e50;
		max = 1e50;
	}

	//Initializes a PID object with p, i, and d factors
	public PID(double p, double i, double d) {
		this();
		this.p = p;
		this.i = i;
		this.d = d;
	}

	//Initializes a PID object with p,i,d factors and max and min output values.
	public PID(double p, double i, double d, double min, double max) {
		this(p, i, d);
		this.max = max;
		this.min = min;
	}
	
	//Gets output value from PID loop
	public double getValue(double input, double target, double feedForward) {
		if (curTarget != target) {
			resetPID();
			curTarget = target;
		}
		e = target - input;
		eDiff = ePrev - e;

		if (e > 0 && iCounter < 1.0 / i) {
			if (e > 0.1) {
				iCounter = 0;
			} else {
				iCounter += e;
			}
		} else if (e < 0 && iCounter < -1.0 / i) {
			if (e < -0.1) {
				iCounter = 0;
			} else {
				iCounter += e;
			}
		}

		output = e * p + eDiff * d + iCounter * i + feedForward;

		ePrev = e;
		if (output > max) {
			output = max;
		} else if (output < min) {
			output = min;
		}

		return output;
	}
	
	//Reset errors for PID
	public void resetPID() {
		e = 0;
		ePrev = 0;
		eDiff = 0;
		iCounter = 0;
		output = 0;
	}
	
	//Changes the p,i,d factors
	public void updatePID(double p, double i, double d) {
		this.p = p;
		this.i = i;
		this.d = d;
	}

	//Gets errors
	public double getError() {
		return e;
	}

}
