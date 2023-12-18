# Task-Managment-System

A simple app to help you track your progress in your daily tasks

## Project main possibilies

**1. SignUp:**
  - Allows user to create an account.
    
**2. SignIn:**
  - Allows user to enter in his account.
    
**3. All tasks tab:**
  - The all task tab is showing all user's created task, that was saved in database.

**4. Add new ask button:**
  - The button in All tasks tab allows user add a new task to database.

**5. Add task tab:**
  - The add task tab is creating a task that user will type.
    
**6. Current task tab:**
  - Current task tab is describing user's task, that he chose in all tasks tab.

**7. Current task tab EDIT MODE:**
  - Allows user to change information of task and saving it.

**8. Current task tab DELETE MODE:**
  - Allows user to delete full current task from database.

**9. Settings tab:**
  - Allows user to change current name or set a new password.

**10. Logout button:**
  - Allows user to logout from current account.

## Team members:
- **Ostapchenko David:**

  Created logic of Repository(PostgreSQL connector) and Model
- **Chambulov Azamat:**

  Created logic of Model and Controller
- **Parvani Vafa:**

  The development of Java Swing UI, LoginWindow
- **Talantova Dilbar:**

  The development of Java Swing UI, MainWindow


## Screenshots of project

**Sign In window**

![SignIn screenshot](https://sun9-2.userapi.com/impg/XKwnLqgQqw5v8bi4tYgYHl0mUEwtQ2riJhhmkQ/vZxP-SNOALI.jpg?size=426x259&quality=96&sign=549d8fa803d3e72ec6623301c8e20459)

**Sign Up window**

![SignUp screenshot](https://sun9-43.userapi.com/impg/Qm87QMevWOBOVv3qZJnqHJErPjrVDcz-Ztav7w/tMfiU9jJIuE.jpg?size=380x388&quality=96&sign=1d504ac8ee8afb0efe44903adfd93e9a)

**Add Task tab**

![AddTask screenshot](https://sun9-9.userapi.com/impg/B64toRnjrGWGdyu1kh-HX4ewqGG8urYOgKP8ww/FMaFhpok2J4.jpg?size=1258x668&quality=96&sign=c126602dfdd95694477cef3d2a50ac48)

**All Tasks tab**

![TasksList screenshot](https://sun9-17.userapi.com/impg/XXyAbAfrKW9p2FbbeZsuq65hX81b6IeAiPfZUA/1Qx1Q2sggfM.jpg?size=1264x681&quality=96&sign=d99ef9e8ce5bdf123ef9e9e0f2625d92)

**Settings tab**

![Settings screenshot](https://sun9-58.userapi.com/impg/iSRjdHbiv2Kxxd1kLKu6IbsdSUUOXavJJI0MIA/awqewRQfsDY.jpg?size=1261x679&quality=96&sign=41472817c0b9e9cda0c4a05130f2b491)


## Getting Started

These instructions will help you set up and run the project on your local machine for development and testing purposes. See "Deployment" for information on deploying the project to a production environment.


### Prerequisites

Ensure you have the following installed on your machine:

- Java Development Kit (JDK) 20 or later
- Apache Maven 
- PostgreSQL dependency

### Installation

Step-by-step guide on how to get a local copy of the project and set it up.

```bash
git clone https://github.com/shinemettd/Task-Managment-System.git
cd task-management-system
mvn clean install

```
After the installation, you have to create your own database, and change the values of databaseAddress, databaseUser and databasePassword to yours, otherwise the connection will be null, and the project wont work.

## **[Here's a JAR File ](https://drive.google.com/file/d/1ch_p7Ccu45q1pntxhc-DBvoVbA0nVUO)**

