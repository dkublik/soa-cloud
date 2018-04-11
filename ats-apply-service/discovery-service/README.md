arc-ng-discovery-service
==================================
Discovery service for arc-ng


Building
--------
* nexus credentials for gradle
    you need to provide credentials to access http://nexus.mtvi.com/nexus/content/groups/public repo
    you can add properties to build or put them inside:

        ~/.gradle/gradle.properties
        mtvnproximityUser=<YOUR_LDAP_USERNAME>
        mtvnproximityPassword=<YOUR_LDAP_PASSWORD>
        
To build a package type:

    ./gradlew build

Publishing
---------------------
in root project:

    ./gradlew clean publish -Prelease.useAutomaticVersion=true

Automatically releases new versions of all releasable applications and publishes them to

    http://nexus.mtvi.com/nexus/content/repositories/releases/tech/viacom/arc-ng/

All Applications in project share version (because they share components) and shouldn't be published / released separately.

Continuous Deployment
---------------------
jenkins feature https://john.jenkins.vmn.io/job/ARC-NG/job/arc-ng-discovery-service-features-ci/
jenkins master build and release: https://john.jenkins.vmn.io/view/ARC%20NG/job/ARC-NG/job/arc-ng-discovery-service-pipeline/


Running locally
--------
When running locally project should be run in __dev-local__ profile

__prerequisites__
* Postgres 9x db up and running

__run from idea__
* run class tools.DevDiscoveryApp

this is shortcut from running __tech.viacom.arc.DiscoveryApp__ with __-Dspring.profiles.active=dev-local__ VM option

__run with java -jar__
* cd discovery-service/build/lib
* java -Dspring.profiles.active=dev-local -jar discovery-service-{version}.jar

__run with gradle__
* cd arc-ng
* gradle -Dspring.profiles.active=dev-local bootRun

  
IDE Configuration
-----------------
Projects uses Lombok features so developers should install proper plugins for IDE.

If you are using IntelliJ IDEA:
1. **Enable annotation processing**

    In Preferences in section:

        Build, Execution, Deployment > Compiler > Annotation Processors

    check "Enable annotation processing"

2. **Import & Use ARC Code Style Schema**

    In Preferences in section:
    
        Editor -> Code Style
        
    Type "Manage..." button then select and import code schema file: **idea-code-schema.xml** from project root directory 

Reference
=========
[Confluence Page](https://confluence.mtvi.com/display/ARC/NG)<br />



