## Smart Travel Agency
### <div align="center">Java (OOP) </div>

#### Travel Agency Booking system that manages Clients, Trips, Accommodations, and Transportations. Command Line Interface Application that prompts for user input using menus. Stores clients and their trips. Trips consist of a base cost, and at least one among a transport and accommodation, as well as the destination, duration and base price. Each object has a unique ID to help with searching and association (i.e. finding a certain client, associating a client to a trip, etc.)
---
### Table of contents
* <a href="packages">Packages</a>
* <a href="#oop-concepts-used">OOP Concepts Used</a>
* <a href="#other-features-and-system--design-choices">Other Features and System & Design Choices</a>
* <a href="#major-menu-options">Major Menu Options</a>
* <a href="#main-menu-each-menu-option-also-has-a-sub-menu---not-included-here-see-driver_a1_comp249">Main Menu (actual)</a>
* <a href="#how-to-run">How to run</a>
---
### Packages
* ```clientPackage```: contains the ```Client``` class
* ```travelPackage```: all travel entities; ```Trip```, ```Accommodation``` (and sub-classes), ```Transportation``` (and sub-classes)
* ```service```: ```SmartTravelService``` class (for business logic), ```RecentList``` and ```Repository``` classes (for history and easy filtering, respectively)
* ```exceptions```: custom checked exceptions for all classes
* ```interfaces```: to allow to set type bounds for certain generic methods and generic classes
* ```persistence```: to manage persistence using file I/O
---
### OOP Concepts Used
* Inheritance, Polymorphism and Abstraction:
  Sub classes ```Flight```, ```Train```, ```Bus``` of abstract class ```Transportation```\
  Sub classes ```Hotel```, ```Hostel``` of abstract class ```Accommodation```\
  - Allows storing of all ```Transportation``` objects in one transport list, and all accommodations in one accommodations list\
  - ```Transportation``` and ```Accommodation``` cannot be initialized to prevent ambiguity\
  - Allows common methods necessary for general accommodations and transportations and method overriding in sub-classes\
  - Method overloading (constructors) and overriding (sub-classes)\
  - Makes it easier to store a transport or accommodation in a Trip object (one ```Transportation``` attribute vs ```Flight```, ```Train``` and ```Bus``` attribute, leaving some potentially empty)\
  - Code reusability:\
      All ```Accommodation``` sub-classes share: name, location, price per night\
      All ```Transportation``` sub-classes share: company name, departure city, price per night\
* Encapsulation:
  - ID attributes in all objects are final, and public: can't be changed, so easily accessible
  - Attributes only accessible and modifiable through getters and setters to enable exception generation where necessary, in order to follow business rules
  - Creation of copy constructors for deep copies
 --- 
### Other Features and System & Design Choices
* Replaced all arrays with generic lists with Type Bounds; improved memory efficiency + implementation of generic methods (code reusability)
* Service layer (```SmartTravelService``` class) to validate certain client and trip data before creating the instance, object list storage and modification, to store ```Repository``` and ```RecentList lists```, and all business logic
* ```RecentList``` class: ```LinkedList``` that stores the last 10 added objects of each type (recent history)
* ```Repository```: ```ArrayList```. Uses predicates for filtering. Includes a sort method for business natural order sorting (ex: Trip greatest to lowest price)
* All classes implement the ```Comparable<Object>``` interface, to allow for sorting at the ```Repository``` level
* Use of ```Interfaces``` to give Type Bounds for generic methods, generic file managers, or list methods (not necessarily linked through inheritance already)
* Persistence using File I/O and ```FileManager``` classes
* Custom ```Exceptions``` to assure the creation of valid objects only, in constructors and in the service layer
* Exception handling at the service layer level (Some at other levels)
* Error logging
* Validation of menu option input
* Main menu in the driver allows to run a predefined hardcoded scenario, to test and view features and methods
---
### Major Menu options
* Add/edit/delete/list all, for ```Client```, ```Trip```, ```Accommodation```, or ```Transportation``` objects
* Display most expensive ```Trip```, calculate total cost of a ```Trip```
* Create deep copy of ```Transportation``` or ```Accommodation``` array
* Various data display after filtering/sorting lists (ex: display top clients by spending, etc.)
---
### Main menu (each menu option also has a sub menu - not included here; see Driver_A1_COMP249)
```
                1. Client Management
				2. Trip Management
				3. Transportation Management
				4. Accommodation Management
				5. Additional Operations
				6. Generate Visualization (dashboard + charts)
				7. Advanced Analytics
				8. Load All Data (output/data/*.csv)
				9. Save All Data (output/data/*.csv)
				10. Run predefined scenario
				0. Exit
```
---
### How to Run
* Open vscode (or other)
* Ensure you have the necessary tools to run Java
* Run the Driver_A1_COMP249 file in the src folder


  
