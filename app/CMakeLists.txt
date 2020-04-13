
cmake_minimum_required(VERSION 3.4.1)


add_subdirectory(src/main/cpp/librtmp)
#引入ffmpeg头文件
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/include)
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/x264lib)
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}")

file(GLOB SRC "${CMAKE_SOURCE_DIR}/src/main/cpp/*.cpp")

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${SRC})


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

target_link_libraries( # Specifies the target library.
        native-lib
        avcodec
        avfilter
        avformat
        avutil
        swresample
        swscale
        android
        z
        OpenSLES
        rtmplib
        x264
        faac
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})