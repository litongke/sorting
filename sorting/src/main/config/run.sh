export JAVA_HOME=/usr/java/jdk1.8.0_144
export PATH=$JAVA_HOME/bin:$PATH
export LANG=zh_cn.UTF-8
export NLS_LANG=AMERICA_CHINA.ZHS16GBK
GC_TUNE="-XX:NewRatio=3 \
-XX:SurvivorRatio=4 \
-XX:TargetSurvivorRatio=90 \
-XX:MaxTenuringThreshold=8 \
-XX:+UseConcMarkSweepGC \
-XX:+UseParNewGC \
-XX:ConcGCThreads=4 -XX:ParallelGCThreads=4 \
-XX:+CMSScavengeBeforeRemark \
-XX:PretenureSizeThreshold=64m \
-XX:+UseCMSInitiatingOccupancyOnly \
-XX:CMSInitiatingOccupancyFraction=50 \
-XX:CMSMaxAbortablePrecleanTime=6000 \
-XX:+CMSParallelRemarkEnabled \
-XX:+ParallelRefProcEnabled"
#JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=6162,server=y,suspend=n "
java -Xmx4096m $GC_TUNE $JAVA_OPTS  -Dfile.encoding=UTF-8 -jar provider-load.jar &
