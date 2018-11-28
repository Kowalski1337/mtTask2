all:
	dot -Tpng tree1.dot > tree1.png
	dot -Tpng tree2.dot > tree2.png
	dot -Tpng tree3.dot > tree3.png
	dot -Tpng tree4.dot > tree4.png
	
clean:
	rm -rf *.dot *.png
