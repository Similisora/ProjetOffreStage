package DAO;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Md5 {
	//Classe pour crypter les mots de passe
	private String code;

	public Md5(String md5) {
		pass(md5);
	}

	public Md5() {
		super();
	}

	public void pass(String password) {
		byte[] passBytes = password.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(passBytes);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(passBytes);
			BigInteger number = new BigInteger(1, messageDigest);
			this.code = number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new Error("invalid JRE: have not 'MD5' impl.", e);
		}
	}

	public String getCode() {
		return code;
	}

}
