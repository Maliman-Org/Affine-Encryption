# MaliMan Note Ecryptor 👩‍🎓 💻 🔑 🔒

 ## Requirements 📃
 1. Create a peer to peer desktop application, in order to send notes between two users. 
 2. The message must be ecrypted during the send, and it must be decrypted when it is received.
 3. Use Affine cipher.
 
 ## Affine Cipher Description
 
  ### Encryption
   1. Remplace every letter by its rank in the alphaber starting by 0 :
   
| A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
| -- |:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:| --:|
| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 |

  2. Choose two integer numbers a and b as key. 
  3. Calculate (a.x+b) mod 26 (knowing that x : the rank of the letter that we want to ecrypte it).
  
  #### Exemple
  the plaintext to be encrypted is "AFFINE CIPHER, we take a=5 , and b=8.
  | Plaintxt | A | F | F | I | N | E | C | I | P | H | E | R |
  |  ------- |:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:| -------:|
  | X | 0 | 5 | 5 | 8 | 13 | 4 | 2 | 8 | 15 | 7 | 4 | 17 |

 ## Screenshots  	📷
| Main Interface |
| ------------- |
|![alt text](https://github.com/madenemalika/Affine-Encryption/blob/master/AffineEncryption/src/Screenshots/main%20page.PNG "Welcome interface" )|


| First User Interface | Second User Interface |
| ------------- |:-------------:|
| ![alt text](https://github.com/madenemalika/Affine-Encryption/blob/master/AffineEncryption/src/Screenshots/user%201.PNG "First User Interface" ) | ![alt text](https://github.com/madenemalika/Affine-Encryption/blob/master/AffineEncryption/src/Screenshots/user%202.PNG "Second User Interface" ) |

| First User Receive The Message | Second User Send The Message |
| ------------- |:-------------:|
| ![alt text](https://github.com/madenemalika/Affine-Encryption/blob/master/AffineEncryption/src/Screenshots/user%202%20get%20the%20msg.PNG "msg recieved" ) | ![alt text](https://github.com/madenemalika/Affine-Encryption/blob/master/AffineEncryption/src/Screenshots/user%201%20with%20msg.PNG "msg sended" ) |
 
 ## IDE & Libraries used 🔧
 * NetBeans IDE 8.2
 * JDK 8
 * Jfoenix 8.0.1
 * controlsfx 9.0.0
