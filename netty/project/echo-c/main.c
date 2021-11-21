#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>

int main() {
    int listen_fd = socket(AF_INET, SOCK_STREAM, 0);
    int conn_fd;
    pid_t  child_pid;
    struct sockaddr_in serv_addr, client_addr;

    memset(&serv_addr, 0, sizeof(serv_addr));

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(5050);

    bind(listen_fd, (struct sockaddr*)&serv_addr, sizeof serv_addr);

    // backlog:1000
    listen(listen_fd, 1000);

    while (1) {
        socklen_t client_len = sizeof client_addr;
        conn_fd = accept(listen_fd, (struct sockaddr*)&client_addr, &client_len);

        if ((child_pid = fork()) == 0) {
            char read_buf[128];
            close(listen_fd);

            int len = read(conn_fd, read_buf, 128);
            printf("%s", read_buf);

            write(conn_fd, read_buf, len);

            pthread_exit(0);
        }

        close(0);
    }

    return 0;
}
