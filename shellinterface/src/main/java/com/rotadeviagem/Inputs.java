package com.rotadeviagem;

import com.rotadeviagem.exceptions.ArrayWithLengthZeroException;

public class Inputs {
    public static String getPathRotas(String[] args) throws ArrayWithLengthZeroException {
        if(args.length == 0) {
            throw new ArrayWithLengthZeroException("Nenhum caminho para o arquivo de inputs foi fornecido");
        }

        return args[0];
    }
}