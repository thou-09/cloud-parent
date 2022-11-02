package com.itany.conf;

import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AlipayConfig.
 *
 * @author Thou
 * @date 2022/11/1
 */
@Configuration
public class AlipayConfig {

    public static final String SERVER_URL = "https://openapi.alipaydev.com/gateway.do";
    public static final String APPID = "2016102500756097";
    public static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC46qE5gfMaIk2nEkNdRYrX/JOcNfpNB6vicryNprw4t61RSxzDVHss4dLx7nUPxooRFbfIrl4bk8apbHF7+u5n5LgfCUsy7qDiq+tqtdUdcsnSwnPskoKg/DRwcnmReR3Dy7jB1f7mT9sqvDFn5+wP0PVP2eftAnFukY1EyuylboGsC+Lhu7+bTsNi8leQ9qmzYT+rCBOuOv+uxVhym39fEoOma8pXc3D9ePVZNYBp8EJ4SnXI/M3rEq4Gq0RNeoswIiKG0opHUyYdMx9SEoWZCsKBAuD0DE028N39YkZE4FnkFq3qqvhxSsbAkfX3YjNTZg0uVkVHsEXO8XmlQU1bAgMBAAECggEAcHbrNaGubB+e4NT/uZOZHUHVuPUvVXgr9x7XRMO6yDgWJlIrnTUdcS+rb08bTUvwHvH5hAlg3R7kfZih8MiuBOwbe2zYmpJ9sT7kgFM02aM90nxHqwN0hVq+vgExpONn0fRuWx2+2O6KmpQ47Dwm23u4Jyedog2RVbEanVNZ2EawgZuf0HAv/8iCUNHnQAzHyYSQhRFWDtzTKrK6HuU8SUeGvwNteOXshgwm0eaZtNiwzxyOFDtvnRaBLTOHxvKa2OHrJ6mWu0OiTkn/7EweiPtRU8nooIN1StCcQoITJ3en4Z4w71Yg31e3n5gLcMBMBI/rOVBlCvhwqheldMrKEQKBgQD+/9hA1JEhpe88wTZP8gtB6Msd/l/fpUcyyDKwO8LccEw89MhxtEt6ev24Ai6jBb+nb/5aKIkPf6+MbKZaELEsTsQHeMSTS6asQdQ6Sn+rBIsYynKj9CJKgct0Kcjxo0WI7L/L4PEilUkzmKs7Dk3kmtpLfpJmd3PLUoAMmk6oGQKBgQC5pGJunLMaC0ivtXhIhz00iWnmCUJ0enxxHiezuDi7F2R6e4ukwaEFQz8FlAhjtip3v2ks8Y3NLONEhFnhVsoXXhcg/FYMyfXMs/qKHyNWryC9oq8QdLjb/yEQlU25trX0w6L82WA5skFVJK3juKKSCZf3p483HAogl2c/vBHfkwKBgDsId8Pqcf7cL1pdUHmJi1mizwMOlJtt4+G5vyJVc3Mc/ElQUecg2ihsuLgUqT9MYWYd40B0CebwpG9Z59kJ7DpZscAZEUkYRmmyg7NK8+tfjeQbWhbiud2N1s/5rG9kYQCl7nEAjlJ3RQ8Q1PNDCH1X7Bl2H+Zg2QnE+6fvQS5hAoGARTwzocE7Ysk2kKGDfLlDcGaXYj2t1TchVibC89oO/mfZB30wNYAph4bu6CuvfVChQgvXM/Xaxtyo3hLmWo49zL1ArwKtLRPdYjz1gbHfQiMKeO9Fj0OY9iOOt1qXpF2qOuED1n1Tz/DVQOXvHHDt4L1HOgv587m3ZTRSQ4jmuZUCgYEAxE/BLjZ2np72O2UqdTpVL99K0BR4mHzsbsStU8Qp7jIladbP7GvRgFWg2T9yWsKLHPDwx6cFRwVqRldE0j3nrWKkRJz4ygz+Ra1A4iODIq5p8Rg1XLthzwXfWFs95CAkRKsv0LePANRPZQNhWndRIjfOZpEaZConnwBogmq+gbA=";
    public static final String FORMAT = "json";
    public static final String CHARSET = "UTF-8";
    public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbjz7r6DRa6JsbVE2hU67pBq3druJSb5TZLhuNvocVefIlFLkArQaQ0D8GPfHF00eTKSaFIkK8TnNoOtBD0vz3C/oeiRQsqoaqwZ/oBjIq7pDxlGDdaEgjMB1fl3fhi9ZEX8aOGB3/A56OKk8HbKVn5CosoIeH+gyiChsCOGTry0WmHdsMGw1KxO/AFG1uQJ0iEEuXJMqmuQWeajuxbPaPlxGTHc8lRSb73zaID2/Uewn6SHaqrRE+ZWeMwg/+KOeoocAYtwOo0N124bz+F4gQyfXrpQh+nKJmeqCBSUEYtYkT6d1gMIflAi+XM2tUxK2l1p5P8UQLvweKc5KEA8XwIDAQAB";
    public static final String SIGN_TYPE = "RSA2";
    public static final String NOTIFY_URL = "http://thou.vipgz4.91tunnel.com/pay/callback/";


    @Bean
    public DefaultAlipayClient alipayClient() {
        return new DefaultAlipayClient(SERVER_URL, APPID, PRIVATE_KEY, FORMAT, CHARSET, PUBLIC_KEY, SIGN_TYPE);
    }
}
