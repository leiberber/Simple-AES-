# Simple-AES-
一、项目简介
-------
本项目实现了一个简单的加密和解密程序，并包括了中间相遇攻击的测试。程序采用Java语言编写，主要包括以下类和方法：

* `Cipher`类和`Decipher`类：包含了`cipher`和`decipher`两个方法，分别用于对二进制明文进行加密和对密文进行解密；包含了`cipher2`和`decipher2`两个方法，分别用于对二进制明文进行双重加密和对密文进行解密；包含了`cipher3`和`decipher3`两个方法，分别用于对二进制明文进行三重加密和对密文进行解密。
* `ASCII`类：包含了`asciiencipher`和`asciidecipher`两个方法，分别用于对字符串明文进行加密和对密文进行解密；包含了`CBCcipher`和`CBCdecipher`两个方法，分别用于对字符串明文进行CBC加密和密文进行解密。
* `Key_Expansion`类：包含了`key_Expansion`方法，用于对密钥进行扩展。
* `Round_Funciton`类：包含了`Half_Byte_Replace`方法，用于对明文进行半字节替换；包含了`Line-shift`方法，对明文进行行移位；包含了`Column_Confusion`方法，对明文进行列混淆。
* `S_Box`类：包含了`s_box`方法，用于辅助半字节替换功能。
* `Middle_Attack`类：包含了`attack`方法，用于对已知明密文对进行中间相遇攻击。
* `SimpleEncryptionApp`类：实现了该项目的`gui`界面。
-------
二、使用方法
-------
1.运行`SimpleEncryptionApp.java`用于实现加解密和破解。
2.处理用户输入：在用户界面上，用户可以输入明文和密钥，并选择输入数据类型，支持16位二进制数字和字符输入。输入类型错误会报错。
明文错误：
![Uploading WT0`_DAQRO1[(}TDJFX@B7R.png…]()
