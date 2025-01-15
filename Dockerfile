FROM ubuntu:latest
LABEL authors="Proccessor"

ENTRYPOINT ["top", "-b"]