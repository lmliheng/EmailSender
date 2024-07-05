#include <stdio.h>
#include <string.h>
#include <stdlib.h>


// 也许需要一个系统匹配版本调度程序



int main(int argc, char *argv[]) {
    if (argc == 1 ) { // argc参数为1时
    
        printf("欢迎使用SendMailSerevr!!\n");
        printf("安装jdk 21环境: send i jdk21\n");
        printf("快速上手: send hello\n");

        return 1;
    }

    if (argc == 2) { // argc参数为2时

     if (strcmp(argv[1], "hello") == 0 && argc == 2) {  // 安装功能，使用strcmp进行字符串比较，并修正逻辑判断顺序

        printf("开始运行java程序\n");    
        system("java -jar ../target/mail-1.0-jar-with-dependencies.jar");

        return 1;

    }

    }


    return 0;
}

