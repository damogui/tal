package com.netsharp.rest.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class AmountUtils implements Serializable {
	
	private static final long	serialVersionUID	= -3357428692528862880L;
	// 默认除法运算精度
	private static final int	DEF_DIV_SCALE			= 2;
	
	// 这个类不能实例化
	
	private AmountUtils() {

	}
	
	/**
	 * 
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *          被加数
	 * 
	 * @param v2
	 *          加数
	 * 
	 * @return 两个参数的和
	 * 
	 */
	
	public static double add(double v1, double v2) {
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.add(b2).doubleValue();
		
	}
	
	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *          被减数
	 * 
	 * @param v2
	 *          减数
	 * 
	 * @return 两个参数的差
	 * 
	 */
	
	public static double sub(double v1, double v2) {
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.subtract(b2).doubleValue();
		
	}
	
	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *          被乘数
	 * 
	 * @param v2
	 *          乘数
	 * 
	 * @return 两个参数的积
	 * 
	 */
	
	public static double mul(double v1, double v2) {
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.multiply(b2).doubleValue();
		
	}
	
	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *          被除数
	 * 
	 * @param v2
	 *          除数
	 * 
	 * @return 两个参数的商
	 * 
	 */
	
	public static double div(double v1, double v2) {
		
		return div(v1, v2, DEF_DIV_SCALE);
		
	}
	
	/**
	 * 
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *          被除数
	 * 
	 * @param v2
	 *          除数
	 * 
	 * @param scale
	 *          表示表示需要精确到小数点以后几位。
	 * 
	 * @return 两个参数的商
	 * 
	 */
	
	public static double div(double v1, double v2, int scale) {
		
		if(scale < 0) {
			
			throw new IllegalArgumentException(

			"The scale must be a positive integer or zero");
			
		}
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}

	public static String getRealAmount(int amount) {
		double rs = AmountUtils.div(amount, 100, 2);
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(false);
		format.setMaximumFractionDigits(2);
		return format.format(rs);
	}
	
	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *          需要四舍五入的数字
	 * 
	 * @param scale
	 *          小数点后保留几位
	 * 
	 * @return 四舍五入后的结果
	 * 
	 */
	
	public static double round(double v, int scale) {
		
		if(scale < 0) {
			
			throw new IllegalArgumentException(

			"The scale must be a positive integer or zero");
			
		}
		
		BigDecimal b = new BigDecimal(Double.toString(v));
		
		BigDecimal one = new BigDecimal("1");
		
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param v
	 *          需要截断的数字
	 * 
	 * @param scale
	 *          小数点后保留几位
	 * 
	 * @return 截断的结果
	 */
	public static double cutOff(double v, int scale) {
		
		if(scale < 0) {
			
			throw new IllegalArgumentException(

			"The scale must be a positive integer or zero");
			
		}
		
		BigDecimal b = new BigDecimal(Double.toString(v));
		
		BigDecimal one = new BigDecimal("1");
		
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	
	/**
	 * if a==b return true, else return false;
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equal(double a, double b) {
		if((a - b > -0.001) && (a - b) < 0.001) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * if a>＝b return true, else return false;
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compare(double a, double b) {
		if(a - b > -0.001)
			return true;
		else
			return false;
	}
	
	/**
	 * if a>b return true, else return false;
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean bigger(double a, double b) {
		if(a - b > 0.001)
			return true;
		else
			return false;
	}

	/**
	 * 金额取顶到百位 单位分
	 *
	 * @param a
	 * @return
	 */
	public static long ceil(long a) {
		if (a == 0l) {
			return 0l;
		}
		// 金额取顶到百位
		long c = a % 10000l;
		if (c > 0l) {
			a = a - c + 10000l;
		} else {
			a = a - c;
		}
		return a;
	}
	
	public static void main(String[] args) {
		// System.out.println(equal(1.001, 2));
		
//		System.out.println("add:" + div(100.0133, 200.03444));
//		System.out.println("round:" + round(100.0133, 2));

	}
	
}
