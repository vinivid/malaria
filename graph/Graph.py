from typing import TextIO

class Graph:
    no_dict : dict[str, dict[str, int]] 
    cities  : list[str]

    def __initiate_from_file(self, file : TextIO) -> None :
        current_parent : str = ""
        for line in file:
            if line.startswith('\t'):
                values = line.removeprefix('\t').removesuffix('\n').split(" ")
                print(values)
                #self.no_dict[current_parent].update({values[0] : int(values[1])})                
            else:
                current_parent = line.removesuffix('\n') 
                self.no_dict.update({current_parent : {}})

    def __init__(self, **kwargs) -> None:
        if len(kwargs) == 0:
            self.no_dict = {}
        elif "file" in kwargs:
            self.no_dict = {}
            self.__initiate_from_file(kwargs["file"])
        else:
            print("Wrong constructor kwargs")