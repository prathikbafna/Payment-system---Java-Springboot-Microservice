package com.zee.themis.converter;

public interface Converter<I,O,E> {
    O convert(I input,E existing);
}
