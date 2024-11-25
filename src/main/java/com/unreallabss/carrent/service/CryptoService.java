package com.unreallabss.carrent.service;


import com.unreallabss.carrent.domain.base.EntityIdCryptoException;

public interface CryptoService {

    String encryptString(String text);

    String decryptString(String text);

    String encryptEntityId(Long value) throws EntityIdCryptoException;

    Long decryptEntityId(String text) throws EntityIdCryptoException;
}
