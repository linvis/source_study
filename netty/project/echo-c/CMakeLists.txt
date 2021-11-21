cmake_minimum_required(VERSION 3.20)
project(echo_c C)

set(CMAKE_C_STANDARD 99)

add_executable(echo_c main.c epoll_server.c)
