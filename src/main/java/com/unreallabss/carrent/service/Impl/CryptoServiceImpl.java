package com.unreallabss.carrent.service.Impl;

import com.unreallabss.carrent.config.PlatformConfig;
import com.unreallabss.carrent.domain.base.EntityIdCryptoException;
import com.unreallabss.carrent.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Security;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    PlatformConfig configs;

    private TextEncryptor textEncryptor;

    private TextEncryptor entityIdEncryptor;

    @PostConstruct
    public void init() {

        Security.setProperty("crypto.policy", "unlimited");
        //entityIdEncryptor = Encryptors.queryableText(configs.getCrypto().getPassword(), configs.getCrypto().getSalt());
        textEncryptor = Encryptors.text(configs.getCrypto().getPassword(), configs.getCrypto().getSalt());
    }

    @Override
    public Long decryptEntityId(String text) {
        try {
            return Long.valueOf(entityIdEncryptor.decrypt(text));
        } catch (Exception e) {
            throw new EntityIdCryptoException("decryption failed! value: " + text, e);
        }
    }

    @Override
    public String encryptString(String text) {

        return textEncryptor.encrypt(text);
    }

    @Override
    public String decryptString(String text) {
        return textEncryptor.decrypt(text);
    }

    @Override
    public String encryptEntityId(Long number)  {
        try {

            return entityIdEncryptor.encrypt(number.toString());
        } catch (Exception e) {
            throw new EntityIdCryptoException("encryption failed! value: " + number, e);
        }

    }
}
