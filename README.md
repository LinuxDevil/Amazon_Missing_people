  ![Logo](https://github.com/LinuxDevil/Amazon_Missing_people/blob/master/Android/app/src/main/res/drawable/logo.png)

# Amazon_Missing_people (Findr)
  Amazon Teckathon App
  
  
## DEMO
  - [For a test of the API's Routes](https://www.getpostman.com/collections/abe3d09eab858b5485a1)
  - [If you want to try the application: ](https://github.com/LinuxDevil/Amazon_Missing_people/blob/master/app-debug.apk)
  
## Idea
  - Findr is the concept of a full-fledged framework of dealing with the issue of missing & lost people 
    on a country-scale. 
    The app can be used to post about missing people, and post about found(lost) people.
    The app also boosts search functionality with fuzzy search capabilities.
    It also contains information about a person, missing or lost. 
    Additionally the app matches the poster of a missing person's post, if the missing person
    was already found in the records as a found(lost) person.

## Base Structure
  The app is based on the Serverless architecture (FaaS) for the Back-end, 
  based on the AWS Lambda platform.
  The Front-end is an android application.
  
## Android Structure
  * The Android application is built using the MVVM design pattern.
  * The Application can also be used offline (We store the posts and fetch them every hour or if there's any new post).
  
  ![MVVM Design Pattern](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
  
  ## Libraries Used:
   - [Retrofit](https://github.com/square/retrofit)
   - [GSON](https://github.com/google/gson)
   - [Material Components](https://github.com/material-components/material-components-android)
   - [Kodein](https://github.com/Kodein-Framework/Kodein-DI)
   - [CircleImageView](https://github.com/hdodenhof/CircleImageView)
   - [Groupie](https://github.com/lisawray/groupie)
   - [AnimatedBottomBar](https://github.com/Droppers/AnimatedBottomBar)
  
## Stack
  The app is based on AWS services mostly. Services include:
    - AWS API Gateway       (REST API Endpoints)
    - AWS LAMBDA            (Serverless Business Logic)
    - MongoDB Atlas         (Database Solution)
    - MongoDB Atlas Search  (Search Solution)
    - AWS Cloudwatch        (Logs)
