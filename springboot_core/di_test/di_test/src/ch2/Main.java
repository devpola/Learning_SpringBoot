package ch2;

import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String url = "www.naver.com/books/it?page=106&size=20";

        //base 64 encoding
        Base64Encoder encoder = new Base64Encoder();
        String result = encoder.encode(url);
        System.out.println(result);

        //url encoding
        UrlEncoder urlEncoder = new UrlEncoder();
        String urlResult = urlEncoder.encode(url);
        System.out.println(urlResult);

        Encoder encoderDI = new Encoder(new Base64Encoder());
        String resultDI = encoderDI.encode(url);
        System.out.println(resultDI);
    }
}
