
cmake_minimum_required(VERSION 3.4.1)
#rtmp.o log.o amf.o hashswf.o parseurl.o
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -DNO_CRYPTO")
add_library(
        rtmplib
        STATIC
        rtmp.c
        log.c
        amf.c
        hashswf.c
        parseurl.c
)