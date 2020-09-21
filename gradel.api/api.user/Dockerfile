#基础镜像
FROM  openjdk:8-jdk-alpine

ENV WORKDIR=/home/tomcat

#定义暴露端口
EXPOSE 8080

#定义挂载目录
VOLUME WORKDIR 

#定义环境变量
ARG JAVA_OPTS='-server -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:$WORKDIR/logs/gc_%p.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m -XX:+HeapDumpOnOutOfMemoryError'

#添加java 包到工作目录
ADD target/api.user.jar $WORKDIR/api.user.jar

#定义环境变量
ENV MEM_OPTS="-Xms1024m -Xmx1024m"
ENV APOLLO_ENV="PRO"
ENV APOLLO_CONFIG_SERVICE=""
#切换
WORKDIR $WORKDIR
#执行启动命令
ENTRYPOINT java ${JAVA_OPTS} ${MEM_OPTS} -Denv=${APOLLO_ENV} -Dapollo.configService=${APOLLO_CONFIG_SERVICE}  -Duser.timezone=GMT+8 -jar -Djava.security.egd=file:/dev/./urandom api.user.jar
