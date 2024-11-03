cmake_minimum_required(VERSION 3.29)
project(CPlusPlus)

set(CMAKE_CXX_STANDARD 20)

set(JAVA_HOME "C:\\Program Files\\Java\\jdk-22")
include_directories(${JAVA_HOME}/include)
include_directories(${JAVA_HOME}/include/win32)

add_library(CPlusPlus SHARED library.cpp)
