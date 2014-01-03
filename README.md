# Cloudfoundry-gemfire

Simple Sample app to demonstrate the Gemfire Service Broker for CloudFoundry.  Prior to utilizing this demo Application it is assumed you have:

* Created a CloudFoundry environment.  For example, using this [https://github.com/cloudfoundry/bosh-lite](https://github.com/cloudfoundry/bosh-lite)
* Deployed Gemfire and the Gemfire Service Broker using BOSH.  See this: [https://github.com/Pivotal-Field-Engineering/gemfire-bosh-release](https://github.com/Pivotal-Field-Engineering/gemfire-bosh-release)
* Running the sample app assumes there is a region created called 'test'.  This may be created using GFSH and service broker functions installed along with the Gemfire Service broker:
```<nowiki>
gfsh> execute function --id=provision --arguments=test
 ```
 
1. Build your application using maven
 ```
C02JW12SDKQ5-3:cf-gemfire azwickey$ mvn install
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building cloudfoundry-gemfire 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 

.....................

[INFO] 
[INFO] --- maven-war-plugin:2.3:war (default-war) @ cloudfoundry-gemfire ---
[INFO] Packaging webapp
[INFO] Assembling webapp [cloudfoundry-gemfire] in [/Users/azwickey/development/demo-projects/cf-gemfire/target/cloudfoundry-gemfire-0.0.1-SNAPSHOT]
[INFO] Processing war project
[INFO] Copying webapp resources [/Users/azwickey/development/demo-projects/cf-gemfire/src/main/webapp]
[INFO] Webapp assembled in [710 msecs]
[INFO] Building war: /Users/azwickey/development/demo-projects/cf-gemfire/target/cloudfoundry-gemfire-0.0.1-SNAPSHOT.war
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ cloudfoundry-gemfire ---
[INFO] Installing /Users/azwickey/development/demo-projects/cf-gemfire/target/cloudfoundry-gemfire-0.0.1-SNAPSHOT.war to /Users/azwickey/.m2/repository/pivotal/org/cloudfoundry-gemfire/0.0.1-SNAPSHOT/cloudfoundry-gemfire-0.0.1-SNAPSHOT.war
[INFO] Installing /Users/azwickey/development/demo-projects/cf-gemfire/pom.xml to /Users/azwickey/.m2/repository/pivotal/org/cloudfoundry-gemfire/0.0.1-SNAPSHOT/cloudfoundry-gemfire-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.448s
[INFO] Finished at: Fri Jan 03 14:31:18 EST 2014
[INFO] Final Memory: 11M/245M
[INFO] ------------------------------------------------------------------------
```
 
2. Deploy the application using the CloudFoundry CLI, creating a gemfire service binding along the way:
```
C02JW12SDKQ5-3:cf-gemfire azwickey$ cf push --path target/cloudfoundry-gemfire-0.0.1-SNAPSHOT.war 

Name> gemfire-cloud

Instances> 1

1: 128M
2: 256M
3: 512M
4: 1G
Memory Limit> 512M

Creating gemfire-cloud... OK

1: gemfire-cloud
2: none
Subdomain> 1            

1: 10.244.0.34.xip.io
2: cloud.io
3: none
Domain> 1                 

Binding gemfire-cloud.10.244.0.34.xip.io to gemfire-cloud... OK

Create services for application?> y

1: p-gemfire , via 
2: user-provided , via 
What kind?> 1

Name?> p-gemfire-20983

1: 1GB-replicated: Multi-tenant Gemfire service; 1GB data storage replicated
Which plan?> 1

Creating service p-gemfire-20983... OK
Binding p-gemfire-20983 to gemfire-cloud... OK
Create another service?> n

Bind other services to application?> n

Save configuration?> n

Uploading gemfire-cloud... OK
Preparing to start gemfire-cloud... OK
-----> Downloaded app package (34M)
-----> Downloading OpenJDK 1.7.0_45 from http://download.pivotal.io.s3.amazonaws.com/openjdk/lucid/x86_64/openjdk-1.7.0_45.tar.gz (15.6s)
       Expanding JRE to .java (0.8s)
-----> Downloading Spring Auto-reconfiguration 0.7.2 from http://download.pivotal.io.s3.amazonaws.com/auto-reconfiguration/auto-reconfiguration-0.7.2.jar (1.3s)
       Modifying /WEB-INF/web.xml for Auto Reconfiguration
-----> Downloading Tomcat 7.0.47 from http://download.pivotal.io.s3.amazonaws.com/tomcat/tomcat-7.0.47.tar.gz (5.7s)
       Expanding Tomcat to .tomcat (0.1s)
-----> Downloading Buildpack Tomcat Support 1.1.1 from http://download.pivotal.io.s3.amazonaws.com/tomcat-buildpack-support/tomcat-buildpack-support-1.1.1.jar (0.2s)
-----> Uploading droplet (71M)
Checking status of app 'gemfire-cloud'...
  0 of 1 instances running (1 starting)
  0 of 1 instances running (1 starting)
  0 of 1 instances running (1 starting)
  0 of 1 instances running (1 starting)
  0 of 1 instances running (1 starting)
  0 of 1 instances running (1 starting)
  0 of 1 instances running (1 starting)
  1 of 1 instances running (1 running)
Push successful! App 'gemfire-cloud' available at gemfire-cloud.10.244.0.34.xip.io
```

3.  You should now be able to put and get from a cache defined in the app using a browser or command line app:
```
C02JW12SDKQ5-3:cf-gemfire azwickey$ curl http://gemfire-cloud.10.244.0.34.xip.io/put/some-awesome-value/HelloGemfire

C02JW12SDKQ5-3:cf-gemfire azwickey$ curl http://gemfire-cloud.10.244.0.34.xip.io/get/some-awesome-value
HelloGemfire
```