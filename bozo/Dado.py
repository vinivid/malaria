from random import Random

class Dado:
    __lados : int
    __atual : int
    __r : Random

    s010 : str = "|  *  |\n"
    s100 : str = "|*    |\n"
    s001 : str = "|    *|\n"
    s000 : str = "|     |\n"
    s101 : str = "|*   *|\n"
    s111 : str = "|* * *|\n"

    def toString(self) -> str:
        if self.__lados != 6:
            return "Não há representação para esse dados"
        
        s : str = "+-----+\n"
        match self.__atual:
            case 1:
                s += (self.s000 + self.s010 + self.s000)
            case 2:
                s += (self.s100 + self.s000 + self.s001)
            case 3:
                s += (self.s100 + self.s010 + self.s001)
            case 4:
                s += (self.s101 + self.s000 + self.s101)
            case 5:
                s += (self.s101 + self.s010 + self.s101)
            case 6:
                s += (self.s111 + self.s000 + self.s111)
            case _:
                print("ERRO COM RELACAO AO ATUAL")
        
        s += ("+-----+\n")
        return s

    def rolar(self) -> int:
        # é dessa forma q funciona no java
        self.__atual = self.__r.randint(1, self.__lados)
        return self.__atual
    
    def getLado(self) -> int:
        return self.__atual

    def __init__(self, n : int | None = None, seed : int | None = None):
        if (n == None and seed == None):
            self.__lados = 6
            self.__r = Random()
            self.rolar()
        elif (seed == None):
            self.__lados = n
            self.__r = Random()
            self.rolar()
        else:
            self.__lados = n
            self.__r = Random(seed)
            self.rolar()