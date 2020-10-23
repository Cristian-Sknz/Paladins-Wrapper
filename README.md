<p align= "center">
<img src="https://web2.hirez.com/paladins/assets/paladins-logo.png" alt="Paladins" width="300"/>	
<h1 align= "center"> A Paladins API wrapper for Java</h1>
</p>


## :paperclip: My Objective
`I created this wrapper, to use in my projects. I have no intention of keeping myself 100% up to date.
If you want to use it, you will have a small example below, and it contains documentation in the project classes.`

I'm sorry my english is bad.

## :dvd: Dependencies
The dependencies are inside build.gradle

## :newspaper: Add your dependencies!
[![](https://jitpack.io/v/Cristian-Sknz/Paladins-Wrapper.svg)](https://jitpack.io/#Cristian-Sknz/Paladins-Wrapper)

* Gradle
```groovy
repositories {
     maven { url 'https://jitpack.io' }
}

dependencies {
     compile 'com.github.Cristian-Sknz:Paladins-Wrapper:VERSION'
}
```
* Maven
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
    <groupId>com.github.Cristian-Sknz</groupId>
    <artifactId>Paladins-Wrapper</artifactId>
    <version>VERSION</version>
</dependency>
```
## :man_teacher: Simple Use
I recommend that you read the official Documentation to know the limits of the API and among other things

[Paladins /Realm API Developer Guide](https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/view#)

[Json API Responses](https://github.com/luissilva1044894/hirez-api-docs/tree/master/api)

### :asterisk: Create a Instance
<p align="center">
:page_facing_up:
</p>

```java
/*
ONLY ONE INSTANCE can be created. If you have an active session, it is not possible to change AuthKey and DevId 
*/
Paladins paladins = new PaladinsBuilder()
        .setAuthKey("YOUR API TOKEN")
        .setDevId(0000) //DEV ID
        .build();
        // Or
Paladins paladins = Paladins.getInstance()
         .setAuthKey("YOUR API TOKEN")
         .setDevId(0000);
```

### :asterisk: Create a Session
<p align="center">
:page_facing_up:
</p>

```java
Session session = paladins.createSession().get();

/*
This is an event that will be launched every time a validation occurs
It is not necessary to set a runnable in this event.
This verification is necessary to keep the session active.
*/
session.setOnValidating(() -> {
     System.out.println("The session is valid!");
});
```

#### :link: Resume Session
<p align="center">
:page_facing_up:
</p>

```java
/*
This method will make a request to see if the session is valid
If not valid, it will throw an exception
*/
Session session = paladins.resumeSession("SESSIONID").get();

```

#### :link: Test Session
<p align="center">
:page_facing_up:
</p>

```java
/**
* This method will make a request to see if the session is valid
* If not valid, it will throw an exception
*/
boolean isValid = paladins.testSession("SESSIONID").get();

```

### :asterisk: Using a Session
<p align="center">
:page_facing_up:
</p>

```java
// Getting an active session
Session session = paladins.getSessions().get(0);
EndPoint endpoint = session.getEndPoint();

/*
These champions will be saved in the API instance.
The next time you want to get them, the API will not make another request,
it will simply take the previous result. (For the results are the same)
*/
Champions champions = endpoint.getChampions(Language.English).get();
Champion champion = champions.get(0);
        
// Printing the information obtained
System.out.println(champion.getName());
champion.getAbilities().forEach(System.out::println);

```
This project is not complete, I intend to update whenever I can.
Thanks for reading me :D
