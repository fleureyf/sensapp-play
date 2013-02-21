package models;

import java.util.List;

public class JsonData {

	public static class Measure {
		
		private String u;
		private long t;
		private String sv;
		private double v;
		private boolean isNumerical;
		
		public String getU() {
			return u;
		}
		
		public void setU(String u) {
			this.u = u;
		}
		
		public long getT() {
			return t;
		}
		
		public void setT(long t) {
			this.t = t;
		}

		public String getSv() {
			return sv;
		}

		public void setSv(String sv) {
			this.sv = sv;
			isNumerical = false;
		}

		public double getV() {
			return v;
		}

		public void setV(double v) {
			this.v = v;
			isNumerical = true;
		}

		public boolean isNumerical() {
			return isNumerical;
		}
	}

	private String bn;
	private long bt;
	private List<Measure> e;
	
	public String getBn() {
		return bn;
	}
	
	public void setBn(String bn) {
		this.bn = bn;
	}
	
	public long getBt() {
		return bt;
	}
	
	public void setBt(long bt) {
		this.bt = bt;
	}

	public List<Measure> getE() {
		return e;
	}

	public List<Measure> getE(int from, int to) {
		if (to > e.size()) {
			to = e.size();
		}
		return e.subList(from, to);
	}
	
	public void setE(List<Measure> e) {
		this.e = e;
	}
}
