package com.gongsibao.panda;

public enum EnumTest {
	MON(1,"一"), TUE(2,"二"), WED(3,"三"), THU(4,"四"), FRI(5,"五"), SAT(6,"六") {
		@Override
		public boolean isRest() {
			return true;
		}
	},
	SUN(0,"七") {
		@Override
		public boolean isRest() {
			return true;
		}
	};

	private int value;
	private String text;
	
	private EnumTest(int value,String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public boolean isRest() {
		return false;
	}

	public String getText() {
		return text;
	}
}
