```
 █████╗       ██████╗ ██╗████████╗     ██████╗██████╗ ██╗   ██╗
██╔══██╗      ██╔══██╗██║╚══██╔══╝    ██╔════╝██╔══██╗██║   ██║
╚█████╔╝█████╗██████╔╝██║   ██║       ██║     ██████╔╝██║   ██║
██╔══██╗╚════╝██╔══██╗██║   ██║       ██║     ██╔═══╝ ██║   ██║
╚█████╔╝      ██████╔╝██║   ██║       ╚██████╗██║     ╚██████╔╝
 ╚════╝       ╚═════╝ ╚═╝   ╚═╝        ╚═════╝╚═╝      ╚═════╝ 
```                                                               
The CPU is designed in Logisim-Evolution. It is connected to simple video card with screen.

## How to run example programs?

In order to run any program located in directory "Programs", firstly you need to use compiler, which is written in Java. Compile .java file, then we can run compiler using commend in terminal:
```
java compiler Programs/program_hello
```
Command above will generate "Program_Memory" file, which contains machine code. Now we can simply edit program memory in Logisim, enable clock and start CPU with switch(it must be 1)

## How to write custom programs?

There are several commends, that compiler can recognize:
```
- add
- and
- clro
- or
- xor
- sub
- mov
- out
- jmp
- jmpz
- jmpnz
- jmpc
```


## OPCODE

```
0000		NOTHING         0000 0000 0000
0001		ADD-> reg       addr 0000 0000
0010		AND-> reg       addr 0000 0000  
0011		OR-> reg        addr 0000 0000
0100		XOR-> reg       addr 0000 0000
0101		WRITE_REG       addr 0000 0000
0110		MOV             addr --value--
0111		SUBTRACTION     addr 0000 0000
1000		LOAD_OUTPUT     addr 0000 0000
1001		
1010		SET_A_REG_JMP   addr 0000 0000
1011		SET_B_REG_JMP   addr 0000 0000
1100		JMP             0000 0000 0000
1101		JMP_Z           0000 0000 0000
1110		JMP_C           0000 0000 0000
1111		JMP_NZ          0000 0000 0000

```
## VALUES OF SIGNS FOR VIDEO CARD
```
1       A
2       B
3       C
4       D
5       E
6       F
7       G
8       H
9       I
10      J
11      K
12      L
13      M
14      N
15      O
16      P
17      Q
18      R
19      S
20      T
21      U
22      V
23      W
24      X
25      Y
26      Z
27      0
28      1
29      2
30      3
31      4
32      5
33      6
34      7
35      8
36      9

63      :)
```
