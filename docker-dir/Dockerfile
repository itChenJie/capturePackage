#镜像出处
FROM jdk-8u144:jdk
#维护者信息
MAINTAINER ChenJie
#镜像操作指令
#在容器的根目录中创建spring-cloud文件夹
RUN mkdir -p /spring-cloud
COPY ./example-1-SNAPSHOT.jar /spring-cloud/example-1-SNAPSHOT.jar
#将本地的可执行文件拷贝到Docker容器中的根目录下
COPY ./app-entrypoint.sh /
#给app-entrypoint.sh赋予可执行权限
RUN chmod +x /app-entrypoint.sh
#对外暴漏的端口号
EXPOSE 8080
#开机启动
ENTRYPOINT ["/app-entrypoint.sh"]
#ENTRYPOINT ["java","-jar","example.jar","main.DemoApplication"]
# docker run -d -p 18080:8080 --name docker-resource leesia/resource:1.0