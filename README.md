# Vending Machine
### Java implementation

### Specifications

* VM payment can be using note(20$ and 50$), coins(10c • 20c • 50c • $10) or card
* VM has 25 slots in 5*5 grid
* VM spesify product required through keypad (user must enter)

### In implementation

* Fill the machine tith products(snacks), each row has spesific snak
* Fill money inventory that already in VM using Math.Random()
* Give product a random price

### Basic Flow

1.customer wants to purchase snacks

2.The customer selects a number by pressing on the keypad

3.The VM displays a message that the snack is available for the selected number and displays its price

4.The customer inserts the money

5.The VM validates the money

6.The VM accepts the money

7.The VM displays the accumulated amount of money each time a new money is entered

8.The VM monitors the amount of the accepted money, If the money is enough, the VM dispenses the selected snack to the customer

9.The VM determines if any change should be sent back to customer

10.The VM displays the change at panel

11.Then, the VM dispenses change

### UML Diagram
![image](https://imgur.com/Gnf5UL6.jpg)

### Test case
![image](https://imgur.com/g67AiD3.png)
