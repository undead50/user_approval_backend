package com.ebl.userapproval.utils;

import com.ebl.userapproval.execption.InvalidSignatureException;
import lombok.experimental.UtilityClass;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ritesh Shrestha on 8/30/2022
 */

@UtilityClass
public class SignatureUtil {

    public static String signSHA256RSAPKCS1(String input, String certFilePath)
            throws InvalidSignatureException, IOException {


        String stringPrivateKey = readPrivatePublicKey(certFilePath);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(0));
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
        v2.add(DERNull.INSTANCE);
        v.add(new DERSequence(v2));
        v.add(new DEROctetString(Base64.getDecoder().decode(stringPrivateKey)));
        ASN1Sequence seq = new DERSequence(v);
        byte[] privKey = seq.getEncoded("DER");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                privKey
        );
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            var privateKey = keyFactory.generatePrivate(keySpec);
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] s = privateSignature.sign();
            return Base64.getEncoder().encodeToString(s);
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException | InvalidKeySpecException e) {
            throw new InvalidSignatureException(e.getLocalizedMessage());
        }
    }


    public static String readPrivatePublicKey(String path) throws InvalidSignatureException {
        final List<String> pemLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(ResourceUtils.getURL(path).openStream()))) {
            Stream<String> lines = bufferedReader.lines();
            lines.forEach(pemLines::add);
        } catch (IOException e) {
            throw new InvalidSignatureException("Can not read cert key");
        }

        pemLines.remove(0);
        pemLines.remove(pemLines.size() - 1);
        String pem = String.join("", pemLines);
        return pem;
    }

}
