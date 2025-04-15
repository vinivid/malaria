from Dado import Dado
from random import Random

class RolaDados:
    __dados : list[Dado] 

    def __init__(self, n : int, seed : int):
        r : Random = Random(seed)
        if seed == 0:
            self.__dados = [Dado() for _ in range(0, n)]
        else:
            self.__dados = [Dado(6, r.randint()) for _ in range(0, n)]
    
    def rolar(self, quais : list[bool] | str) -> list[int]:
        z = quais
        if type(quais) is str:
            z = list[bool] = [False] * len(self.__dados)
            for i in quais:
                z[int(i,10)] = True

        for i in range(0, len(self.__dados)):
            if quais[i]:
                self.__dados[i].rolar()
        
        return [x.getLado() for x in self.__dados]
    
    def rolar(self):
        ok : list[int]
        for i in range(0, len(self.__dados)):
            ok[i] = self.__dados[i].rolar
    
    def toString(self) -> str:
        s : str = ''
        for i in range(0, 5):
            base : int = i * 8

            for d in self.__dados:
                p = d.toString()

                s += p[base: base + 7]
                s += "    "
            
            s+= "\n"
        
        return s