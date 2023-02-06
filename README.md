# WikTionary

This project follows a combination of 2 componnents. One is the clean architecture and the other is the MVI(Model-View-Intent) flow


# What is MVI ?
MVI Stands for Model View Intent. The architecture is inspired by the unidirectional control flow.
The role of each MVI component is as follows:
* **Model** : Model in MVI represents a state. For example UI might have different states like Data Loading, Error, Update UI etc. Each State is stored as similar to the object in the model. This component interacts with beneath layers to access data.
* **View** : View represents our UI layer. View is nothing but an interface that is implemented by our Activities/Fragments. This interface is nothing but a container that accepts model and renders it as UI.
* **Intent** : Intent represents an intent or a desire to perform an action, either by the user or the app itself. 


### How MVI works
For every action, **View** receives an **Intent**. The **Presenter(ViewModel)** observes the **Intent**. Then pass it on to respective domain layer to process same to give back **Model**. The resultant **Model** is translated to a state which is received by the **View**. 


### Architecture - MVI + Clean Architecture
This project follows the clean architecture which implies that each components in the archirecture points in one direction. That means child classes don't have refernce to their parent classes. The app cna be divided into 3 major areas for handling of data. They are - 

- Presentation/App: Consists of Activities, Fragments and ViewModels. This layer interacts with UI. 
- Domain: Contains the business logic of the application. This is the individual and innermost circle of clean Architecture.
- Data: Consists of Repositories. This layer would implement interface exposed by domain layer and dispenses data to the app.

![image](https://user-images.githubusercontent.com/58071934/215340081-9baa2020-e64d-42e2-84d4-c8fa42e81a20.png)



### Diagramatic representation of Dataflow

![image](https://user-images.githubusercontent.com/58071934/216809090-696def3a-4ea5-4b3b-8901-fbe3cf8b98c5.png)

