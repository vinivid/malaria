from Graph import Graph
import sys

def main() -> None:
    map = Graph(file=sys.stdin)
    map.visit_all() 
    
if __name__ == "__main__":
    main()