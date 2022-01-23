@echo off
set CLASSPATH=.;lib/*;*.xml;*.yml;sorting-0.0.1-SNAPSHOT.jar;
set PATH=%PATH%
set JAVA_HOME=%JAVA_HOME%
set GC_TUNE = -XX:NewRatio=3 -XX:SurvivorRatio=4 -XX:TargetSurvivorRatio=90 -XX:MaxTenuringThreshold=8 -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=4 -XX:ParallelGCThreads=4 -XX:+CMSScavengeBeforeRemark -XX:PretenureSizeThreshold=64m -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=50 -XX:CMSMaxAbortablePrecleanTime=6000 -XX:+CMSParallelRemarkEnabled -XX:+ParallelRefProcEnabled
javaw -Xms2048m -Xmx2048m %GC_TUNE% -Dfile.encoding=UTF-8 -jar sorting-0.0.1-SNAPSHOT.jar &
exit
@pause