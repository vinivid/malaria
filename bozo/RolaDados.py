from Dado import Dado
from random import Random

class RolaDados:
    __dados : list[Dado] 

    def __init__(self, n : int, seed : int):
        r : Random = Random(seed)
        if seed == 0:
            self.__dados = [Dado() for _ in range(0, n)]
        else:
            self.__dados = [Dado(n=6, seed=r.randint(1,10000)) for _ in range(0, n)]
    
    def rolar(self, quais : list[bool] | str | None = None) -> list[int]:
        if quais == None:
            ok : list[int] = [0] * len(self.__dados) 
            for i in range(0, len(self.__dados)):
                ok[i] = self.__dados[i].rolar()
            return ok
        
        z = quais
        if type(quais) is str:
            str_numbers : list[str] = [int(x, 10) - 1 for x in ''.join(quais.split()) if x.isdecimal()] 
            z : list[bool] = [False] * len(self.__dados)
            for i in str_numbers:
                if i >= 5 or i < 0:
                    continue
                
                z[i] = True

        for i in range(0, len(self.__dados)):
            if z[i]:
                self.__dados[i].rolar()
        
        return [x.getLado() for x in self.__dados]
    
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