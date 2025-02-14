package com.htbsc.fipe.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> tClass);
}
