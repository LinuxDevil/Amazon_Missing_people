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

## UX

- Project Idea :
One time, our friend Osama suggested the idea of an application that is oriented about finding people missing due to the disasters and accidents that happened in the Beirut explosion, and this idea was implemented by all members of the team to make it applicable. Our goal from the beginning is to stop the daily posts about missing persons and find a place; A certified one helps everyone find missing people in the fastest and easiest way.
Our goal is clear and achievable, the idea will not stop here, we will work to develop, improve and apply it on the world as soon as possible.
The success lies in the attempt. Thank you Amazon for giving us this opportunity to participate and come up with an idea for an application that will serve many people in the near future.

- As a UX designer, I focus on my designs to be easy to handle and contain creativity at the same time.
Making sure that the application serve all groups is hard, but with the help of my team we were able to make the application with a simple and easy to learn user interface.
The idea of the application was inspired by the events that we live in every day, so we were devoting all our efforts to make the application functional and design appropriate, in addition to using design thinking during the application design process


- فكرة المشروع :
في جلسة حوارية كنا نقوم بها ، اقترح صديقنا اسامة فكرة التطبيق التي تسهل ايجاد الاشخاص المفقودين اثر الكوارث والحوادث كما حدث في انفجار بيروت ، وقد تم تطوير الفكرة من خلال جميع اعضاء الفريق لجعلها قابلة للتطبيق ، هدفنا من البداية ايقاف البوستات اليومية عن الاشخاص المفقودين وايجاد مكان واحد معتمد يساعد الجميع في ايجاد المفقودين بأقصى سرعة وبأسهل طريقة.
هدفنا واضح ، الفكرة لن تتوقف هنا ، سنعمل على تطويرها وتحسينها وتطبيقها على ارض الواقع بأقصى مدة ممكنة.
ان النجاح يكمن في المحاولة ، شكرا أمازون على اتاحة هذه الفرصة لنا بالمشاركة وايجاد فكرة لتطبيق سيخدم الكثيرين في الوقت القريب.
ك مصممة ، اركز في تصاميمي على ان تكون سهلة التعامل وتحتوي على الابداع في نفس الوقت ، ان جعل التطبيق يخدم جميع الفئات التي قد تستخدم التطبيق أمر صعب ، ولكنني وبمساعدة فريقي استطعنا ان نجعل التطبيق ذو واجهة جمالية بسيطة وسهلة التعلم.
فكرة التطبيق جاءت من وحي الأحداث التي نعيشها كل يوم ، لذا كنت انا وفريقي نصب كل جهدنا في جعل التطبيق ذو فكرة جيدة وتصميم مناسب، بالاضافة الى استخدام التفكير التصميمي اثناء عملية تصميم التطبيق.


## Stack
  The app is based on AWS services mostly. Services include:
    - AWS API Gateway       (REST API Endpoints)
    - AWS LAMBDA            (Serverless Business Logic)
    - MongoDB Atlas         (Database Solution)
    - MongoDB Atlas Search  (Search Solution)
    - AWS Cloudwatch        (Logs)
