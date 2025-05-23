from Graph import Graph
import sys

def main() -> None:
    mapa = Graph(file=sys.stdin)
    print(mapa.no_dict)
    
if __name__ == "__main__":
    main()
