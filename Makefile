inputfile = test.txt
LOX_DIR = com/craftinginterpreters/lox
Lox = $(LOX_DIR)/Lox.java
TOOL_DIR = com/craftinginterpreters/tool
GEN_AST = $(TOOL_DIR)/GenerateAst.java

all: $(Lox)
	@mkdir -p bin
	@javac -d bin $(Lox)

tool: $(GEN_AST)
	@mkdir -p bin
	@javac -d bin $(GEN_AST)

gen: tool
	@java -cp bin com.craftinginterpreters.tool.GenerateAst "com/craftinginterpreters/lox"

run: all
	@java -cp bin com.craftinginterpreters.lox.Lox

test: $(inputfile) all
	@java -cp bin com.craftinginterpreters.lox.Lox $<

clean:
	@rm -rf bin

.PHONY: all run clean test tool gen
