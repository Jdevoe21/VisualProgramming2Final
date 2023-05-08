# VisualProgramming2Final
# Semester Project for CSC261
### By Jack Devoe
- My project is called Arduino Simulator.
- My project is a simulator for arduino that lets you build an arduino circuit board before you buy the parts to see what you need.
- Similar to the application "Scratch", my project also uses code blocks to code the arduino simulation so the user gets a feel for what they have to do to get their code to run.
- I am working with java in NetBeans for my project.

## File Information
- Inside the src folder holds all the folders and code files for my project.
  - MainWindow.java is the main view of the project, this is what the user will be seeing everything through.
  - BlockPanel.java is the view of the coding side of the project. All coding blocks will only be seen through this panel.
  - ToolBar.java is the half of block panel to select and drag which blocks the user will be coding with.
  - BlockContainer.java is the list that the blocks are added to when they are dragged from the toolbar to the other side of the block panel.
  - Block.java is an abstract class for every single block. this includes inputs and variables.
  - FunctionBlock.java is an abstract class for all the blocks that hold inputs. Meaning this will exclude inputs and variables.
  - TabMenu.java is to switch between the different types of blocks. These include Blocks, and variables.
  - SetUpBlock.java
  - LoopBlock.java
  - PrintBlock.java is the code block that acts as a "Serial.println" in arduino converted to java.
  - IfBlock.java is the code block that acts as a "If statement" in arduino converted to java.
  - IfElseBlock.java is the code block that acts as a "If statement with an Else statement" in arduino converted to java.
  - ForBlock.java is the code block that acts as a "For loop" in arduino converted to java.
  - WhileBlock.java is the code block that acts as a "While loop" in arduino converted to java.
  - SetBlock.java is the code block that acts as a "Set variable" in arduino converted to java.
  - CreateBlock.java is a code block that cannot be dragged. This block is only for creating variables that will show up along side it in the toolbar. 
  - Input.java is a code block that sits in all kinds of function blocks. These inputs can be typed in and be given values. They can also have variables and expressions dragged into them.
  - InputButton.java is a code block that is similar to an input but cannot be typed in. Instead it acts as a button to perform various actions such as flip between menus, create variables, or show a drop down menu.
  - Variable.java is a code block that can only be created via the "CreateBlock" class. These variables are able to be given values of any type and are able to be dragged into input blocks.
  - Literal.java holds the value of a variable.
  - Expression.java is a code block that acts similar to a variable but also has its own inputs. It has a drop down menu to view all different types of expressions and is used to create more complex if statements and for loops.
  - DropDown.java is a code block similar to an input button this button has a single funtion and that is to reaveal a drop down menu when clicked.
  - IncorrectOperandException.java is a class to stableize the conditions for the expressions, variables, and inputs.
  - CircuitPanel.java is the view of the circuit side of the project. All Arduino parts will only be seen through this panel.
  - CircuitEditorPanel.java
  - Sprite.java is an abstract class for every single Arduino part, this includes the arduino itself, the breadboard and, the pins.
  - Component.java
  - ComponentCollection.java
  - Arduino.java will act as the Arduino Mega to connect wires too and to connect itself to the breadboard.
  - Pin.java acts as each individual pin on the Arduino, Breadboard or other parts.
  - MalePin.java
  - BreadBoard.java is the breadboard complete with all the pins like an actual breadboard uses.
  - LED.java is an LedLight with two pins to connect to the breadboard.
  - RGBLED.java
  - Resistor.java
  - GPS.java
  - Pot.java
  - PinModeBlock.java
  - AnalogWriteBlock.java
  - AnalogReadBlock.java
  - DigitalWriteBlock.java
  - DigitalReadBlock.java
  - DelayBlock.java
  - GPSReadBlock.java

 ## Features
 - Fully functional coding side(Block Panel) (Complete)
 - User is able to drag and drop Blocks at will (Complete)
 - User is able to snap blocks together and create long complex lists of code (Complete)
 - User is able to create and set variables with any name they would like (Complete)
 - Variables can be set to any type possible (Strings, Booleans, Integers, Floats, Expression Values) (Complete)
 - User is able to drag and drop variables and expressions inside inputs however they would like (Complete)
 - If blocks and for blocks are able to expand and decreace their height depending on how many children blocks they have (Complete)
 - Blocks and inputs are able to expand and decrease their width depending on if their inputs hold expressions or variables (Complete)
 - Replicated the flow of electricity (Complete)
 - Fully functional Arduino simulation (Complete)
 - API integration(Complete)
