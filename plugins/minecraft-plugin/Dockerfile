FROM maven:latest as builder
LABEL authors="mburr"
WORKDIR /home/app
ENV MC_PLUGINS_DIR=/home/app/build/
ADD ./ /home/app
RUN mvn install
RUN ls /home/app/build/

FROM marctv/minecraft-papermc-server:latest as mcserver
ENV MEMORYSIZE='1G'
COPY --from=builder /home/app/build/SmarthomePlugin.jar /data/plugins/
EXPOSE 25565
ENTRYPOINT ["/opt/minecraft/docker-entrypoint.sh"]