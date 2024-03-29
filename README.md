# MaliMan Note Ecryptor 👩‍🎓 💻 🔑 💌

 ## Requirements 📃
 1. Create a peer to peer desktop application, in order to send notes between two users. 
 2. The note must be ecrypted during the send, and it must be decrypted when it is received.
 3. Use Affine cipher.
 
 ## Affine Cipher Description
 
  ### Encrypting 🔒
  
   1. Replace every letter by its rank in alphabets starting by 0 :
   
| A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
| -- |:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:| --:|
| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 |

  2. Choose two integer numbers a and b as keys. 
  3. Calculate (a.x+b) mod 26 (knowing that x : the rank of the letter that we want to ecrypte ).
  
  #### Exemple
  
  The plaintext to be encrypted is "HELLO" , we take a=17 , and b=3.
  
  | Plaintxt | H | E | L | L | O |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | X | 7 | 4 | 11 | 11 | 14 |
  
  Now, take each value of x, and calculate the result of the equation : (17x + 3) mod 26, for each character.
  
  | Plaintxt | H | E | L | L | O |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | X | 7 | 4 | 11 | 11 | 14 |
  | (17x+3)mod26 | 18 | 19 | 8 | 8 | 9 |
  
  The final step in encrypting the message is to look up each numeric value in the table for the corresponding letters.
  
  | Plaintxt | H | E | L | L | O |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | X | 7 | 4 | 11 | 11 | 14 |
  | (17x+3)mod26 | 18 | 19 | 8 | 8 | 9 |
  | Ciphertext | S | T | I | I | H |
  
  
  ### Decrypting 🔓
  
   1. Replace every letter by its rank in alphabet starting by 0.
   2. Calculate using the decryption fonction D(y)=((y-b)* a’) mod 26 ( knowig that a' is the modular reverse of a versus 26), of each caracter.
   3. Decrypte the ciphertext using the table.


  #### Exemple
  
  Finishing with same exemple, a'=23 :
  
  | Ciphertext | S | T | I | I | H |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | Y | 18 | 19 | 8 | 8 | 9 |
  
  Subtract b from each number:
  
  | Ciphertext | S | T | I | I | H |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | Y | 18 | 19 | 8 | 8 | 9 |
  | Y-b | 15 | 16 | 5 | 5 | 4 |
  
  Multiply by a' then take mod 26:
  
  | Ciphertext | S | T | I | I | H |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | Y | 18 | 19 | 8 | 8 | 9 |
  | Y-b | 15 | 16 | 5 | 5 | 4 |
  | ((Y-b).a')mod 26 | 7 | 4 | 11 | 11 | 14 |
  
  The final step in decrypting the ciphertext is to use the table to convert numeric values back into letters:
  
  | Ciphertext | S | T | I | I | H |
  | ------- |:--:|:--:|:--:|:--:| --:|
  | Y | 18 | 19 | 8 | 8 | 9 |
  | Y-b | 15 | 16 | 5 | 5 | 4 |
  | ((Y-b).a')mod 26 | 7 | 4 | 11 | 11 | 14 |
  | Plaintxt | H | E | L | L | O |
  
 ## Peer To Peer Using Sockets 🔗 💡
 
 According to requirements, our system should deliver notes in the runtime without any conditions of order (the user can receive a note
 at any time & as much as his parterner want to; we also can't determine which one of them will be the first to inisiate the
 connexion).
 
 To create this project, we have choosed to build our peer to peer architecture using sockets from skratch following these steps: 
 
 1. creating a Server class that listens :ear: & receives 📭 notes.
 2. creating anothe Client class in order to send notes :scroll: .
 3. assign a Client & a Server for each user.
 4. run the server when the application lunches.
 
 This solution helped us to avoide any delay of receiving notes due to socket timeout and the usfulness of leaving sockets opened during
 all the execution whitout actually needing it...
 
 
 ## Execution 🔌🔨
 
 To execute the application we have to make some changes:
 1. if the execution is local (one computer):
   * Change the value of the boolean variable named "local" in the main class of the class AffineEncyptor.java to true.
   * In the bloc of *if(local)*, change the value "Client.USED_CLIENT_IP" to your ip address.
   * execute two time , the first one the boolean variable in the same class named "user1" must be true, and in the second one change it to false.
   
 2. else (two computers):
   * Change the value of the boolean variable named "local" in the main class of the class AffineEncyptor.java to false.
   * For user1 :
     * Change the boolean variable in the same class named "user1" to true.
     * In the else bloc of *if(local)* Change the value "Client.USED_CLIENT_IP" to your ip addres, and "Client.USED_SERVER_IP" to the ip adress of user2.
   * For user2:
     * Change the boolean variable in the same class named "user1" to false.
     * In the else bloc of *if(local)* Change the value "Client.USED_CLIENT_IP" to your ip address, and "Client.USED_SERVER_IP" to the ip adress of user2.
   
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
