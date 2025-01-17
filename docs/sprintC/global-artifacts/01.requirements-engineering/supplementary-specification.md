# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._


Security

- All those who wish to use the application must be authenticated.
- The password must have seven alphanumeric characters, including three capital letters and two digits.


## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

Help and Documentation

- Javadoc must be used during the software development to generate useful documentation for Java code.



## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


(fill in here )

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


(fill in here)

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 


Testability

 - The development team must implement unit tests for all methods, except for methods that
   implement Input/Output operations.

Locality

- The application must support the English language.
## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  

Programming Languages

- The application must be developed in Java Language using Intellij or NetBeans.

Use of Development Tools
 
- The JaCoCo plugin should be used to generate the coverage report. 
- The application graphical interface is to be developed in JavaFX II. 
- All the images/figures produced during the software development process should be recorded in SVG format.

### Implementation Constraints

_Specifies or constraints the code or construction of a system
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

Platform

- The unit tests should be implemented using the JUnit 5 framework.

Mandatory standards/patterns

- The application must adopt recognized coding conventions and standards (e.g., CamelCase);
 

Database Integrity

- The application should use object serialization to ensure data persistence between two runs of the
  application.

### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


(fill in here)

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here)