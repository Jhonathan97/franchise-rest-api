FROM ubuntu:latest
LABEL authors="jhona"

ENTRYPOINT ["top", "-b"]