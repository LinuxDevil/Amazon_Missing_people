# Amazon_Missing_people (Findr)
  Amazon Teckathon App
## DEMO
  https://www.getpostman.com/collections/abe3d09eab858b5485a1
  for a test of the API's Routes
  
## Idea
  - Findr is the concept of a full-fledged framework of dealing with the issue of missing & lost people 
    on a country-scale. 
    The app can be used to post about missing people, and post about found(lost) people.
    The app also boosts search functionality with fuzzy search capabilities.
    It also contains information about a person, missing or lost. 
    Additionally the app matches the poster of a missing person's post, if the missing person
    was already found in the records as a found(lost) person.


  
## Structure
  The app is based on the Serverless architecture (FaaS) for the Back-end, 
  based on the AWS Lambda platform.
  The Front-end is an android application.
  
## Stack
  The app is based on AWS services mostly. Services include:
    - AWS API Gateway       (REST API Endpoints)
    - AWS LAMBDA            (Serverless Business Logic)
    - MongoDB Atlas         (Database Solution)
    - MongoDB Atlas Search  (Search Solution)
    - AWS Cloudwatch        (Logs)
