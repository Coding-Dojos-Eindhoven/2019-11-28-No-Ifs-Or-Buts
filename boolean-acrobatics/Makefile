compile_tests:
	g++ tests.c logic.c -o tests -lgtest -lgtest_main -lpthread -g -O0

run_tests: compile_tests
	./tests
