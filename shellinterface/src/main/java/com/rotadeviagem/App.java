package com.rotadeviagem;

public class App 
{
    public static void main(final String[] args) {
        try {
            final String pathRotas = Inputs.getPathRotas(args);
            System.out.println(pathRotas);

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
