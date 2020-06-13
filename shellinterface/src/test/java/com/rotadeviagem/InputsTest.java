package com.rotadeviagem;

import com.rotadeviagem.exceptions.ArrayWithLengthZeroException;

import org.junit.Test;

public class InputsTest {

    @Test(expected = ArrayWithLengthZeroException.class)
    public void getPathRotasWithZeroLengthArgs() throws ArrayWithLengthZeroException {
        final String[] args = new String[0];
        final String path = Inputs.getPathRotas(args);
    }
    
}