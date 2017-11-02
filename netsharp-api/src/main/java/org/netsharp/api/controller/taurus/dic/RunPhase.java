package org.netsharp.api.controller.taurus.dic;

public enum RunPhase {

	RUNPHASE_1(0, 1, "初创期"), RUNPHASE_2(1, 2, "发展期"), RUNPHASE_3(2, 3, "增长期"), RUNPHASE_4(
			3, 4, "成长期"), RUNPHASE_5(4, 5, "成熟期"), RUNPHASE_6(6, 10, "转型期"), RUNPHASE_7(
			11, 20, "多元化"), RUNPHASE_8(21, 10000, "多元化");

	private int startCount;
	private int endCount;
	private String text;

	RunPhase(int startCount, int endCount, String text) {
		this.startCount = startCount;
		this.endCount = endCount;
		this.text = text;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static RunPhase getItem(int count) {

		for (RunPhase item : values()) {

			if (count <= item.getEndCount()) {
				return item;
			}
		}
		return null;
	}
}
// phase = distinceYear <= 1 ? "初创期" :
// distinceYear <= 2 ? "发展期" :
// distinceYear <= 3 ? "增长期" :
// distinceYear <= 5 ? "成长期" :
// distinceYear <= 10 ? "成熟期" :
// distinceYear < 20 ? "转型期"
// : "多元化";
