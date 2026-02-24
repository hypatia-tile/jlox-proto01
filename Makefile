inputfile = test.txt

all: bin/Lox.class

bin/Lox.class: Lox.java
	@mkdir -p bin
	@javac -d bin $<

$(inputfile): all
	@java -cp bin Lox $@

run: all
	@java -cp bin Lox

clean:
	@rm -rf bin

.PHONY: all run clean
