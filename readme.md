
Name: Nabin Bhandari  SID: 214379635

Name: Pawan Kumar siwakoti SID: 215433131

Project Title: VicRoads License Test

PLATFORM: ANDROID

Overview of the Project: 
It is imperative to have all the information regarding rules, regulations and best practices while driving in the Victorian road for self and for other as well. VicRoads has an extensive process for people to follow to get driving license and knowledge test is one of the first hurdle to pass. This project aims to develop a mobile application to let users to practice questions for VicRoads learner test at any time. I believe this will not only give user confident they need to sit on exam, but they also have better understanding of rules and regulations, which helps them to drive safer.

The proposed system will be developed in Android platform using some of the latest trend and some third-party libraries in its development. Android development has shift to Kotlin programming language from traditional Java programming language. Having increased community support and being supported by Google, I prefer using Kotlin for Android development for this project, however we can use Java as well at the same time. I’ll be using Room library for storing list of questions in database, retrofit for making API connection, Picasso for displaying images and other Android support libraries and Live Data API provided by Android to read data from database.

At the start of this app, it would fetch list of questions from an API and save it locally in device. User can practice randomly selected quizzes or take a mock-up test with randomly generated list of quizzes from database.


Directory structure:
My important files like MainActivity.java, Question.java, Result.java,HomePage.java, SplashScreen.java, db folder,model folder, network folder and repository folder are located on  as:
VicRoadsLicenseTest/app/src/main/java/com/example/pawansiwakoti/vicroadslicensetest/
and their activityxml file are on
VicRoadsLicenseTest/app/src/main/res/layout/
other drawable and styles files are on:
VicRoadsLicenseTest/app/src/main/res/drawable/
VicRoadsLicenseTest/app/src/main/res/styles/

13/04/2019
SplashScreen has been updated.
Added few new folders db,model,network and repository are created:
db folder has Appdatabase java class and Quiz Dao interface
model folder has quiz java class
network folder has Retrofit ClientInstance and Webservice interface
Repository folder has AppRepository
References:
https://www.linkedin.com/learning/building-an-android-app-with-architecture-components?fbclid=IwAR1QWvTy-mWWa5JIhu7VHwia8D1xk_qxhnMR4OFXXzXFeEwGt8j7LCHMXs0

updated retrofitClientInstance.java file
