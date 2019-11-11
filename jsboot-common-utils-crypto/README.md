# **加密编码工具包**
<!-- TOC -->

- [**加密编码工具包**](#加密编码工具包)
    - [1. 介绍](#1-介绍)
    - [2. 常用不可逆加密方式,支持类型HashModelEnum中](#2-常用不可逆加密方式支持类型hashmodelenum中)
        - [2.1 md5](#21-md5)
        - [2.2 sha](#22-sha)
    - [3. 常用对称加密工具](#3-常用对称加密工具)
        - [3.1 aes](#31-aes)
    - [4. 其他](#4-其他)
        - [4.1 base64](#41-base64)

<!-- /TOC -->

## 1. 介绍
基础模块-加密编码工具包

 `为了工具包足够轻量,只封装最常用的一些工具`


## 2. 常用不可逆加密方式,支持类型HashModelEnum中
### 2.1 md5
```
//md5(默认16进制小写)
String md5Hex = Md5Util.md5(DATA);
```
### 2.2 sha

```
//sha1(默认16进制小写)
String sha1 = SHAUtil.sha1(DATA);

//sha256(默认16进制小写)
String sha256 = SHAUtil.sha256(DATA);

//sha512(默认16进制小写)
String sha512 = SHAUtil.sha512(DATA);
```

## 3. 常用对称加密工具
### 3.1 aes

`提供了最为常用的Aes对称加密工具,密文支持默认16进制小写,base64,base64换行模式,支持的模式和补码方式见AesModelEnum,AesPaddingEnum `

`支持模式:CBC,ECB,CTR,OFB,CFB,支持补码方式:NoPadding,PKCS5Padding,ZeroPadding,ISO10126Padding`
```
//默认AES/CBC/PKCS5Padding
Aes aes = new Aes("密钥", "IV");
AesUtil.encrypt("密钥", "IV", data);
AesUtil.decrypt("密钥", "IV", aesData);

//最常用的aes模式 AES/CBC/PKCS5Padding
Aes aes = new Aes(AesModelEnum.CBC, AesPaddingEnum.PKCS5Padding, "密钥", "IV");

//也可以换用其他模式或者补码方式 AES/ECB/ZeroPadding
Aes aes = new Aes(AesModelEnum.ECB, AesPaddingEnum.ZeroPadding, "密钥", null);

Aes aes = new Aes(AesModelEnum.ECB, AesPaddingEnum.PKCS5Padding, "密钥", null);

//也提供了一个util直接调用
AesUtil.encrypt(AesModelEnum.CBC, AesPaddingEnum.PKCS5Padding, "密钥", "IV", data);
AesUtil.decrypt(AesModelEnum.CBC, AesPaddingEnum.PKCS5Padding, "密钥", "IV", aesData);

ase.encrypt(data);//加密(返回16进制小写密文)
ase.decrypt(aesData);//解密16进制小写密文
ase.encryptBase64(data);//加密(返回base64密文)
ase.decryptBase64(aesData);//解密base64密码
ase.encryptBase64(data, true);//加密(返回base64换行密文)
ase.decryptBase64(aesData, true);//解密base64换行密文
```

## 4. 其他
### 4.1 base64

`提供了base64转码和解码方法,支持默认不换行和换行模式。推荐不换行模式使用jdk8的Base64。换行模式使用的sun.misc.BASE64，不推荐。`

```
//base64编码(jdk8util无换行符模式)
Base64Util.encode(data);

//base64编码选择是否换行
Base64Util.encode(data,lineSep);

//base64解码(jdk8util无换行符模式)
Base64Util.decode(data);

//base64解码选择是否换行(这里没有做自动识别,为了让代码明确知道是否使用了换行模式)
Base64Util.decode(data,lineSep);
```