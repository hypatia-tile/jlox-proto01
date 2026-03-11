inputfile = test.txt
LOX_DIR = com/craftinginterpreters/lox
Lox = $(LOX_DIR)/Lox.java

all: $(Lox)
	@mkdir -p bin
	@javac -d bin $(Lox)

run: all
	@java -cp bin com.craftinginterpreters.lox.Lox

test: $(inputfile) all
	@java -cp bin com.craftinginterpreters.lox.Lox $<

clean:
	@rm -rf bin

.PHONY: all run clean test
