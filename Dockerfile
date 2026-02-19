FROM ubuntu:latest
LABEL authors="kenmoe"

ENTRYPOINT ["top", "-b"]