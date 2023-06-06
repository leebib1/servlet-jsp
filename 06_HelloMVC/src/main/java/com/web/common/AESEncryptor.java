package com.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//양방향 암호화
//java에서 제공하는 클래스를 이용해서 양방향 암호화를 진행한다.
//java.crypto 패키지, java.security 패키지에서 제공하는 클래스를 이용한다.
//양방향 암호화는 암호화와 복호화를 할 수 있는 key가 있어야한다.
//대칭키 암호화(AES) : key가 하나로 암호화, 복호화에 하나의 key를 사용한다.
//비대칭키 암호화(RSA) : 암호화 key인 공개키 1개, 복호화 key인 개인키 1개로 총 두 개의 key가 존재한다.

public class AESEncryptor {
	//1. 키 생성
	private static SecretKey key; //암호화 키를 저장하는 객체
	private String path; //key가 파일로 저장된 위치를 나타내는 변수
	public AESEncryptor() {
		//키 값을 생성하거나 가져오기
		//key를 저장할 파일명은 keyfile.key;
		this.path=AESEncryptor.class.getResource("/").getPath();
		this.path=this.path.substring(0, this.path.indexOf("classes"));
		System.out.println(path);
		File keyFile=new File(this.path+"keyfile.key");
		if(keyFile.exists()) {
			//1) 생성된 키가 존재하면 저장된 파일에서 키를 가져와서 key변수에 저장
			try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(keyFile));) {
				
				AESEncryptor.key=(SecretKey)ois.readObject();
				
			}catch(IOException|ClassNotFoundException e){
				e.printStackTrace();
			}
		}else {
			//2) 키가 없으면 SecretKey 클래스를 생성하고 key변수에 저장
			generateKey(keyFile);
			
		}
	}
	private void generateKey(File keyFile) {
		//java.crypto 패키지에서 제공하는 KeyGenerator클래스를 이용해서 SecretKey를 생성한다.
		//키 생성 시 salt값이 필요하다.
		SecureRandom rnd=new SecureRandom();
		KeyGenerator keygen=null;
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(keyFile))) {
			keygen=KeyGenerator.getInstance("AES");
			keygen.init(128,rnd); //초기화해서 128비트를 가지고 랜덤의 솔트값을 만들어낸다.
			//key를 생성해서 멤버변수에 저장
			AESEncryptor.key=keygen.generateKey();
			//생성된 키를 파일로 저장
			oos.writeObject(key);
		}catch(IOException|NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	}
	
	//2. 암호화 메소드 선언
	public static String encryptData(String oriData) throws Exception{
		//원본 데이터를 암호화 처리하는 메소드
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, AESEncryptor.key); //Cipher.ENCRYPT_MODE 변수로 지정되어있음
		//나중에 복호화 했을 때 조합할 수 있게 인코딩값을 설정함
		byte[] targetData=oriData.getBytes(Charset.forName("UTF-8"));
		byte[] encryptData=cipher.doFinal(targetData);
		
		return Base64.getEncoder().encodeToString(encryptData);
	}
	
	//3. 복호화 메소드 선언
	public static String decryptData(String encryptData) throws Exception{
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, AESEncryptor.key); //해당 키를 이용해서 복호화처리 할 수 있도록 초기화
		//인코딩된 데이터를 다시 디코딩하고 디코딩값 설정
		byte[] encrypt=Base64.getDecoder().decode(encryptData.getBytes(Charset.forName("UTF-8")));
		byte[] decryptData=cipher.doFinal(encrypt); //디코딩된 데이터를 복호화
		return new String(decryptData);
	}
}
