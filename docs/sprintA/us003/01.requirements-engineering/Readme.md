# US 003 - To register an Employee 

## 1. Requirements Engineering


### 1.1. User Story Description

As a system administrator, I want to register a new employee.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Each employee is characterized by having a name, a unique citizen's card number, a tax number, an address, an email address, a contact telephone number and a agency which it is assigned.


>	All employees will be registered by the company's system administrator.



**From the client clarifications:**

> **Question:** The administrator when registering a new employee will also have to specify the category/office that he will perfom (for example agent, store manager, store network manager)? 
>  
> **Answer:** The administrator has to specify the role of the employee. 


> **Question:** What would be the attributes of the System Administrator, Agency and the Roles? 
>  
> **Answer:** The System Administrator is an employee. You can get the roles from the project description. Please check the project description. Moreover, I just answered a question to clarify what are the attributes of an agency/store. 


> **Question:** Can an employee be registered to more than one agency?
>
> **Answer:** No. 


> **Question:** When registering a new employee, will the administrator set the respective employee password?
>
> **Answer:** The password should have eight characters in length and should be generated automatically. The password is sent to the employee by e-mail.


> **Question:** Does the System Administrator have permission to create, edit, delete, or just create new employee registrations?
>
> **Answer:** For now, the System Administrator can only do what is specified in the Project Requirements.


> **Question:** The system administrator cannot add an agent that already exists, the agent has two unique numbers that identify him (Tax number and Citizen's card number) which one should be used to identify the agent?
>
> **Answer:** The tax number.


> **Question:** Must the Tax number and Citizen's card number follow any convention? If so, which?
>
> **Answer:** You should use the tax identification number used for tax purposes in the US.
 

> **Question:** Does the system administrator select the agency to which the employee is assigned and his role from a list? Or does he just type that data?
>
> **Answer:** The System Administrator should select.


> **Question:** However, it was replied to a question when a new Employee is created in the system, that a 8 digit Password should be automatically generated. How many digits should we go forward for password length validation in your software? And please confirm required special characters, etc.
>
> **Answer:** Sorry, I completely forgot that all our authentication systems require passwords with seven alphanumeric characters in length , including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail.


> **Question:** When registering a new employee, all the required data (name, citizen's card number, etc...) have to be filled or exists not mandatory data?
>
> **Answer:** Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which it is assigned.


> **Question:** You've stated previously that an employee can only be registered to one agency so what happens if an employee wants/has to change agencies and needs to be registered to a new one? Should the system reject such operation or should the employee's previous registration be deleted?
>
> **Answer:** For now I do not want such features to be included in the system. I will discuss your suggestion with the company shareholders and I will come back here if we decide to include such features in the system.


> **Question:** You have stated before that name, cc number, tax number, email address, phone number and the assigned agency of the employee are the mandatory requirements to register a new one, leaving out the employee's address and role. This confused me, because it wasn't clear whether leaving out those two characteristics from the answer was intentional or not. Furthermore, the role of the employee seems like too much of an important piece of information to be left out. My request is, then, for you to state whether or not that was a conscious decision in your answer.
>
> **Answer:** The role is required.


> **Question:** I have a question related to the output data: when the system administrator is registering a new employee are we free to display what we feel is important or should a specific message be shown? I was thinking of displaying whether the operation was successful or not, is that fine or should something else be displayed as well?
>
> **Answer:** A good practice is to show the information and ask for confirmation.


### 1.3. Acceptance Criteria


* **AC1:** All required fields must be filled in.
* **AC2:** An employee can only be registered to one agency.
* **AC3:** The citizen's card number must have 9 digits.
* **AC4:** The tax number must have 9 digits.
* **AC5:** For the address, the street number need to have at least 1 digit, street name and the city name must have at least 1 alpha chars each, state zip code must have 5 digits.
* **AC6:** The email address minimal length resolves to 3 characters.
* **AC7:** The contact telephone number need to have 10 digits.
* **AC8:** The password for the employee should have seven alphanumeric characters in length, including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail.
* **AC9:** When registering an employee that already exists, the system must reject such operation and the administrator must have the change to modify the typed tax number.


### 1.4. Found out Dependencies

* There is a dependency to "US004 To submit a requested Property sale or rent" since it's necessary to register an agent to submit the request.


### 1.5 Input and Output Data

**Input Data:**

* Typed data:
	* a name
	* the citizen's card number
	* the tax number
	* the address
	* the email address
	* the contact telephone number
    * the password
    
* Selected data:
    * the agency to which it is assigned
    * the role of the employee

**Output Data:**

* (In)Success of the operation


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us003-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

n/a