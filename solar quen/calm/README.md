# RelaxSound
https://developer.android.com/jetpack/

# Deploy
- Command build : mvnw -P dev package
- Folder deploy : /home/bigqsys/Projects/Solar-Mobile-Platform/calm-service/target
- Command copy : 

    #server: 192.168.0.135
    scp calm-0.0.1-SNAPSHOT.war root@192.168.0.135:/home/bigqsys/Projects/Solar-Mobile-Platform/calm-service/target

# Android Debug Tools
adb forward tcp:8080 tcp:8080

# Room Data
https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html#7
https://github.com/erikcaffrey/Movies-PagingLibrary-Arch-Components/tree/7ee4b35fbd0ef1573bbb15e9f4e0472d3662fd56
