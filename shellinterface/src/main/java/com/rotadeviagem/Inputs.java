package com.rotadeviagem;

import java.util.Arrays;
import java.util.List;

import com.rotadeviagem.exceptions.ArrayWithLengthZeroException;

public class Inputs {
    public static String getPathRotas(String[] args) throws ArrayWithLengthZeroException {
        if(args.length == 0) {
            throw new ArrayWithLengthZeroException("Nenhum caminho para o arquivo de inputs foi fornecido");
        }

        return args[0];
    }
    
    public static List<String> getIataCodes(final String routeString) {
    	try {
    		final List<String> iataCodes = Arrays.asList(routeString.replace(" ", "").split("-"));

    		if(iataCodes.size() != 2) {
    			System.out.println("Invalid Route");
    			return null;
    		}
    		
    		return iataCodes;
    	} catch (Exception e) {
    		System.out.println("Invalid route");
    		return null;
    	}
    }
}