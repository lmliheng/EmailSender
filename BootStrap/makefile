CC = gcc
CFLAGS = -I./includes -Wall -fPIC
OBJFILES = objs/bootstrap.o 
TARGET = send


all: $(TARGET)

objs:
	@mkdir -p objs

objs/bootstrap.o: src/bootstrap.c objs
	$(CC) $(CFLAGS) -c $< -o $@


$(TARGET): $(OBJFILES) 
	$(CC) $(CFLAGS) -pie -o $(TARGET) 

clean:
	rm -f $(OBJFILES) $(TARGET) 


