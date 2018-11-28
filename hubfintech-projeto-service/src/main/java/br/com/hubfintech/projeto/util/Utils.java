package br.com.hubfintech.projeto.util;

import org.apache.commons.lang.StringUtils;

public class Utils {

	public static boolean isPf(Boolean tipoPessoa) {
		Boolean isPf = Boolean.TRUE;
		if(tipoPessoa != null
			&& tipoPessoa) {
			isPf = Boolean.FALSE;
		}
		
		return isPf;
	}
	
	public static String removerMascaraCpfCnpj(String cpfCnpj) {
		String retorno = null;
		if(cpfCnpj != null) {
			retorno = cpfCnpj.replaceAll("\\D", "");
		}
		return retorno;
	}
	
	public static String formatCpfCnpj(String cpfCnpj, Boolean tipoPessoa) {
		String retorno = null;
		
		if(StringUtils.isNotBlank(cpfCnpj)) {
			retorno = removerMascaraCpfCnpj(cpfCnpj);
			if(isPf(tipoPessoa)) {
				retorno = StringUtils.leftPad(retorno, 11, "0");
			} else {
				retorno = StringUtils.leftPad(retorno, 14, "0");
			}
		}
		
		return retorno;
	}
	
}
