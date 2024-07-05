CC = gcc
CFLAGS = -I./includes -Wall -fPIC
OBJFILES = objs/Bootstrap.o 
TARGET = send


all: $(TARGET)

objs:
	@mkdir -p objs

objs/Bootstrap.o: src/Bootstrap.c objs
	$(CC) $(CFLAGS) -c $< -o $@


$(TARGET): $(OBJFILES) 
	$(CC) $(CFLAGS) -pie -o $(TARGET) 

clean:
	rm -f $(OBJFILES) $(TARGET) 


