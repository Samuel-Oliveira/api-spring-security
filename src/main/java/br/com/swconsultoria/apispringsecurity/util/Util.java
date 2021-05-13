package br.com.swconsultoria.apispringsecurity.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;

public class Util {

    public static String objectToJson(Object objeto) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(objeto);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        if(json == null){
            return null;
        }
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false)
        .readValue(json,clazz);
    }

    public static String criaTokenCriptorgrafado(String token) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String tipoSeguranca = "AES";
        Key key = new SecretKeySpec(token.getBytes(), tipoSeguranca);
        Cipher cipher = Cipher.getInstance(tipoSeguranca);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        LocalDate data = LocalDate.now(ZoneId.of("America/Sao_Paulo"));

        return new String(Base64.encodeBase64(cipher.doFinal(data.toString().getBytes())));
    }

}