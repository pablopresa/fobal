package com.fobal.util;

import java.text.DecimalFormat;

public class Util {

	
	public static Double truncarDouble(Double original, Integer cifrasSignificativas) {
		
		DecimalFormat df = new DecimalFormat("#." + new String(new char[cifrasSignificativas]).replace('\0', '#'));
		String resultado = df.format(original);
		return Double.parseDouble(resultado.replace(",", "."));
	}
}
