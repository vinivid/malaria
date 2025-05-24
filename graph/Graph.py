from typing import TextIO
import sys
import heapq
import re

class Graph:
    no_dict : dict[str, dict[str, int]] 

    def add_node(self, node : str) -> None :
        self.no_dict.update({node : {}})

    def add_neighbour(self, node : str, neighbour : str, distance : int) -> None :
        self.no_dict[node].update({neighbour : distance})     

    def __initiate_from_file(self, file : TextIO) -> None :
        current_parent : str = ""
        for line in file:
            if line.startswith('\t'):
                values = re.split(r" |\t", line.strip())
                self.add_neighbour(current_parent, values[0], int(values[1]))
            else:
                current_parent = line.strip()
                self.add_node(current_parent)

    def __init__(self, **kwargs) -> None:
        if len(kwargs) == 0:
            self.no_dict = {}
        elif "file" in kwargs:
            self.no_dict = {}
            self.__initiate_from_file(kwargs["file"])
        else:
            print("Wrong constructor kwargs")
    
    def djikstra(self, start : str, destination : str) -> tuple[int, list[str]]:
        p_queue   : list[tuple[int, str]] = []
        distances : dict[str, int] = {x : sys.maxsize for x in self.no_dict.keys()}
        prev      : dict[str, str] = {x : "" for x in self.no_dict.keys()}

        heapq.heappush(p_queue, (0, start))
        distances[start] = 0

        while p_queue:
            _, u = heapq.heappop(p_queue)

            if u == destination:
                break
            
            for v, z in self.no_dict[u].items():
                alt = distances[u] + z

                if alt < distances[v]:
                    distances[v] = alt
                    prev[v] = u 
                    heapq.heappush(p_queue, (alt, v))
        
        path : list[str] = [destination, prev[destination]]
        dist : int = self.no_dict[destination][prev[destination]]
        current_visit = prev[destination]

        while current_visit != start:
            preve_prev = prev[current_visit]
            path.append(preve_prev)
            dist += self.no_dict[current_visit][preve_prev]
            current_visit = preve_prev

        path.pop()
        path.reverse()
        return (dist, path)
    
    def visit_all(self) -> None :

        nodes : list[str] = list(self.no_dict.keys())

        for source in nodes:
            for visit in nodes:
                if source == visit:
                    continue
                
                dist, path = self.djikstra(source, visit)

                print(f'{source} para {visit}')
                print(f'\tDistancia: {dist},0')

                print('\tCaminho: ', end='')
                for n in path:
                    print(f' --> {n}', end='')

                print('\n',end='')

            print('---------------------------------------------')