package com.rotadeviagem;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;

public class App 
{
    public static void main(final String[] args) {
        try {
            final String inputsPath = "/home/luizarthur/reposLuiz/RotaDeViagem/input-file.txt";
//            		Inputs.getPathRotas(args);
			final Scanner scan = new Scanner(System.in);
            
            while(true) {
            	System.out.println("Please enter the route:");
            	String  routeString = scan.next();
            	List<String> iataCodes = Inputs.getIataCodes(routeString);
            	if(iataCodes == null) {
            		continue;
            	}
            	JsonNode response = Http.get(iataCodes.get(0), iataCodes.get(1), inputsPath);
            	System.out.println(response);
            }

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
